package com.spring.bom.dao.hoon;

import java.util.List;

import com.spring.bom.model.hoon.Junghun;

public interface JunghunDao {
	List<Junghun> listSearch(Junghun junghun);
	List<Junghun> listUser(Junghun junghun);
	List<Junghun> listNew(Junghun junghun);
	List<Junghun> listCount(Junghun junghun);
	int		  	  searchData(Junghun junghun);
	List<Junghun> listHash(Junghun junghun);
	List<Junghun> listTrend(Junghun junghun);
	List<Junghun> searchkeyword(Junghun junghun);
	int 		  deleterow(int ucode);
	List<Junghun> searchblock(Junghun junghun);
	int		 	  searchlike(Junghun junghun);
}
