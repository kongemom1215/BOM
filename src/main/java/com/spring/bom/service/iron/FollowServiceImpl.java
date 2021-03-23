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
	public List<Follow> getSuggestFollowList1(int ucode) {
		System.out.println("[iron] FollowServiceImpl getSuggestFollowList1 start... ");
		List<Follow> suggestFlist = fd.getSuggestFollowList1(ucode);
		System.out.println("[iron] suggestFlist.size() -> "+suggestFlist.size());
		return suggestFlist;
	}
	
	@Override
	public List<Follow> getSuggestFollowList2(int ucode) {
		System.out.println("[iron] FollowServiceImpl getSuggestFollowList2 start... ");
		List<Follow> suggestFlist = fd.getSuggestFollowList2(ucode);
		System.out.println("[iron] suggestFlist.size() -> "+suggestFlist.size());
		return suggestFlist;
	}
	
	@Override
	public int fwInsert(Follow follow) {
		System.out.println("[kiwoong] FollowServiceImpl FwInsert");
		
		return fd.fwInsert(follow);
	}
	
	@Override
	public int unfollow(Follow follow) {
		System.out.println("[kiwoong] FollowServiceImpl unfollow");
		return fd.unfollow(follow);
	}
}
