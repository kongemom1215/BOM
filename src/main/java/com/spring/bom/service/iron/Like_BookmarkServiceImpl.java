package com.spring.bom.service.iron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.iron.Like_BookmarkDao;
import com.spring.bom.model.iron.Like_Bookmark;
@Service
public class Like_BookmarkServiceImpl implements Like_BookmarkService {
	@Autowired
	private Like_BookmarkDao lbd;
	
	@Override
	public int checkBoardLike(Like_Bookmark lb) {
		System.out.println("[iron] Like_BookmarkServiceImpl checkBoardLike start...");
		return lbd.checkBoardLike(lb);
	}

	@Override
	public int doBoardLike(Like_Bookmark lb) {
		System.out.println("[iron] Like_BookmarkServiceImpl doBoardLike start...");
		return lbd.doBoardLike(lb);
	}

	@Override
	public Like_Bookmark getBoardLikeCount(Like_Bookmark lb) {
		System.out.println("[iron] Like_BookmarkServiceImpl getBoardLikeCount start...");
		return lbd.getBoardLikeCount(lb);
	}

	@Override
	public Like_Bookmark checkBoardBookmark(Like_Bookmark lb) {
 		System.out.println("[iron] Like_BookmarkServiceImpl checkBoardBookmark start...");
		return lbd.checkBoardBookmark(lb);
	}

	@Override
	public int doBookmark(Like_Bookmark lb) {
		System.out.println("[iron] Like_BookmarkServiceImpl doBookmark start...");
		return lbd.doBookmark(lb);
	}

}
