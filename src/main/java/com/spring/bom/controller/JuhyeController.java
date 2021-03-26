package com.spring.bom.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.bom.model.god.JHBoard;
import com.spring.bom.model.god.JHHashtag;
import com.spring.bom.model.god.JHLike;
import com.spring.bom.model.god.JHUser_info;
import com.spring.bom.model.god.JHVote;
import com.spring.bom.service.god.JHBoardService;
import com.spring.bom.service.god.JHHashtagService;
import com.spring.bom.service.god.JHUserService;
import com.spring.bom.service.god.JHVoteService;

@Controller
public class JuhyeController {
	@Autowired
	private JHUserService us;
	@Autowired
	private JHVoteService vs;
	@Autowired
	private JHBoardService bs;
	@Autowired
	private JHHashtagService hs;

	// 예약글 수 가져오는 아작스
	@RequestMapping(value = "god/getReserveNum", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String getReserveNum(int ucode, Model model) {
		System.out.println("[GOD] start JuhyeController getReserveNum");
		return us.getReserveNum(ucode);
	}

	// 저장글 수 가져오는 아작스
	@RequestMapping(value = "god/getSaveNum", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String getSaveNum(int ucode, Model model) {
		System.out.println("[GOD] start JuhyeController getSaveNum");
		return us.getSaveNum(ucode);
	}

	// 예약 글 리스트 정보 가져오는 아작스
	@RequestMapping(value = "god/getReserveList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<JHBoard> getReserveList(int ucode, Model model) {
		System.out.println("[GOD] start JuhyeController getReserveList");
		return us.getReserveList(ucode);
	}

	// 저장 글 리스트 정보 가져오는 아작스
	@RequestMapping(value = "god/getSaveList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<JHBoard> getSaveList(int ucode, HttpServletRequest request) {
		System.out.println("[GOD] start JuhyeController getSaveList ucode -> " + ucode);
		List<JHBoard> list = us.getSaveList(ucode);
		/*
		 * for(int i=0; i<list.size(); i++) {
		 * list.get(i).setCtx(request.getContextPath()); }
		 */
		return list;
	}

	// 글 등록 컨트롤
	@RequestMapping(value = "god/write", method = RequestMethod.POST)
	public String write(JHBoard board, JHVote vote, Model model, @RequestParam("attach") MultipartFile file,
			HttpServletRequest request) {
		System.out.println("[GOD] start JuhyeController write bcode -> " + board.getSavebcode());
		if (board.getBregdate().equals("1")) {
			String regdate = request.getParameter("year").substring(2, 4) + "/"
					+ request.getParameter("month").substring(0, 2) + "/" + request.getParameter("day").substring(0, 2)
					+ " " + request.getParameter("hours").substring(0, 2) + ":"
					+ request.getParameter("minute").substring(0, 2) + ":00";
			System.out.println("regdate -> " + regdate);
			board.setBregdate(regdate);
		} else
			board.setBregdate(null);
		if (request.getParameter("image").equals("1")) {
			try {
				String filename = System.currentTimeMillis() + file.getOriginalFilename();
				file.transferTo(new File(request.getServletContext().getRealPath("/image/") + filename));
				board.setBattach("image/" + filename);
			} catch (Exception e) {
				System.out.println("[GOD] JuhyeController 글쓰기 이미지 오류 -> " + e.getMessage());
			}
		} else if (request.getParameter("video").equals("1")) {
			try {
				String filename = System.currentTimeMillis() + file.getOriginalFilename();
				file.transferTo(new File(request.getServletContext().getRealPath("/video/") + filename));
				board.setBattach("video/" + filename);
			} catch (Exception e) {
				System.out.println("[GOD] JuhyeController 글쓰기 비디오 오류 -> " + e.getMessage());
			}
		} else
			board.setBattach(null);
		if (request.getParameter("vote").equals("1")) {
			// 마감투표설정
			vote.setVendtime(vs.settingTime(vote));
			// 중복투표설정
			if (request.getParameter("multipleChk") == null || request.getParameter("multipleChk").equals("")) {
				vote.setVmulti(0);
			} else if (request.getParameter("multipleChk").equals("on"))
				vote.setVmulti(1);
			// 투표 등록
			int vcode = vs.insertVote(vote);
			board.setBvotecode(vote.getVcode());
			int result = bs.insertVoteBoard(board);
		} else {
			// 글등록하자
			int result = bs.insertBoard(board);
		}

		if (board.getBbcode() != 0) {
			// 답글일 경우 count 올려주기
			if (board.getBtype().equals("reply")) {
				// 단 저장글이 아닐떄
				if (board.getBsaveorrsvd() != 0)
					bs.upBreplyCount(board);
			}
			// 인용일 경우 count 올려주기
			else if (board.getBtype().equals("quote")) {
				// 단 저장글이 아닐때
				if (board.getBsaveorrsvd() != 0)
					bs.upScrapCount(board);
			}
		}

		// 해시태그 처리
		if (request.getParameter("hashtags") != null || request.getParameter("hashtags") != "") {
			if (board.getBsaveorrsvd() != 0) {
				String hashtags = request.getParameter("hashtags");
				String[] hashs = hashtags.split("#");
				ArrayList<String> hashlist = new ArrayList<String>();
				System.out.println("hash -> " + hashs);
				for (String hash : hashs) {
					if (!hashlist.contains(hash))
						hashlist.add(hash);
				}
				System.out.println("haslist -> " + hashlist);
				hashlist.remove(0);
				JHHashtag hashtag = new JHHashtag();
				hashtag.setHbcode(board.getBcode());
				int hash_result = hs.insertHash(hashlist, hashtag);
			}
		}
		return "redirect:/iron/timeline";
	}

	// 선택한 예약/저장글 정보 가져오는 아작스
	@RequestMapping(value = "god/callSaveBoard", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JHBoard callSaveBoard(int bcode, HttpServletRequest request) {
		System.out.println("[GOD] start JuhyeController callSaveBoard");
		JHBoard board = bs.getBoard(bcode);
		if (board.getBtype().equals("quote")) {
			board.setQprofileimage(request.getContextPath() + "/profile_image/" + board.getQprofileimage());
		}
		return board;
	}

	// 예약/저장했던 글 삭제하는 아작스
	@RequestMapping(value = "god/deleteSaveWrite", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int deleteSaveWrite(int[] bcodes) {
		System.out.println("[GOD] start JuhyeController deleteSaveWrite");
		int result = bs.deleteSaveWrite(bcodes);

		return result;
	}

	// 유저의 팔로우 리스트를 가져오는 아작스
	@RequestMapping(value = "god/getFollowerList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<JHUser_info> getFollowerList(int ucode) {
		System.out.println("[GOD] start JuhyeController getFollowerList");
		List<JHUser_info> followList = us.getFollowerList(ucode);

		return followList;
	}

	// 검색한 유저 목록을 가져오는 아작스
	/*
	 * @RequestMapping(value = "god/getSearchUser", produces =
	 * "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public List<JHUser_info> getSearchUser(String search_value) {
	 * System.out.println("[GOD] start JuhyeController getSearchUser");
	 * List<JHUser_info> searchList = us.getSearchList(search_value);
	 * 
	 * return searchList; }
	 */
	
	@RequestMapping(value="god/AjaxLikeAction", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JHLike getUserLike(int bcode, int ucode) {
		System.out.println("[GOD] start JuhyeController getUserLike");
		JHLike like= us.getUserLike(bcode, ucode);
		
		return like;
	}
}
