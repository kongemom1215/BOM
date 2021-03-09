package com.spring.bom.service.iron;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.iron.HashTagDao;
import com.spring.bom.model.iron.HashTag;

@Service
public class HashTagServiceImpl implements HashTagService {
	
	@Autowired
	private HashTagDao hd;
	
	@Override
	public List<HashTag> getHashTagRanking() {
		System.out.println("[iron] HashTagServiceImpl getHashTagRanking start...");
		return hd.getHashTagRanking();
	}

}
