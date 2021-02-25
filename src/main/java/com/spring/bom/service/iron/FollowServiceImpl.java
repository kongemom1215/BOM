package com.spring.bom.service.iron;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.iron.FollowDao;
import com.spring.bom.model.iron.Follow;

@Service
public class FollowServiceImpl implements FollowService {
	@Autowired
	private FollowDao fd;
	@Override
	public List<Follow> getSuggestFollowList(int ucode) {
		System.out.println("[iron] FollowServiceImpl getSuggestFollowList start... ");
		List<Follow> suggestFlist = fd.getSuggestFollowList(ucode);
		System.out.println("[iron] suggestFlist.size() -> "+suggestFlist.size());
		return suggestFlist;
	}

}
