package com.spring.bom.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//o jungchurl Controller
//- active navi support
//- view timeline
//- view sub_contents
//o profile support

@Controller
public class JungchurlController {
	
	@GetMapping(value = "test")
	public String goTest(HttpServletRequest request,Model model) {
		return "test";
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