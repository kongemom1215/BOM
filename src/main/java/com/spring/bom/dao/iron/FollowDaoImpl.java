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
	public List<Follow> getSuggestFollowList1(int ucode) {
		System.out.println("[iron] FollowDaoImpl getSuggestFllowList1 start...");
		List<Follow> suggestFlist = session.selectList("followListWhoSameTrend",ucode);
		System.out.println("[iron] FollowDaoImpl suggestFlist.size() -> "+suggestFlist.size());
		return suggestFlist;
	}
	
	@Override
	public List<Follow> getSuggestFollowList2(int ucode) {
		System.out.println("[iron] FollowDaoImpl getSuggestFllowList2 start...");
		List<Follow> suggestFlist = session.selectList("followListWhoIknow",ucode);
		System.out.println("[iron] FollowDaoImpl suggestFlist.size() -> "+suggestFlist.size());
		return suggestFlist;
	}

	@Override
	public int fwInsert(Follow follow) {
		System.out.println("[kiwoong] FollowDaoImpl fwInsert start....");
		return session.insert("fwInsert",follow);
	}
	
	@Override
	public int unfollow(Follow follow) {
		System.out.println("[kiwoong] FollowDaoImpl unfoloow start ....");
		int result = session.delete("unfollow",follow);
		return result;
	}
}