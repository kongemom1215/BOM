package com.spring.bom.dao.iron;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.iron.Board;
import com.spring.bom.model.yeah.UserBookmarkBoard;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession session;

	@Override
	public List<Board> getTimelineBoard(int ucode) {

		System.out.println("[iron] BoardDaoImpl getTimelineBoard start...");
		List<Board> bdlist = null;
		try {
			bdlist = session.selectList("timelineBoardList", ucode);
			System.out.println("[iron] BoardDaoImpl bdlist.size() -> " + bdlist.size());
			/*
			 * <a href="#"></a> for (int i = 0; i < bdlist.size(); i++) { String hbcontent =
			 * bdlist.get(i).getbcontent(); if (hbcontent.contains(" #") ||
			 * hbcontent.contains("@")) //check content # or @ exist continue; //if exist
			 * StringBuffer temp; // save keyword
			 * 
			 * String hashTagStart = "<a href='#'>"; String atTagStart = "<a href='@'>";
			 * String atagEnd = "</a>"; for (int point = 0; point < hbcontent.length();
			 * point++) { if (hbcontent.charAt(point) == '#') { for(int check =
			 * point;check<hbcontent.length();check++) { if(hbcontent.charAt(check)=='\n' ||
			 * hbcontent.charAt(check)==' ') { hbcontent.substring(point,check); } } } else
			 * if (hbcontent.charAt(point) == '@') {
			 * 
			 * } }
			 * 
			 * }
			 */
			// 인용데이터 처리
			for (int i = 0; i < bdlist.size(); i++) {
				if (bdlist.get(i).getBtype().equals("quote")) {
					int code=Integer.parseInt(bdlist.get(i).getBbcode());
					
					Board quoteboard = session.selectOne("Iron_QuoteBoard", bdlist.get(i).getBbcode());
					bdlist.get(i).setQ_uimage(quoteboard.getUimage());
					bdlist.get(i).setQ_nickname(quoteboard.getUnickName());
					bdlist.get(i).setQ_atid(quoteboard.getUatid());
					bdlist.get(i).setQ_content(quoteboard.getBcontent());
					bdlist.get(i).setQ_regdate(quoteboard.getBregDate());

					if (quoteboard.getBattach() != null) {
						bdlist.get(i).setQ_attachsrc(quoteboard.getBattach().substring(6));
						bdlist.get(i).setQ_attachtype(quoteboard.getBattach().substring(0, 5));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("getTimeLine Lists e.Message() -> " + e.getMessage());
		}
		return bdlist;
	}

	@Override
	public Board getSingleBoard(Board board) {
		System.out.println("[iron] BaordDaoImpl getTimelineBoard start...");
		try {
			board = session.selectOne("SingleBoard", board);
			System.out.println("[iron] User_InfoDaoImpl bLikeCount -> " + board.getBlikeCount());
			if (board.getBtype().equals("quote")) {
				Board quoteboard = session.selectOne("QuoteBoard", board.getBbcode());
				System.out.println("QuoteBoard--bdlist.get(i).getBbcode() --> " + board.getBbcode());
				System.out.println("쿼트문 실행");
				board.setQ_uimage(quoteboard.getUimage());
				System.out.println("quoteboard.getUimage() -> " + quoteboard.getUimage());
				board.setQ_nickname(quoteboard.getUnickName());
				board.setQ_atid(quoteboard.getUatid());
				board.setQ_content(quoteboard.getBcontent());
				board.setQ_regdate(quoteboard.getBregDate());

				if (quoteboard.getBattach() != null) {
					System.out.println("quoteboard.getBattach() -> " + quoteboard.getBattach());
					board.setQ_attachsrc(quoteboard.getBattach().substring(6));
					board.setQ_attachtype(quoteboard.getBattach().substring(0, 5));
					System.out
							.println("quoteboard.getBattach().substring(6) -> " + quoteboard.getBattach().substring(6));
					System.out.println(
							"quoteboard.getBattach().substring(0, 5) -> " + quoteboard.getBattach().substring(0, 5));
				}
			}
		} catch (

		Exception e) {
			System.out.println("getTimeLine Lists e.Message() -> " + e.getMessage());
		}
		return board;

	}

	@Override
	public List<Board> getReplyList(int bcode) {
		System.out.println("[iron] BoardDaoImpl getReplyList start...");
		List<Board> list = null;
		try {
			list = session.selectList("ReplyList", bcode);
			System.out.println("[iron] replylist.size() -> " + list.size());

			// 인용데이터 처리
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getBtype().equals("quote")) {
					Board quoteboard = session.selectOne("QuoteBoard", list.get(i).getBbcode());
					System.out.println("QuoteBoard--bdlist.get(i).getBbcode() --> " + list.get(i).getBbcode());
					System.out.println("쿼트문 실행");
					list.get(i).setQ_uimage(quoteboard.getUimage());
					System.out.println("quoteboard.getUimage() -> " + quoteboard.getUimage());
					list.get(i).setQ_nickname(quoteboard.getUnickName());
					list.get(i).setQ_atid(quoteboard.getUatid());
					list.get(i).setQ_content(quoteboard.getBcontent());
					list.get(i).setQ_regdate(quoteboard.getBregDate());

					if (quoteboard.getBattach() != null) {
						System.out.println("quoteboard.getBattach() -> " + quoteboard.getBattach());
						list.get(i).setQ_attachsrc(quoteboard.getBattach().substring(6));
						list.get(i).setQ_attachtype(quoteboard.getBattach().substring(0, 5));
						System.out.println(
								"quoteboard.getBattach().substring(6) -> " + quoteboard.getBattach().substring(6));
						System.out.println("quoteboard.getBattach().substring(0, 5) -> "
								+ quoteboard.getBattach().substring(0, 5));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("getReplyList e.getMessage -> " + e.getMessage());
		}
		return list;
	}

	@Override
	public List<Board> getMyBoardList(Board myboard) {
		System.out.println("[iron] BoardDaoImpl getMyBoardList start...");
		List<Board> list = null;
		try {

			list = session.selectList("MyBoardList", myboard);
			System.out.println("[iron] BoardDaoImpl list.size() -> " + list.size());

			// 인용데이터 처리
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getBtype().equals("quote")) {
					Board quoteboard = session.selectOne("QuoteBoard", list.get(i).getBbcode());
					System.out.println("QuoteBoard--bdlist.get(i).getBbcode() --> " + list.get(i).getBbcode());
					System.out.println("쿼트문 실행");
					list.get(i).setQ_uimage(quoteboard.getUimage());
					System.out.println("quoteboard.getUimage() -> " + quoteboard.getUimage());
					list.get(i).setQ_nickname(quoteboard.getUnickName());
					list.get(i).setQ_atid(quoteboard.getUatid());
					list.get(i).setQ_content(quoteboard.getBcontent());
					list.get(i).setQ_regdate(quoteboard.getBregDate());

					if (quoteboard.getBattach() != null) {
						System.out.println("quoteboard.getBattach() -> " + quoteboard.getBattach());
						list.get(i).setQ_attachsrc(quoteboard.getBattach().substring(6));
						list.get(i).setQ_attachtype(quoteboard.getBattach().substring(0, 5));
						System.out.println(
								"quoteboard.getBattach().substring(6) -> " + quoteboard.getBattach().substring(6));
						System.out.println("quoteboard.getBattach().substring(0, 5) -> "
								+ quoteboard.getBattach().substring(0, 5));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("getMyBoardList e.getMessage() -> " + e.getMessage());
		}
		return list;
	}

	@Override
	public List<Board> getMyReplyBoardList(Board myReplyBoard) {
		System.out.println("[iron] BoardDaoImpl getMyReplyBoardList start...");
		List<Board> list = null;
		try {
			list = session.selectList("MyReplyBoardList", myReplyBoard);
			System.out.println("[iron] BoardDaoImpl list.size() -> " + list.size());

			// 인용데이터 처리
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getBtype().equals("quote")) {
					Board quoteboard = session.selectOne("QuoteBoard", list.get(i).getBbcode());
					System.out.println("QuoteBoard--bdlist.get(i).getBbcode() --> " + list.get(i).getBbcode());
					System.out.println("쿼트문 실행");
					list.get(i).setQ_uimage(quoteboard.getUimage());
					System.out.println("quoteboard.getUimage() -> " + quoteboard.getUimage());
					list.get(i).setQ_nickname(quoteboard.getUnickName());
					list.get(i).setQ_atid(quoteboard.getUatid());
					list.get(i).setQ_content(quoteboard.getBcontent());
					list.get(i).setQ_regdate(quoteboard.getBregDate());

					if (quoteboard.getBattach() != null) {
						System.out.println("quoteboard.getBattach() -> " + quoteboard.getBattach());
						list.get(i).setQ_attachsrc(quoteboard.getBattach().substring(6));
						list.get(i).setQ_attachtype(quoteboard.getBattach().substring(0, 5));
						System.out.println(
								"quoteboard.getBattach().substring(6) -> " + quoteboard.getBattach().substring(6));
						System.out.println("quoteboard.getBattach().substring(0, 5) -> "
								+ quoteboard.getBattach().substring(0, 5));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("getMyReplyBoardList e.getMessage() -> " + e.getMessage());
		}
		return list;
	}

	@Override
	public List<Board> getMyMediaBoardList(Board myMediaBoard) {
		System.out.println("[iron] BoardDaoImpl getMyMediaBoardList start...");
		List<Board> list = null;
		try {
			list = session.selectList("MyMediaBoardList", myMediaBoard);
			System.out.println("[iron] BoardDaoImpl list.size() -> " + list.size());

			// 인용데이터 처리
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getBtype().equals("quote")) {
					Board quoteboard = session.selectOne("QuoteBoard", list.get(i).getBbcode());
					System.out.println("QuoteBoard--bdlist.get(i).getBbcode() --> " + list.get(i).getBbcode());
					System.out.println("쿼트문 실행");
					list.get(i).setQ_uimage(quoteboard.getUimage());
					System.out.println("quoteboard.getUimage() -> " + quoteboard.getUimage());
					list.get(i).setQ_nickname(quoteboard.getUnickName());
					list.get(i).setQ_atid(quoteboard.getUatid());
					list.get(i).setQ_content(quoteboard.getBcontent());
					list.get(i).setQ_regdate(quoteboard.getBregDate());

					if (quoteboard.getBattach() != null) {
						System.out.println("quoteboard.getBattach() -> " + quoteboard.getBattach());
						list.get(i).setQ_attachsrc(quoteboard.getBattach().substring(6));
						list.get(i).setQ_attachtype(quoteboard.getBattach().substring(0, 5));
						System.out.println(
								"quoteboard.getBattach().substring(6) -> " + quoteboard.getBattach().substring(6));
						System.out.println("quoteboard.getBattach().substring(0, 5) -> "
								+ quoteboard.getBattach().substring(0, 5));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("getMyMediaBoardList e.getMessage() -> " + e.getMessage());
		}
		return list;
	}

	@Override
	public List<Board> getMyLikeBoardList(Board myLikeBoard) {
		System.out.println("[iron] BoardDaoImpl getMyLikeBoardList start...");
		List<Board> list = null;
		try {
			list = session.selectList("MyLikeBoardList", myLikeBoard);
			System.out.println("[iron] BoardDaoImpl list.size() -> " + list.size());

			// 인용데이터 처리
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getBtype().equals("quote")) {
					Board quoteboard = session.selectOne("QuoteBoard", list.get(i).getBbcode());
					System.out.println("QuoteBoard--bdlist.get(i).getBbcode() --> " + list.get(i).getBbcode());
					System.out.println("쿼트문 실행");
					list.get(i).setQ_uimage(quoteboard.getUimage());
					System.out.println("quoteboard.getUimage() -> " + quoteboard.getUimage());
					list.get(i).setQ_nickname(quoteboard.getUnickName());
					list.get(i).setQ_atid(quoteboard.getUatid());
					list.get(i).setQ_content(quoteboard.getBcontent());
					list.get(i).setQ_regdate(quoteboard.getBregDate());

					if (quoteboard.getBattach() != null) {
						System.out.println("quoteboard.getBattach() -> " + quoteboard.getBattach());
						list.get(i).setQ_attachsrc(quoteboard.getBattach().substring(6));
						list.get(i).setQ_attachtype(quoteboard.getBattach().substring(0, 5));
						System.out.println(
								"quoteboard.getBattach().substring(6) -> " + quoteboard.getBattach().substring(6));
						System.out.println("quoteboard.getBattach().substring(0, 5) -> "
								+ quoteboard.getBattach().substring(0, 5));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("getMyLikeBoardList e.getMessage() -> " + e.getMessage());
		}
		return list;
	}
}
