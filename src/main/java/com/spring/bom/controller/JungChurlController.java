package com.spring.bom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.bom.iron.service.UserInfoService;

//o jungchurl Controller
//- active navi support
//- view timeline
//- view sub_contents
//o profile support

@Controller
public class JungChurlController {
	/*
	 * @Autowired private UserInfoService us;
	 */
	/*
	 * @Autowired private FollowService fs;
	 * 
	 * @Autowired private BoardService bs;
	 */
	
	@GetMapping(value = "iron/timeline")
	public String goTimeline(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		/*
		 * List<Follow> listFollow1 = fs.getFollowList1(follow); List<Follow>
		 * listFollow2 = fs.getFollowList2(follow);
		 */
		return "iron/timeline";
	}
	
	@GetMapping(value = "iron/profile")
	public String goProfile(Model model) {
		return "iron/profile";
	}
	//Service Bean Zone
	//Autowired
	
	//Navigation
	//RequestMapping
		//홈
		//검색하기
		//알림
		//쪽지
		//북마크
		//정보수정 및 통계
		//ajax? 
		//글쓰기
	
	//TimeLine
		//글쓰기
		//게시글
		//팔로우추천 나와 관심사가 같은 사람들 추천
	//Sub Content
		//검색
		//팔로우추천 나를 팔로우하는 사람들 추천
	//Profile
	
}