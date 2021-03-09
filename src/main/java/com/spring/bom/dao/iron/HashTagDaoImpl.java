package com.spring.bom.dao.iron;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.bom.model.iron.HashTag;

public class HashTagDaoImpl implements HashTagDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<HashTag> getHashTagRanking() {
		System.out.println("[iron] HashTagDaoImpl getHashTagRanking start...");
		List<HashTag> list = session.selectList("HashtagRankList");
		System.out.println("listSize -> "+list.size());
		return list;
	}

}
