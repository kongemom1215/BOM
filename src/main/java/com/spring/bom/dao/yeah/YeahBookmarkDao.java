package com.spring.bom.dao.yeah;

import java.util.List;


import com.spring.bom.model.yeah.YeahBoard;
import com.spring.bom.model.yeah.YeahBookmark;
import com.spring.bom.model.yeah.UserBookmarkBoard;

public interface YeahBookmarkDao {
	List<UserBookmarkBoard> ubmBoardListDao(int ucode);
	int update(YeahBookmark bm);
	int updateAll(String ucode);


	
}
