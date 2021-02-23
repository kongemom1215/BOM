package com.spring.bom.dao.iron;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.iron.Board;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<Board> getTimelineBoard(int ucode) {
		System.out.println("[iron] BaordDaoImpl getTimelineBoard start...");
		List<Board> bdlist = session.selectList("timelineBoardList", ucode);
		System.out.println("[iron] User_InfoDaoImpl bdlist.size() -> "+bdlist.size());
		return bdlist;
	}

}
