package com.spring.bom.dao.god;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.god.JHBoard;

@Repository
public class JHBoardDaoImpl implements JHBoardDao {
	@Autowired
	private SqlSession session;

	@Override
	// 일반 글 삽입
	public int insertBoard(JHBoard board) {
		int result = 0;
		try {
			System.out.println("board.getBsaveorrsvd() -> " + board.getBsaveorrsvd());
			// 저장글이면
			if (board.getBsaveorrsvd() == 0) {
				System.out.println("저장 글 등록");
				result = session.insert("JHinsertSaveBoard", board); // 저장글이면 bregdate=null, bsaveorrsvd=0
			}
			// 예약글이면
			else if (board.getBsaveorrsvd() == 1) {
				System.out.println("예약 글 등록");
				// 예약글을 등록할경우
				if (!board.getSavebcode().equals("")) {
					result = session.update("JHinsertSaveRsvdBoard1", board);
				} else
					result = session.insert("JHinsertRsvdBoard", board); // 예약글이면 bregdate 있고, bsaveorrsvd=1
			}
			// 일반글이면
			else {
				System.out.println("board.getsavebcode() -> " + board.getSavebcode());
				// 저장글 등록할경우
				if (!board.getSavebcode().equals("")) {
					System.out.println("저장,예약글을 일반글로 등록");
					result = session.update("JHinsertSaveRsvdBoard2", board);
				} else {
					System.out.println("일반글 등록");
					result = session.insert("JHinsertBoard", board); // 일반글이면 bregdate 있고, bsaveorrsvd=null
				}
			}
		} catch (Exception e) {
			System.out.println("GOD BoardDaoImpl insertBoard -> " + e.getMessage());
		}
		return result;
	}

	@Override
	// 투표있는 글 삽입
	public int insertVoteBoard(JHBoard board) {
		int result = 0;
		try {
			System.out.println("일반글 등록");
			result = session.insert("JHinsertVoteBoard", board); // 일반글이면 bregdate 있고, bsaveorrsvd=null
		} catch (Exception e) {
			System.out.println("GOD BoardDaoImpl insertVoteBoard -> " + e.getMessage());
		}
		return result;
	}

	@Override
	public JHBoard getBoard(int bcode) {
		JHBoard board = new JHBoard();
		try {
			board = session.selectOne("JHgetBoard", bcode);
		} catch (Exception e) {
			System.out.println("GOD BoardDaoImpl getBoard -> " + e.getMessage());
		}
		return board;
	}

	@Override
	public int deleteSaveWrite(int[] bcodes) {
		int result = 0;
		Map<String, Object> param = new HashMap<String, Object>();
		List<Integer> arrayList = new ArrayList<Integer>();
		for (int temp : bcodes) {
			arrayList.add(temp);
		}
		param.put("arraylist", arrayList);
		try {
			result = session.update("JHdeleteSaveWrite", param);
		} catch (Exception e) {
			System.out.println("GOD BoardDaoImpl deleteSaveWrite -> " + e.getMessage());
		}

		return result;
	}

}
