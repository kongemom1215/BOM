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
		System.out.println("[iron] BoardDaoImpl getTimelineBoard start...");
		List<Board> bdlist = session.selectList("timelineBoardList", ucode);
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
		return bdlist;
	}

	@Override
	public Board getSingleBoard(Board board) {
		System.out.println("[iron] BaordDaoImpl getTimelineBoard start...");
		board = session.selectOne("SingleBoard", board);
		System.out.println("[iron] User_InfoDaoImpl bLikeCount -> " + board.getBlikeCount());
		return board;
	}

	@Override
	public List<Board> getReplyList(int bcode) {
		System.out.println("[iron] BoardDaoImpl getReplyList start...");
		List<Board> list = session.selectList("ReplyList", bcode);
		System.out.println("[iron] replylist.size() -> " + list.size());
		return list;
	}

	
}
