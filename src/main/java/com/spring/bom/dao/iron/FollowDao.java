package com.spring.bom.dao.iron;

import java.util.List;

import com.spring.bom.model.iron.Follow;

public interface FollowDao {

	List<Follow> getSuggestFollowList(int ucode);

}
