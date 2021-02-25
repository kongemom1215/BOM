package com.spring.bom.dao.iron;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.iron.Follow;

@Repository
public class FollowDaoImpl implements FollowDao {
	@Autowired
	private SqlSession session;
	@Override
	public List<Follow> getSuggestFollowList(int ucode) {
		System.out.println("[iron] FollowDaoImpl start...");
		List<Follow> suggestFlist = session.selectList("followListWhoIknow",ucode);
		System.out.println("[iron] FollowDaoImpl suggestFlist.size() -> "+suggestFlist.size());
		return suggestFlist;
	}

}
