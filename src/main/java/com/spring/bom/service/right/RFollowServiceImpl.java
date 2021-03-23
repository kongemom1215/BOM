package com.spring.bom.service.right;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.right.RFollowDao;
import com.spring.bom.model.right.RFollow;

@Service
public class RFollowServiceImpl implements RFollowService {
	@Autowired
	private RFollowDao fd;
	
	//session ucode --> 팔로우 팔로잉 바꿔서 조회함
	//팔로잉 조회
	@Override
	public List<RFollow> selectFollower(int ucode) {
		System.out.println("RFollowServiceImpl selectFollower start");
		return fd.selectFollower(ucode);
	}
	//팔로워 조회
	@Override
	public List<RFollow> selectFollowing(int ucode) {
		System.out.println("RFollowServiceImpl selectFollowing start");
		return fd.selectFollowing(ucode);
	}
	//팔로잉 취소
	@Override
	public int deleteFollowing(int ucode, int fopcode) {
		System.out.println("RFollowServiceImpl deleteFollowing start");
		return fd.deleteFollowing(ucode, fopcode);
	}
	//팔로우 팔로잉 추가
	@Override
	public int addfollowing(int ucode, int fopcode) {
		System.out.println("RFollowServiceImpl addfollowing start");
		return fd.addfollowing(ucode, fopcode);
	}
	//팔로워 차단 조회
	@Override
	public List<RFollow> selectBlockFollower(int ucode) {
		System.out.println("RFollowServiceImpl selectBlockFollower start");
		return fd.selectBlockFollower(ucode);
	}
	//팔로워 차단당한거 조회
	@Override
	public List<RFollow> selectBlockFollowing(int ucode) {
		System.out.println("RFollowServiceImpl selectBlockFollowing start");
		return fd.selectBlockFollowing(ucode);
	}
	
	//RequestParam uatid
	@Override
	public List<RFollow> selectFollower_p(String uatid) {
		System.out.println("RFollowServiceImpl selectFollower_p start");
		return fd.selectFollower_p(uatid);
	}
	@Override
	public List<RFollow> selectFollowing_p(String uatid) {
		System.out.println("RFollowServiceImpl selectFollowing_p start");
		return fd.selectFollowing_p(uatid);
	}
	@Override
	public List<RFollow> selectBlockFollower_p(String uatid) {
		System.out.println("RFollowServiceImpl selectBlockFollower_p start");
		return fd.selectBlockFollower_p(uatid);
	}
	@Override
	public List<RFollow> selectBlockFollowing_p(String uatid) {
		System.out.println("RFollowServiceImpl selectBlockFollowing_p start");
		return fd.selectBlockFollowing_p(uatid);
	}
	

	
	

}