package com.spring.bom.service.god;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.god.JHBoardDao;
import com.spring.bom.model.god.JHBoard;

@Service
public class JHBoardServiceImpl implements JHBoardService {
	@Autowired
	private JHBoardDao bd;
	
	@Override
	public int insertBoard(JHBoard board) {
		// TODO Auto-generated method stub
		System.out.println("service -> board.getsavebcode() -> "+board.getSavebcode());
		return bd.insertBoard(board);
	}

	@Override
	public int insertVoteBoard(JHBoard board) {
		// TODO Auto-generated method stub
		return bd.insertVoteBoard(board);
	}

	@Override
	public JHBoard getBoard(int bcode) {
		// TODO Auto-generated method stub
		return bd.getBoard(bcode);
	}

	@Override
	public int deleteSaveWrite(int[] bcodes) {
		// TODO Auto-generated method stub
		return bd.deleteSaveWrite(bcodes);
	}

}
