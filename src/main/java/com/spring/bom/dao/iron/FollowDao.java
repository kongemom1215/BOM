package com.spring.bom.dao.iron;

import java.util.List;

import com.spring.bom.model.iron.Follow;

public interface FollowDao {

	List<Follow> getSuggestFollowList1(int ucode);	//관심사가 겹치는 유저 추천

	List<Follow> getSuggestFollowList2(int ucode);	//나를 팔로우하는 유저 추천

	int fwInsert(Follow follow);

}
