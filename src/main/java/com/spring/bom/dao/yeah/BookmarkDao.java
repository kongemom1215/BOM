package com.spring.bom.dao.yeah;

import java.util.List;


import com.spring.bom.model.yeah.YeahBoard;

import com.spring.bom.model.yeah.UserBookmarkBoard;

public interface BookmarkDao {
	List<UserBookmarkBoard> ubmBoardListDao(int ucode);

	int delete(YeahBoard board);

	int deleteAll(String ucode);


	
}
