package com.spring.bom.service.god;

import org.springframework.stereotype.Service;

import com.spring.bom.model.god.JHBoard;

@Service
public interface JHBoardService {

	int insertBoard(JHBoard board);

	int insertVoteBoard(JHBoard board);

	JHBoard getBoard(int bcode);

	int deleteSaveWrite(int[] bcodes);

}
