package com.spring.bom.dao.bear;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.bear.Chat;
import com.spring.bom.model.bear.User;
import com.spring.bom.model.iron.Follow;

@Repository
public class ChatDaoImpl implements ChatDao {
	// Mybatis 세션 
	@Autowired
	private SqlSession session;
		
	@Override
	public List<User> uonline(User user){
		
		List<User> uonline = null;
		try {
			uonline = session.selectList("listOnline", user);
		} catch (Exception e) {
			System.out.println("UserDaoImpl uonline e.getMessage() -> " + e.getMessage());
			
		}
		System.out.println("UserDaoImpl uonline.size() ->  "   + uonline.size());
		return uonline;
	}

	@Override
	public int chatmsg(Chat chat) {
		
		
		return session.insert("insertmsg",chat);
	}

	@Override
	public List<Chat> roomId(int sessionId) {
		List<Chat> roomId = null;
		System.out.println("chatDaoImple roomId - > "  + sessionId);
		try {
			roomId = session.selectList("listId",sessionId); 
			
		} catch (Exception e) {
			System.out.println("ChatDaoImpl chatinglist exception - > " + e.getMessage());
		}
		return roomId;
	}

	@Override
	public List<Chat> chatinglist(Chat chat) {
		List<Chat> chatinglist = null;
		try {
			chatinglist = session.selectList("chainglist", chat);
			
			
		} catch (Exception e) {
			System.out.println("ChatDaoImpl chatinglist exception - > " + e.getMessage());
		}
		return chatinglist;
	}

	@Override
	public int mycreate(Chat chat) {
		
		List<Chat> selectck =  session.selectList("selectck" , chat);
		System.out.println("방 존재여부 리스트 - > " + selectck);
		//방생성되어있는지 체크 
		if(selectck.isEmpty()) {
			System.out.println("ucode 와 uopcode 체크결과 방이 생성되어있지않습니다 ");
			return session.insert("mycreate",chat);
		}else {
			System.out.println("체크결과 방이 생성되어있습니다 .");
			return 0 ;
		}
		
		//System.out.println("chatDaoImpl mycreate 진행 - > chat 안에 있는 uopcode -> " + chat.getUopcode());
		//return session.insert("mycreate",chat);
	}
	
	
	
	

	@Override
	public String selectcode(String atid) {
		System.out.println("chatDaoImpl selectcode 진행 - > atid " +atid);
		return session.selectOne("selectcode" ,atid);
	}

	@Override
	public List<User> userinfo(int kiwoong) {
		System.out.println("chatDaoImpl userinfo 진행 - > ucode " +kiwoong);
		return session.selectList("userinfo", kiwoong);
	}

		

}
