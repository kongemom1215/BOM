package com.spring.bom.service.iron;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.iron.BoardDao;
import com.spring.bom.model.iron.Board;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao bd;

	@Override
	public List<Board> getTimelineBoard(int ucode) {
		System.out.println("[iron] BoardServiceImpl getTimelineBoard start...");
		List<Board> bdlist = bd.getTimelineBoard(ucode); 
		System.out.println("[iron] bdlist.size() -> "+bdlist.size());
		return bdlist;
	}

	@Override
	public Board getSingleBoard(Board board) {
		System.out.println("[iron] BoardServiceImpl getSingleBoard start...");
		return bd.getSingleBoard(board);
	}

	@Override
	public List<Board> getReplyList(int bcode) {
		System.out.println("[iron] BoardServiceImpl getReplyList start...");
		return bd.getReplyList(bcode);
	}

}
