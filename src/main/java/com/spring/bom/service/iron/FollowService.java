package com.spring.bom.service.iron;

import java.util.List;

import com.spring.bom.model.iron.Follow;

public interface FollowService {

	List<Follow> getSuggestFollowList(int ucode);

}
