package com.spring.bom.service.yeah;

import java.util.List;


import com.spring.bom.model.yeah.YeahBoard;

import com.spring.bom.model.yeah.UserBookmarkBoard;



public interface BookmarkService {
    List<UserBookmarkBoard> ubmBoardList(int int_ucode);

	int delete(YeahBoard bd);

	int deleteAll(String ucode);




	
    
	
}
