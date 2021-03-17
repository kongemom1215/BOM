package com.spring.bom.dao.god;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.god.JHVote;

@Repository
public class JHVoteDaoImpl implements JHVoteDao {
	@Autowired
	private SqlSession session;

	@Override
	public String settingTime(JHVote vote) {
		String votetime=null;
		try {
			votetime=session.selectOne("JHsetVoteTime",vote);
		} catch (Exception e) {
			System.out.println("GOD VoteDaoImpl settingTime -> "+e.getMessage());
		}
		return votetime;
	}

	@Override
	public int insertVote(JHVote vote) {
		try {
			session.insert("JHinsertVote", vote);
		} catch (Exception e) {
			System.out.println("GOD VoteDaoImpl insertVote -> "+e.getMessage());
		}
		return vote.getVcode();
	}
}
