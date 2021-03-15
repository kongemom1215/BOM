package com.spring.bom.service.iron;

import com.spring.bom.model.iron.Like_Bookmark;

public interface Like_BookmarkService {

	int checkBoardLike(Like_Bookmark lb);

	int doBoardLike(Like_Bookmark lb);

	Like_Bookmark getBoardLikeCount(Like_Bookmark lb);

	Like_Bookmark checkBoardBookmark(Like_Bookmark lb);

}
