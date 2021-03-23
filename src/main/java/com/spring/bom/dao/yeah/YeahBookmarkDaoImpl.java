package com.spring.bom.dao.yeah;


import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.yeah.YeahBookmark;
import com.spring.bom.model.yeah.UserBookmarkBoard;



@Repository
public class YeahBookmarkDaoImpl implements YeahBookmarkDao{

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
	public int update(YeahBookmark bm) {
	    System.out.println("BookmarkDaoImpl update...");  
		int workCount = 0;
		
		try {
			System.out.println("BookmarkDaoImpl update getUcode->" + bm.getUcode());
			
            /*
    		 * 1. ucode & bcode 의 조건이 만족하는 컬럼의 bbtype을 0처리
    		 * 2. ucode & bcode 의 조건이 만족하는 컬럼의 ltype = 0 이면 delete 
    		 */
			 int result1 = session.update("oneBookmarkCancel1", bm);
			 System.out.println("Check update result1 -> "+result1);
			 int result2 = session.delete("oneBookmarkCancel2", bm);
			 System.out.println("Check update result2 -> "+result2);
			 workCount = result1 + result2;
			 //workCount -> 0, 1, 2 -> 삭제된 것 이 없는것.(0)
		} catch (Exception e) {
			System.out.println("BookmarkDaoImpl update Exception =>" +e.getMessage());
		}  
		
		return workCount;
	}

	@Override
	public int updateAll(String ucode) {
		System.out.println("BookmarkDaoImpl deleteAll");
		int updateAllCount = 0;
		try {
			 
			/*
			 * 1. ucode & bcode 의 조건이 만족하는 컬럼의 bbtype을 0처리 2. ucode & bcode 의 조건이 만족하는 컬럼의
			 * ltype = 0 이면 delete
			 */
    		 
			 int result1 = session.update("AllBookmarkCancel1", ucode);
			 System.out.println("Check update result1 -> "+result1);
			 int result2 = session.delete("AllBookmarkCancel2", ucode);
			 System.out.println("Check update result2 -> "+result2);
			 updateAllCount = result1 + result2;
			 //workCount -> 0 or N -> 삭제된 것 이 없는것.(0)
		}catch (Exception e) {
			System.out.println("BookmarkDaoImpl Exception => " +e.getMessage());
			
		}
		
		
		return updateAllCount;
	}	

	
}
