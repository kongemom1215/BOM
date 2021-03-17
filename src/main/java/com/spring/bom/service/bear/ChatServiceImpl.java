package com.spring.bom.service.bear;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.bear.ChatDao;
import com.spring.bom.model.bear.Chat;
import com.spring.bom.model.bear.User;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDao cd;
	
	@Override
	public List<User> uonline(User user){
		List<User> userlist = null;
		
		userlist = cd.uonline(user);
		System.out.println("ChatServiceImpl uonline userlist 오른쪽 실시간들어온유저 확인  =>  " + userlist);
		
		return userlist;
	}

	@Override
	public int chatmsg(Chat chat) {
		System.out.println("ChatServiceImpl chatmsg - > " );
		
		return cd.chatmsg(chat);
	}

	@Override
	public List<Chat> roomId(int sessionId) {
		List<Chat> listId =cd.roomId(sessionId);
		System.out.println("ChatServiceImpl roomId listId => " + listId);
		return listId;
	}

	@Override
	public List<Chat> chatinglist(Chat chat) {
		List<Chat> chatinglist = null;
		 chatinglist = cd.chatinglist(chat);
		System.out.println("ChatServiceImpl chatinglist  -> " +chatinglist);
		return chatinglist;
	}

	@Override
	public int mycreate(Chat chat) {
		System.out.println("ChatServiceImpl mycreate 진행  chat.getUopcode() - > " + chat.getUopcode());
		return cd.mycreate(chat);
		}

	@Override
	public String selectcode(String uatid) {
		System.out.println("chatserviceimpl selectcode 진행  -> uatid " + uatid);
		return cd.selectcode(uatid);
	}

	@Override
	public List<User> userinfo(int kiwoong) {
		System.out.println("chatserviceimpl userinfo 진행  -> ucode " + kiwoong);
		return cd.userinfo(kiwoong);
	}
}
