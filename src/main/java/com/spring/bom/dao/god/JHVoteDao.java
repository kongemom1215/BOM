package com.spring.bom.dao.god;

import com.spring.bom.model.god.JHVote;

public interface JHVoteDao {

	String settingTime(JHVote vote);

	int insertVote(JHVote vote);

}
