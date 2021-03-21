package com.spring.bom.service.right;

import java.util.List;

import com.spring.bom.model.right.RFollow;

public interface RFollowService {
	
	//팔로워 조회
	List<RFollow> selectFollower(int ucode);

	//팔로잉 조회
	List<RFollow> selectFollowing(int ucode);
	
	//팔로잉 취소
	int deleteFollowing(int ucode, int fopcode);
	
	//팔로우 팔로잉 추가
	int addfollowing(int ucode, int fopcode);
	
	//팔로워 차단 조회
	List<RFollow> selectBlockFollower(int ucode);
	//팔로잉 차단당한거 조회
	List<RFollow> selectBlockFollowing(int ucode);

}
