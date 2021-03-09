package com.spring.bom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.bom.model.iron.Board;
import com.spring.bom.model.iron.Follow;
import com.spring.bom.model.iron.HashTag;
import com.spring.bom.model.iron.User_Info;
import com.spring.bom.service.iron.BoardService;
import com.spring.bom.service.iron.FollowService;
import com.spring.bom.service.iron.HashTagService;
import com.spring.bom.service.iron.User_InfoService;

//o jungchurl Controller
//- active navi support
//- view timeline
//- view sub_contents
//o profile support

@Controller
public class JungChurlController {
	@Autowired
	private User_InfoService us;

	@Autowired
	private BoardService bs;

	@Autowired
	private FollowService fs;

	@Autowired HashTagService hs;
	
	@GetMapping(value = "iron/timeline")
	public String goTimeline(HttpServletRequest request, Model model) {
		// System.out.println("[JungChurlController]");
		System.out.println("context->" + request.getContextPath());
		System.out.println("[JungChurlController] goTimeline start...");
		HttpSession session = request.getSession();
		System.out.println("[JungChurlController] Do -> us.getLoginUserInfo(21)");
		// 임시 로그인 세팅
		User_Info user = us.getLoginUserInfo(21); // DB에서 회원코드 21인 회원 정보 가져오기. 필요한것만!
		session.setAttribute("user", user); // Session에 user 데이터 저장
		System.out.println("[JungChurlController] Result : " + user + " -> us.getLoginUserInfo(21)");

		// 로그인유저가 볼 수 있는 글데이터 가져오기
		System.out.println("[JungChurlController] Do -> bs.getTimelineBoard(user.getUcode())");
		List<Board> bdlist = bs.getTimelineBoard(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + bdlist.size()
				+ " -> bs.getTimelineBoard(user.getUcode()).size()");
		//// 미디어파일 세부지정
		System.out.println("[JungChurlController] Setting Battach Type and Source -> start...");
		for (int i = 0; i < bdlist.size(); i++) {
			if (bdlist.get(i).getBattach() != null) {
				System.out.println("[" + i + "]" + " battach Data : " + bdlist.get(i).getBattach());
				bdlist.get(i).setBattachType(bdlist.get(i).getBattach().substring(0, 5));
				bdlist.get(i).setBattachSrc(bdlist.get(i).getBattach().substring(6));
				System.out.println("battach Type : " + bdlist.get(i).getBattachType() + " / battach Source : "
						+ bdlist.get(i).getBattachSrc());
			}
		}
		System.out.println("[JungChurlController] Setting Battach Type and Src -> done!!!");

		model.addAttribute("tl_list_size", bdlist.size());
		model.addAttribute("tl_list", bdlist);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = fs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = fs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		//실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		return "iron/timeline";
	}

	@GetMapping(value = "iron/profile")
	public String goProfile(Model model) {
		return "iron/profile";
	}
	// Service Bean Zone
	// Autowired
	// Navigation
	// RequestMapping
	// 홈
	// 검색하기
	// 알림
	// 쪽지
	// 북마크
	// 정보수정 및 통계
	// ajax?
	// 글쓰기

	// TimeLine
	// 글쓰기
	// 게시글
	// 팔로우추천 나와 관심사가 같은 사람들 추천
	// Sub Content
	// 검색
	// 팔로우추천 나를 팔로우하는 사람들 추천
	// Profile

}