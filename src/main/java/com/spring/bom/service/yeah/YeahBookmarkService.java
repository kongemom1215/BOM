package com.spring.bom.service.yeah;

import java.util.List;


import com.spring.bom.model.yeah.YeahBookmark;
import com.spring.bom.model.yeah.UserBookmarkBoard;



public interface YeahBookmarkService {
    List<UserBookmarkBoard> ubmBoardList(int int_ucode);
	int update(YeahBookmark bm);
	int updateAll(String ucode);







	
    
	
}
