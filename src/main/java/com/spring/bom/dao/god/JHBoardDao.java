package com.spring.bom.dao.god;

import com.spring.bom.model.god.JHBoard;

public interface JHBoardDao {

	int insertBoard(JHBoard board);

	int insertVoteBoard(JHBoard board);

	JHBoard getBoard(int bcode);

	int deleteSaveWrite(int[] bcodes);

}
