package com.spring.bom.dao.right;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.right.RFollow;
import com.spring.bom.model.right.RInterest;

@Repository
public class RFollowDaoImpl implements RFollowDao {
	@Autowired
	private SqlSession session;
	
	//팔로워 조회
	@Override
	public List<RFollow> selectFollower(int ucode) {
		System.out.println("RFollowDaoImpl List<RFollow> selectFollower start..");
		List<RFollow> followerList = new ArrayList<RFollow>();
		try {
			followerList = session.selectList("rightselectFollower", ucode);
		} catch (Exception e) {
			System.out.println("RFollowDaoImpl List<RFollow> selectFollower Exception->"+e.getMessage());
		}

		return followerList;
	}
	//팔로잉 조회
	@Override
	public List<RFollow> selectFollowing(int ucode) {
		System.out.println("RFollowDaoImpl List<RFollow> selectFollowing start..");
		List<RFollow> followingList = new ArrayList<RFollow>();
		try {
			followingList = session.selectList("rightselectFollowing", ucode);
		} catch (Exception e) {
			System.out.println("RFollowDaoImpl List<RFollow> selectFollowing Exception->"+e.getMessage());
		}

		return followingList;
	}
	//팔로잉 취소
	@Override
	public int deleteFollowing(int ucode, int fopcode) {
		System.out.println("RFollowDaoImpl deleteFollowing start..");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fopcode", fopcode);
		map.put("ucode", ucode);
		System.out.println("RFollowDaoImpl deleteFollowing start..");
		System.out.println("RFollowDaoImpl deleteFollowing map -> "+map);
		int result = 0;
		try {
			result = session.delete("rightdeleteFollowing", map);
		} catch (Exception e) {
			System.out.println("RFollowDaoImpl deleteFollowing Exception->"+e.getMessage());
		}
		return result;
	}
	//팔로우 팔로잉 추가
	@Override
	public int addfollowing(int ucode, int fopcode) {
		System.out.println("RFollowDaoImpl addfollowing start..");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fopcode", fopcode);
		map.put("ucode", ucode);
		System.out.println("RFollowDaoImpl addfollowing start..");
		System.out.println("RFollowDaoImpl addfollowing map -> "+map);
		int result = 0;
		try {
			result = session.insert("rightaddfollowing", map);
		} catch (Exception e) {
			System.out.println("RFollowDaoImpl addfollowing Exception->"+e.getMessage());
		}
		return result;
	}
	//팔로워 차단 조회
	@Override
	public List<RFollow> selectBlockFollower(int ucode) {
		System.out.println("RFollowDaoImpl List<RFollow> selectBlockFollower start..");
		List<RFollow> followerBlockList = new ArrayList<RFollow>();
		try {
			followerBlockList = session.selectList("rightselectblockFollower", ucode);
		} catch (Exception e) {
			System.out.println("RFollowDaoImpl List<RFollow> selectBlockFollower Exception->"+e.getMessage());
		}

		return followerBlockList;
	}
	//팔로워 차단당한거 조회
	@Override
	public List<RFollow> selectBlockFollowing(int ucode) {
		System.out.println("RFollowDaoImpl List<RFollow> selectBlockFollowing start..");
		List<RFollow> followingBlockList = new ArrayList<RFollow>();
		try {
			followingBlockList = session.selectList("rightselectblockFollowing", ucode);
		} catch (Exception e) {
			System.out.println("RFollowDaoImpl List<RFollow> selectBlockFollowing Exception->"+e.getMessage());
		}

		return followingBlockList;
	}

}