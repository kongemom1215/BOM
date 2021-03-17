package com.spring.bom.service.god;

import com.spring.bom.model.god.JHVote;

public interface JHVoteService {

	String settingTime(JHVote vote);

	int insertVote(JHVote vote);

}
