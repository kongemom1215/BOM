package com.spring.bom.service.god;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.god.JHHashtagDao;
import com.spring.bom.model.god.JHHashtag;

@Service
public class JHHashtagServiceImpl implements JHHashtagService {
	@Autowired
	private JHHashtagDao hd;

	@Override
	public int insertHash(ArrayList<String> hashlist, JHHashtag hashtag) {
		// TODO Auto-generated method stub
		return hd.insertHash(hashlist, hashtag);
	}
}
