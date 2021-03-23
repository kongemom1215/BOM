package com.spring.bom.service.right;

import java.util.List;

import com.spring.bom.model.right.RFollow;

public interface RFollowService {
	// session ucode 현재 로그인 되어있는 계정의 팔로우/팔로워 List & 차단 List
	//팔로워 조회
	List<RFollow> selectFollower(int ucode);
	//팔로잉 조회
	List<RFollow> selectFollowing(int ucode);
	//팔로워 차단 조회
	List<RFollow> selectBlockFollower(int ucode);
	//팔로잉 차단당한거 조회
	List<RFollow> selectBlockFollowing(int ucode);
	
	// 프로필 uatid 계정의 팔로우/팔로워 List & 차단 List
	//팔로워 조회
	List<RFollow> selectFollower_p(String uatid);
	//팔로잉 조회
	List<RFollow> selectFollowing_p(String uatid);
	//팔로워 차단 조회
	List<RFollow> selectBlockFollower_p(String uatid);
	//팔로잉 차단당한거 조회
	List<RFollow> selectBlockFollowing_p(String uatid);	
	
	//팔로잉 취소
	int deleteFollowing(int ucode, int fopcode);
	//팔로우 팔로잉 추가
	int addfollowing(int ucode, int fopcode);
	
	

}