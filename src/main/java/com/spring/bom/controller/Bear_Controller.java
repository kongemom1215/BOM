package com.spring.bom.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.bom.model.bear.Chat;
import com.spring.bom.model.bear.User;
import com.spring.bom.model.iron.Follow;
import com.spring.bom.model.iron.User_Info;
import com.spring.bom.service.bear.ChatService;
import com.spring.bom.service.iron.FollowService;

@Controller
public class Bear_Controller {
	@Autowired
	private ChatService cs;
    @Autowired 
    private FollowService fs;
	 

	List<Chat> roomlist = new ArrayList<Chat>();
	static int ccode = 0;
	/*
	 * @RequestMapping(value = "bear/main") public String gotest(HttpSession
	 * session) { System.out.println("기웅이 메인페이지 작동"); int ucode = (int)
	 * session.getAttribute("ucode"); //List<User> user = (List<User>)
	 * session.getAttribute("user"); System.out.println("ucode 값가져오기 " + ucode);
	 * return "bear/main"; }
	 * 
	 */

	@RequestMapping(value = "bear/chat")
	public String uonline(User user, Model model, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		int kiwoong = (int) session.getAttribute("ucode");
		System.out.println("세션에 저장되어있는 ucode = " + kiwoong);

		User_Info loginUser = (User_Info) session.getAttribute("user");
		//팔로우 추천1 나와 관심사가 곂치는 유저를 추천
		User_Info user1 = new User_Info();
		user1.setUcode(kiwoong);
		List<Follow> suggestFlist1 = fs.getSuggestFollowList1(user1.getUcode());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);
		//팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = fs.getSuggestFollowList2(user1.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		
		//리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림  
		Collections.shuffle(suggestFlist2);
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);
		
		
		
		

		List<User> useronline = cs.uonline(user);
		List<User> userinfo = cs.userinfo(kiwoong);
		System.out.println("bear_controller userinfo - > " + userinfo);
		System.out.println("Bear_controller uonline.size -> " + useronline.size());
		model.addAttribute("user", loginUser);
		model.addAttribute("useronline", useronline);
		model.addAttribute("userinfo", userinfo);
		return "bear/chat";
	}

	// 방 페이지
	@RequestMapping(value = "bear/room")
	public ModelAndView room(HttpServletRequest request, HttpSession session) {
		// 전에했던 페이지 볼려고 잠시만들어놓음 끝나면 삭제 요망
		session = request.getSession();
		// int kkk = 2;
		// session.setAttribute("kiwoong", kkk);
		// 여기까지

		int loginsession = (int) session.getAttribute("ucode");

		System.out.println("방페이지를 열겠습니다 . 받은 로그인 코드 -> " + loginsession);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("bear/room");
		System.out.println("룸페이지 mv -> " + mv);
		return mv;
	}

	// @아이디로 회원 ucode값 가져오기
	@RequestMapping(value = "bear/selectcode", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String selectcode(String uatid, Model model) {

		System.out.println("controller selectcode 실행 ");

		System.out.println("아작스통해 msg값을 가져옵니다 uatid -> " + uatid);

		String ucode = cs.selectcode(uatid);
		System.out.println("ucode - >" + ucode);
		int strUcode = Integer.parseInt(ucode);
		System.out.println("strUcode -> " + strUcode);
		return ucode;
	}

	// 방생성
	@RequestMapping(value = "bear/createRoom")
	public @ResponseBody int createRoom(@RequestParam HashMap<Object, Object> params, HttpSession session, Chat chat) {

		// ajax 의 msg 에있는 것을 가지고 옴

		String uopcode = (String) params.get("uopcode");
		// int uopcode = (int) params.get("uopcode");
		System.out.println("createroom 상대코드 (방제목) -> " + uopcode);
		// httpsession 가지고오기
		int loginsession = (int) session.getAttribute("ucode");
		System.out.println("createRoom loginsession -> " + loginsession);

		int uopcode1 = Integer.parseInt(uopcode);

		// 방생성 ccode, uopcode, sessionId
		// 시퀀스 하나올리고 세션아이디로 값넣고 , 시퀀스그대로하고 상대방아이디 값넣기
		Chat chat1 = new Chat();
		chat1.setUcode(loginsession);
		chat1.setUopcode(uopcode1);
		System.out.println("createRoom chat1 안에  getUcode() ->" + chat1.getUcode());
		System.out.println("createRoom chat1 안에  getUopcode() ->" + chat1.getUopcode());
		int mycreate = cs.mycreate(chat1);
		System.out.println("mycreate 값 " + mycreate);
		if (mycreate > 0) {
			System.out.println("방생성되었습니다 ucode = sessionId  ");

		} else {
			System.out.println("방이 존재하여 생성되지않았습니다.");
		}

		return mycreate;
	}

	/**
	 * 방 정보가져오기
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "bear/getRoom")
	public @ResponseBody List<Chat> getRoom(@RequestParam HashMap<Object, Object> params, HttpSession session) {
		System.out.println("방정보 가져오기를 시작하겠습니다 .");
		// 세션아이디 불러오기
		int sessionId = (int) session.getAttribute("ucode");
		System.out.println("방정보에 저장된 세션 id 값은 -> " + sessionId);

		// 세션 id로 방 목록 가져오기
		List<Chat> roomlist = cs.roomId(sessionId);
		System.out.println("시작할때 방의대한 리스트입니다 - > " + roomlist);

		return roomlist;
	}

	/**
	 * 채팅방
	 * 
	 * @return
	 */
	@RequestMapping(value = "bear/moveChating")
	public @ResponseBody List<Chat> chating1(@RequestParam HashMap<Object, Object> params, HttpServletRequest request,
			HttpSession session) {
		System.out.println("채팅방로그시작");
		int sessionId = (int) session.getAttribute("ucode");
		int roomnumber = Integer.parseInt((String) params.get("roomnumber"));
		System.out.println("채팅방에 ccode  -> " + roomnumber);
		System.out.println("채팅방에 세션의있는 아이디값 -> " + sessionId);

		System.out.println("bear_controller chating start roomNumber -> " + roomnumber);
		Chat chat = new Chat();
		chat.setCcode(roomnumber);
		List<Chat> chainglist = cs.chatinglist(chat);

		List<Chat> new_list = chainglist.stream().filter(o -> o.getCcode() == roomnumber).collect(Collectors.toList());
		System.out.println("new_list - > " + new_list);
		if (new_list != null && new_list.size() > 0) {

			// 메세지불러오기

			// mv.addObject(chainglist);
			System.out.println("채팅방 chainglist - > " + chainglist.size());

			// System.out.println("controller chating mv -> " + mv);
			// mv.setViewName("bear/chat2");
			return chainglist;
		} else {
			System.out.println("chating else -> ");

		}
		// 쪽지방 코드로 방목록 뽑아오고 ccode는 로그인한 session 아이디로 뽑아와 상대와 내자신 메세지 구별하기
		return new_list;

	}

	// 팔로워 하는로직
	@RequestMapping(value = "bear/followchk")
	public @ResponseBody String followchk(String uopcode, HttpSession session) {
		// 세션아이디
		int sessionId = (int) session.getAttribute("ucode");
		System.out.println("세션 아이디값 - > " + sessionId);
		// 상대방 아이디
		int uopcode1 = Integer.parseInt(uopcode);

		Follow follow = new Follow();
		follow.setUcode(sessionId);
		follow.setFopcode(uopcode1);

		
		  int result = fs.fwInsert(follow); 
		String kkk =  Integer.toString(result);
		  return kkk;
		  
		
	}

}
