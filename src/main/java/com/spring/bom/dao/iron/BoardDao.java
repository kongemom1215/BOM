package com.spring.bom.dao.iron;

import java.util.List;

import com.spring.bom.model.iron.Board;

public interface BoardDao {

	List<Board> getTimelineBoard(int ucode);

	Board getSingleBoard(Board board);

	List<Board> getReplyList(int bcode);

}
