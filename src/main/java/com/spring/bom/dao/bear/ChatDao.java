package com.spring.bom.dao.bear;

import java.util.List;

import com.spring.bom.model.bear.Chat;
import com.spring.bom.model.bear.User;
import com.spring.bom.model.iron.Follow;

public interface ChatDao {
	
	//user 온라인유저 조회
	List<User> 	uonline(User user);
	
	//chat msg 저장 
	int		chatmsg(Chat chat);
	
	//chat roomlist 불러오기
	
	List<Chat> roomId(int sessionId);

	
	//chat 채팅방 메세지 불러오기 
	List<Chat> chatinglist  (Chat chat);
	
	//채팅방 생성 회원아이디와 상대 아이디 둘다 db속에 넣을려고
	int mycreate(Chat chat);
	
	
	//회원아이디로 ucode값 가져오기
	String selectcode(String atid);
	//세션아이디로 회원정보 가져오기
	
	List<User> userinfo(int kiwoong);


	

}
