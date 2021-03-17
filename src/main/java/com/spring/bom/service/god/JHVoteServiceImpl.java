package com.spring.bom.service.god;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.god.JHVoteDao;
import com.spring.bom.model.god.JHVote;

@Service
public class JHVoteServiceImpl implements JHVoteService {
	@Autowired
	private JHVoteDao vd;
	@Override
	public String settingTime(JHVote vote) {
		return vd.settingTime(vote);
	}
	@Override
	public int insertVote(JHVote vote) {
		// TODO Auto-generated method stub
		return vd.insertVote(vote);
	}

}
