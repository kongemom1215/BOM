package com.spring.bom.controller;



import java.util.ArrayList;
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
import com.spring.bom.model.iron.User_Info;
import com.spring.bom.service.bear.ChatService;

@Controller
public class Bear_Controller {
	@Autowired
	private ChatService cs; 
	
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
	public String uonline(User user, Model model,HttpSession session ,HttpServletRequest request) {
		session= request.getSession();
		int kiwoong =(int) session.getAttribute("ucode");
		System.out.println("세션에 저장되어있는 ucode = "+kiwoong);
		
		 User_Info loginUser = (User_Info)session.getAttribute("user");
		 
		 model.addAttribute("user",loginUser);
		 
		 List<User> useronline =cs.uonline(user);
		 
		 List<User> userinfo = cs.userinfo(kiwoong);
		 System.out.println("bear_controller userinfo - > " +userinfo);
		 
		 System.out.println("Bear_controller uonline.size -> " + useronline.size());
		 model.addAttribute("useronline",useronline);
		 model.addAttribute("userinfo",userinfo);
		 return "bear/chat";
	}

	
	//방 페이지
	@RequestMapping(value = "bear/room")
	public ModelAndView room(HttpServletRequest request,HttpSession session) {
		//전에했던 페이지 볼려고 잠시만들어놓음 끝나면 삭제 요망 
		session = request.getSession();
		//int kkk = 2;
		//session.setAttribute("kiwoong", kkk);
		// 여기까지 
		
		int loginsession= (int) session.getAttribute("ucode");
		
		System.out.println("방페이지를 열겠습니다 . 받은 로그인 코드 -> " + loginsession);
		
	
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bear/room");
		System.out.println("룸페이지 mv -> " +mv);
		return mv;
	}
	
	//@아이디로 회원 ucode값 가져오기
	@RequestMapping(value = "bear/selectcode", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String selectcode (String uatid, Model model) {
		
		System.out.println("controller selectcode 실행 ");
		
		System.out.println("아작스통해 msg값을 가져옵니다 uatid -> " + uatid);
		
		
		String ucode =cs.selectcode(uatid); 
		System.out.println("ucode - >"+ucode );
		int  strUcode =  Integer.parseInt(ucode);
		System.out.println("strUcode -> " + strUcode);
		return ucode;
	}	
	
	
	//방생성
	@RequestMapping(value = "bear/createRoom")
	public @ResponseBody int createRoom(@RequestParam HashMap<Object, Object> params, HttpSession session,Chat chat ){
	
		//ajax 의 msg 에있는 것을 가지고 옴 
		
		String uopcode = (String) params.get("uopcode");
		//int uopcode = (int) params.get("uopcode");
		System.out.println("createroom 상대코드 (방제목) -> " +uopcode );
		//httpsession 가지고오기
		int loginsession= (int) session.getAttribute("ucode");
		System.out.println("createRoom loginsession -> " + loginsession);
		
		int uopcode1 = Integer.parseInt(uopcode) ;
		
		//방생성 ccode, uopcode, sessionId
		//시퀀스 하나올리고 세션아이디로 값넣고 , 시퀀스그대로하고 상대방아이디 값넣기
		Chat chat1 = new Chat();
		chat1.setUcode(loginsession);
		chat1.setUopcode(uopcode1);
		System.out.println("createRoom chat1 안에  getUcode() ->" +chat1.getUcode());
		System.out.println("createRoom chat1 안에  getUopcode() ->" +chat1.getUopcode());
		int mycreate = cs.mycreate(chat1);
		System.out.println("mycreate 값 "+mycreate);
		if(mycreate >0 ) {
			System.out.println("방생성되었습니다 ucode = sessionId  " );
			
		}
			else {
				System.out.println("방이 존재하여 생성되지않았습니다.");
			}
			
		
		return mycreate;
	}
	/**
	 * 방 정보가져오기
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "bear/getRoom")
	public @ResponseBody List<Chat> getRoom(@RequestParam HashMap<Object, Object> params, HttpSession session){
		System.out.println("방정보 가져오기를 시작하겠습니다 .");
		// 세션아이디 불러오기
		int sessionId =  (int) session.getAttribute("ucode");
		System.out.println("방정보에 저장된 세션 id 값은 -> " + sessionId);
		
		
		// 세션 id로 방 목록 가져오기
		List<Chat> roomlist  = cs.roomId(sessionId);
		System.out.println("시작할때 방의대한 리스트입니다 - > " + roomlist);
		
		return roomlist;
	}
	
	/**
	 * 채팅방
	 * @return
	 */
	@RequestMapping(value = "bear/moveChating")
	public @ResponseBody List<Chat> chating1(@RequestParam HashMap<Object, Object> params,HttpServletRequest request,HttpSession session) {
		System.out.println("채팅방로그시작");
		int sessionId =  (int) session.getAttribute("ucode");
		int roomnumber =Integer.parseInt((String) params.get("roomnumber"));
		System.out.println("채팅방에 ccode  -> " +roomnumber);
		System.out.println("채팅방에 세션의있는 아이디값 -> " +sessionId);
		
		
		System.out.println("bear_controller chating start roomNumber -> " +roomnumber);
		Chat chat = new Chat();
		chat.setCcode(roomnumber);
		List<Chat> chainglist = cs.chatinglist(chat);
		
		List<Chat> new_list = chainglist.stream().filter(o->o.getCcode()==roomnumber).collect(Collectors.toList());
		System.out.println("new_list - > " + new_list);
		if(new_list != null && new_list.size() > 0) {
			
		
			//메세지불러오기
			
		//	mv.addObject(chainglist);
			System.out.println("채팅방 chainglist - > " + chainglist.size());
			
		//System.out.println("controller chating mv -> " + mv);
		//	mv.setViewName("bear/chat2");
			return chainglist;
		}else {
			System.out.println("chating else -> ");
			
		}
		//쪽지방 코드로 방목록 뽑아오고 ccode는 로그인한 session 아이디로 뽑아와 상대와 내자신 메세지 구별하기
		return new_list;
	
		
	}
	
}
