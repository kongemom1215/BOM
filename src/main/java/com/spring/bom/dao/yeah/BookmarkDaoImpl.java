package com.spring.bom.dao.yeah;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.yeah.YeahBoard;
import com.spring.bom.model.yeah.YeahBookmark;
import com.spring.bom.model.yeah.UserBookmarkBoard;



@Repository
public class BookmarkDaoImpl implements BookmarkDao{

	@Autowired
	private SqlSession session;    

	@Override
	public List<UserBookmarkBoard> ubmBoardListDao(int ucode) {
		List<UserBookmarkBoard>  ubmBoardListDao = null;
		try {
			ubmBoardListDao = session.selectList("ubmBoardListMapper" ,ucode );
			System.out.println("BookmarkDaoImpl ubmBoardListDao ubmBoardListDao.size()->" +ubmBoardListDao.size());
			
			for(int i=0; i<ubmBoardListDao.size(); i++) {
				if (ubmBoardListDao.get(i).getBtype().equals("quote")) {
					UserBookmarkBoard quoteboard=session.selectOne("ubmQuoteBoard", ubmBoardListDao.get(i).getBbcode());
					System.out.println("쿼트문 실행");
					ubmBoardListDao.get(i).setQ_uimage(quoteboard.getUimage());
					System.out.println("quoteboard.getUimage() -> "+quoteboard.getUimage());
					ubmBoardListDao.get(i).setQ_nickname(quoteboard.getUnickname());
					ubmBoardListDao.get(i).setQ_atid(quoteboard.getUatid());
					ubmBoardListDao.get(i).setQ_content(quoteboard.getBcontent());
					ubmBoardListDao.get(i).setQ_regdate(quoteboard.getBregdate());
					
					if (quoteboard.getBattach()!= null) {
						System.out.println("quoteboard.getBattach() -> "+quoteboard.getBattach());
						ubmBoardListDao.get(i).setQ_attachsrc(quoteboard.getBattach().substring(6));
						ubmBoardListDao.get(i).setQ_attachtype(quoteboard.getBattach().substring(0, 5));
						System.out.println("quoteboard.getBattach().substring(6) -> "+quoteboard.getBattach().substring(6));
						System.out.println("quoteboard.getBattach().substring(0, 5) -> "+quoteboard.getBattach().substring(0, 5));
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("BookmarkDaoImpl ubmBoardListDao e.getMessage()->" +e.getMessage());
			
		}	
		
		return ubmBoardListDao;
	}

	@Override
	public int delete(YeahBoard board) {
	    System.out.println("BookmarkDaoImpl delete...");  
		int delete = 0;
	
		try {
			System.out.println("BookmarkDaoImpl  board.bcode -> " + board.getBcode());
	    	delete = session.delete("delete",board);
	    		
		} catch (Exception e) {
			System.out.println("BookmarkDaoImpl Exception =>" +e.getMessage());
		}  

		return delete;
	}

	@Override
	public int deleteAll(String ucode) {
		System.out.println("BookmarkDaoImpl deleteAll");
		int deleteAll = 0;
		try {
			deleteAll = session.delete("deleteAll" , ucode);
		
		}catch (Exception e) {
			System.out.println("BookmarkDaoImpl Exception => " +e.getMessage());
			
		}
		
		
		return deleteAll;
	}

	

	
}
