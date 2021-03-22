package com.spring.bom.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.spring.bom.model.yeah.YeahBoard;
import com.spring.bom.model.iron.Follow;
import com.spring.bom.model.iron.HashTag;
import com.spring.bom.model.iron.User_Info;
import com.spring.bom.model.yeah.UserBookmarkBoard;
import com.spring.bom.service.iron.FollowService;
import com.spring.bom.service.iron.HashTagService;
import com.spring.bom.service.yeah.BookmarkService;

@Controller
public class Yeah_Controller {

	@Autowired
	private BookmarkService bms;

	@Autowired
	private FollowService fs;

	@Autowired
	private HashTagService hs;
	
	@RequestMapping(value = "yeah/bookmark")
	public String bookmark(Model model, HttpSession session, HttpServletRequest request) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		System.out.println("Yeah_Controller yeah/bookmark 조회 Start...");
		int int_ucode = user.getUcode();
		System.out.println("Yeah_Controller yeah/bookmark int_ucode -> " + int_ucode);
		System.out.println("Yeah_Controller bookmark Start...");
		System.out.println("Yeah_Controller bookmark ucode->" + int_ucode);

		List<UserBookmarkBoard> ubmBoardList = bms.ubmBoardList(int_ucode);

		System.out.println("Yeah_Controller ubmBoardList.size()" + ubmBoardList.size());
		model.addAttribute("ucode", int_ucode);

		for (int i = 0; i < ubmBoardList.size(); i++) {
			// ubmBoardList.set(i,
			// ubmBoardList.get(i)).setBattachSrc(ubmBoardList.get(i).getBattach().substring(6));
			if (ubmBoardList.get(i).getBattach() != null) {
				ubmBoardList.get(i).setBattachSrc(ubmBoardList.get(i).getBattach().substring(6));
				ubmBoardList.get(i).setBattachType(ubmBoardList.get(i).getBattach().substring(0, 5));
				System.out.println("setBattachSrc -> " + ubmBoardList.get(i).getBattachSrc());
				System.out.println("setBattachType -> " + ubmBoardList.get(i).getBattachType());
			}
		}

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = fs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);

		// For Context
		model.addAttribute("context", request.getContextPath());
		model.addAttribute("ubmBoardList", ubmBoardList);
		return "yeah/bookmark";
	}

	@RequestMapping(value = "/yeah/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("ucode") String ucode, @RequestParam("bcode") String bcode, Model model) {
		YeahBoard bd = new YeahBoard();
		bd.setUcode(Integer.parseInt(ucode));
		bd.setBcode(Integer.parseInt(bcode));
		System.out.println("bd.ucode -> " + bd.getUcode());
		System.out.println("bd.bcode -> " + bd.getBcode());
		int result = bms.delete(bd);
		System.out.println("Yeah_Controller delete result -> " + result);
		return "forward:bookmark"; // Controller간 data 전달시 활용(Model에 담아서 파라메타 이동)
	}

	@RequestMapping(value = "/yeah/deleteAll", method = RequestMethod.GET)
	public String deleteAll(@RequestParam("ucode") String ucode, Model model) {
		bms.deleteAll(ucode);

		return "yeah/bookmark";

	}

}