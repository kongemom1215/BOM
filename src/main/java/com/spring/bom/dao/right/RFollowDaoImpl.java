package com.spring.bom.dao.right;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.right.RFollow;

@Repository
public class RFollowDaoImpl implements RFollowDao {
	@Autowired
	private SqlSession session;
	
	//session ucode
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
	
	//RequestParam uatid
	@Override
	public List<RFollow> selectFollower_p(String uatid) {
		System.out.println("RFollowDaoImpl List<RFollow> selectFollower_p start..");
		List<RFollow> pfollowerList = new ArrayList<RFollow>();
		try {
			pfollowerList = session.selectList("rightselectprofileFollower", uatid);
		} catch (Exception e) {
			System.out.println("RFollowDaoImpl List<RFollow> selectFollower_p Exception->"+e.getMessage());
		}

		return pfollowerList;
	}
	@Override
	public List<RFollow> selectFollowing_p(String uatid) {
		System.out.println("RFollowDaoImpl List<RFollow> selectFollowing_p start..");
		List<RFollow> pfollowingList = new ArrayList<RFollow>();
		try {
			System.out.println("#RFollowDaoImpl List<RFollow> selectFollowing_p select 전 start..");
			pfollowingList = session.selectList("rightselectprofileFollowing", uatid);
			System.out.println("##RFollowDaoImpl List<RFollow> selectFollowing_p select 후 start..");
		} catch (Exception e) {
			System.out.println("RFollowDaoImpl List<RFollow> selectFollowing_p Exception->"+e.getMessage());
		}
		System.out.println("RFollowDaoImpl pfollowingList : "+pfollowingList);
		return pfollowingList;
	}
	@Override
	public List<RFollow> selectBlockFollower_p(String uatid) {
		System.out.println("RFollowDaoImpl List<RFollow> selectBlockFollower_p start..");
		List<RFollow> pfollowerBlockList = new ArrayList<RFollow>();
		try {
			pfollowerBlockList = session.selectList("rightselectprofileFollowerBlock", uatid);
		} catch (Exception e) {
			System.out.println("RFollowDaoImpl List<RFollow> selectBlockFollower_p Exception->"+e.getMessage());
		}

		return pfollowerBlockList;
	}
	@Override
	public List<RFollow> selectBlockFollowing_p(String uatid) {
		System.out.println("RFollowDaoImpl List<RFollow> selectBlockFollowing_p start..");
		List<RFollow> pfollowingBlockList = new ArrayList<RFollow>();
		try {
			pfollowingBlockList = session.selectList("rightselectprofileFollowingBlock", uatid);
		} catch (Exception e) {
			System.out.println("RFollowDaoImpl List<RFollow> selectBlockFollowing_p Exception->"+e.getMessage());
		}

		return pfollowingBlockList;
	}
	
	

}