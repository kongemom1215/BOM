package com.spring.bom.dao.hoon;

import java.util.List;

import com.spring.bom.model.hoon.Junghun;
import com.spring.bom.model.iron.Follow;

public interface JunghunDao {
	List<Junghun> listSearch(Junghun junghun);
	List<Junghun> listUser(Junghun junghun);
	List<Junghun> listNew(Junghun junghun);
	List<Junghun> listCount(Junghun junghun);
	int		  	  searchData(Junghun junghun);
	List<Junghun> listHash(Junghun junghun);
	List<Junghun> listTrend();
	List<Junghun> searchkeyword(Junghun junghun);
	int 		  deleterow(int ucode);
	List<Junghun> searchblock(Junghun junghun);
	int		 	  searchlike(Junghun junghun);
	List<Junghun> searchlistall(Junghun junghun);
	List<Junghun> searchbattach(Junghun junghun);
	List<Junghun> searchbattachvideo(Junghun junghun);
	List<Junghun> searchbattach2(Junghun junghun);
	List<Junghun> searchbattachvideo2(Junghun junghun);
	
	List<Follow> getSuggestFollowList1(int ucode);	//관심사가 겹치는 유저 추천

	List<Follow> getSuggestFollowList2(int ucode);	//나를 팔로우하는 유저 추천

}
