package com.spring.bom.dao.iron;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.iron.Like_Bookmark;

@Repository
public class Like_BookmarkDaoImpl implements Like_BookmarkDao {
	@Autowired
	private SqlSession session;

	@Override
	public int checkBoardLike(Like_Bookmark lb) {
		System.out.println("[iron] LikeBookmarkDaoImpl checkBoardLike start...");
		int step=0;
		// 게시글의 북마크 여부 체크
		// step == 0 -> 북마크 x 
			// 좋아요 여부 체크
			// 좋아요 x => step == 1 > insert
			// 좋아요 o => step == 2 > delete
		
		// step == 1 -> 북마크 o
			// 좋아요 여부 체크
			// 좋아요 x => step == 3 > update
			// 좋아요 o => step == 4 > update 
		System.out.println("[iron] LikeBookmarkDaoImpl checkBoardLike1 start...");
		step = session.selectOne("checkBoardLikeStep1", lb);
		System.out.println("[iron] LikeBookmarkDaoImpl checkBoardLike1 end -> "+step);
		if(step==0) {
			step = session.selectOne("checkBoardLikeStep2", lb);
			if(step==0) return 1;
			else return 2;
		}
		else {
			step = session.selectOne("checkBoardLikeStep2", lb);
			if(step==0) return 3;
			else return 4;
		}
		
	}

	@Override
	public int doBoardLike(Like_Bookmark lb) {
		System.out.println("[iron] LikeBookmarkDaoImpl doBoardLike start...");
		int result = 0;
		int state = lb.getLikeLogicStep();
		switch(state) {
		case 1:			// 좋아요 x => step == 1 > insert 
			result = session.insert("doBoardLike1", lb);
			break;
		case 2:			// 좋아요 o => step == 2 > delete
			result = session.delete("doBoardLike2", lb);
			break;
		case 3:			// 좋아요 x => step == 3 > update
			result = session.update("doBoardLike3", lb);
			break;
		case 4:			// 좋아요 o => step == 4 > update -> set null
			result = session.update("doBoardLike4", lb);
			break;
		}
		System.out.println("result -> " + result);
		return result;
	}

	@Override
	public Like_Bookmark getBoardLikeCount(Like_Bookmark lb) {
		System.out.println("[iron] LikeBookmarkDaoImpl getBoardLikeCount start...");
		lb = session.selectOne("BoardLikeCount", lb);
		System.out.println("[iron] LikeBookmarkDaoImpl lb.LikeCount -> "+lb.getLikeCount());
		System.out.println("Board: "+lb.getBcode());
		return lb;
	}

	@Override
	public Like_Bookmark checkBoardBookmark(Like_Bookmark lb) {
		System.out.println("[iron] LikeBookmarkDaoImpl checkBoardBookmark start...");
		lb = session.selectOne("checkBoardBookmark",lb);
		System.out.println("ucode -> "+lb.getUcode());
		System.out.println("bcode -> "+lb.getBcode());
		System.out.println("ltype -> "+lb.getLtype());
		return lb;
	}

	@Override
	public int doBookmark(Like_Bookmark lb) {
		System.out.println("[iron] LikeBookmarkDaoImpl doBookmark start...");
		int state = session.selectOne("checkBookmark1",lb);	//0: goto insert or update ... 1: exist
		if(state==1) {
			return 0;
		}
		else {
			int state2 = session.selectOne("checkBookmark2",lb);
			if(state2==0) {
				session.insert("insertBookmark",lb);
				return 1;
			}else {
				return 2;
			}
		}
	}

}
