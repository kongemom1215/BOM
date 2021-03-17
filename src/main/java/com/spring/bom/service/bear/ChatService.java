package com.spring.bom.service.bear;

import java.util.List;

import com.spring.bom.model.bear.Chat;
import com.spring.bom.model.bear.User;

public interface ChatService {

	
	
	//user 온라인유저 조회
	List<User> 		uonline(User user);
	//chat msg 저장 
	int				chatmsg(Chat chat);
	
	//chat roomlist 불러오기
	
	List<Chat> roomId		(int sessionId);
	
	//chat 채팅방 내용 불러오기
	List<Chat> chatinglist	(Chat chat);
	//채팅방 생성 세션아이디와 상대회원아이디 
	int mycreate(Chat chat);
	
	
	//@아이디로 회원ucode값 가져오기
	String selectcode(String uatid);
	// 세션아이디로 회원정보가져오기
	
	List<User> userinfo(int kiwoong);
	
}
