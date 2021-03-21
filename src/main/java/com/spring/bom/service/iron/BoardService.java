package com.spring.bom.service.iron;

import java.util.List;

import com.spring.bom.model.iron.Board;

public interface BoardService {

	List<Board> getTimelineBoard(int ucode);

	Board getSingleBoard(Board board);

	List<Board> getReplyList(int bcode);

	List<Board> getMyBoardList(Board myboard);

	List<Board> getMyReplyBoardList(Board myReplyBoard);

	List<Board> getMyMediaBoardList(Board myMediaBoard);

	List<Board> getMyLikeBoardList(Board myLikeBoard);

}
