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
import com.spring.bom.model.yeah.YeahBookmark;
import com.spring.bom.model.iron.Follow;
import com.spring.bom.model.iron.HashTag;
import com.spring.bom.model.iron.User_Info;
import com.spring.bom.model.yeah.UserBookmarkBoard;
import com.spring.bom.service.iron.FollowService;
import com.spring.bom.service.iron.HashTagService;
import com.spring.bom.service.yeah.YeahBookmarkService;

@Controller
public class Yeah_Controller {

	@Autowired
	private YeahBookmarkService bms;

	@Autowired
	private FollowService fs;

	@Autowired
	private HashTagService hs;
		
	@RequestMapping(value = "yeah/bookmark")
	public String bookmark(Model model, HttpSession session, HttpServletRequest request) {
		User_Info user = (User_Info) session.getAttribute("user");
		int int_ucode = user.getUcode();
		model.addAttribute("user", user);
		System.out.println("Yeah_Controller yeah/bookmark int_ucode -> " + int_ucode);
		System.out.println("Yeah_Controller bookmark Start...");
		System.out.println("Yeah_Controller bookmark ucode->" + int_ucode);

		List<UserBookmarkBoard> ubmBoardList = bms.ubmBoardList(int_ucode);

		System.out.println("Yeah_Controller ubmBoardList.size()" + ubmBoardList.size());
		model.addAttribute("ucode", int_ucode);
		model.addAttribute("ubmBoardList", ubmBoardList); // 주소가 넘어가기 때문에

		for (int i = 0; i < ubmBoardList.size(); i++) {
			if (ubmBoardList.get(i).getBattach() != null) {
				ubmBoardList.get(i).setBattachSrc(ubmBoardList.get(i).getBattach().substring(6));
				ubmBoardList.get(i).setBattachType(ubmBoardList.get(i).getBattach().substring(0, 5));
				System.out.println("setBattachSrc -> " + ubmBoardList.get(i).getBattachSrc());
				System.out.println("setBattachType -> " + ubmBoardList.get(i).getBattachType());
			}
		}
		model.addAttribute("ubmBoardList", ubmBoardList); // 주소가 넘어가기 때문에
		// 팔로우 추천1 나와 관심사가 곂치는 유저를 추천
		List<Follow> suggestFlist1 = fs.getSuggestFollowList1(user.getUcode());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);
		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = fs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2);
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

		return "yeah/bookmark";
	}

	@RequestMapping(value = "/yeah/update", method = RequestMethod.GET)
	public String update(@RequestParam("ucode") String ucode, @RequestParam("bcode") String bcode, Model model) {
		YeahBookmark bd = new YeahBookmark();
		bd.setUcode(Integer.parseInt(ucode));
		bd.setBcode(Integer.parseInt(bcode));
		System.out.println("Yeah_Controller update bd.ucode -> " + bd.getUcode());
		System.out.println("Yeah_Controller update bd.bcode -> " + bd.getBcode());
		int result = bms.update(bd);

		System.out.println("Yeah_Controller update result -> " + result);
		return "forward:bookmark"; // Controller간 data 전달시 활용(Model에 담아서 파라메타 이동)
	}

	@RequestMapping(value = "/yeah/updateAll", method = RequestMethod.GET)
	public String deleteAll(@RequestParam("ucode") String ucode, Model model) {
		int result = bms.updateAll(ucode);
		System.out.println("Yeah_Controller update result -> " + result);
		return "forward:bookmark"; // Controller간 data 전달시 활용(Model에 담아서 파라메타 이동)
	}

}
