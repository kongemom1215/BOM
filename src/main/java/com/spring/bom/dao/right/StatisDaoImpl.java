package com.spring.bom.dao.right;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.right.Statis;


@Repository
public class StatisDaoImpl implements StatisDao {
	// Mybatis 세션 
	@Autowired
	private SqlSession session;

	//10대 통계 가져오기
	@Override
	public List<Statis> getSearchRank1() {
		System.out.println("StatisDaoImpl List<Statis> getSearchRank1 start..");
		List<Statis> aList1 = new ArrayList<Statis>();
		try {
			aList1 = session.selectList("rightSearch1");
		} catch (Exception e) {
			System.out.println("StatisDaoImpl List<Statis> getSearchRank1 Exception->"+e.getMessage());
		}
		return aList1;
	}
	//20대 통계 가져오기
	@Override
	public List<Statis> getSearchRank2() {
		System.out.println("StatisDaoImpl List<Statis> getSearchRank2 start..");
		List<Statis> aList2 = new ArrayList<Statis>();
		try {
			aList2 = session.selectList("rightSearch2");
		} catch (Exception e) {
			System.out.println("StatisDaoImpl List<Statis> getSearchRank2 Exception->"+e.getMessage());
		}
		return aList2;
	}
	//30대 통계 가져오기
	@Override
	public List<Statis> getSearchRank3() {
		System.out.println("StatisDaoImpl List<Statis> getSearchRank3 start..");
		List<Statis> aList3 = new ArrayList<Statis>();
		try {
			aList3 = session.selectList("rightSearch3");
		} catch (Exception e) {
			System.out.println("StatisDaoImpl List<Statis> getSearchRank3 Exception->"+e.getMessage());
		}
		return aList3;
	}
	//남자 통계 가져오기
	@Override
	public List<Statis> getSearchRankm() {
		System.out.println("StatisDaoImpl List<Statis> getSearchRankm start..");
		List<Statis> gListm = new ArrayList<Statis>();
		try {
			gListm = session.selectList("rightSearchm");
		} catch (Exception e) {
			System.out.println("StatisDaoImpl List<Statis> getSearchRankm Exception->"+e.getMessage());
		}
		return gListm;
	}
	//여자 통계 가져오기
	@Override
	public List<Statis> getSearchRankw() {
		System.out.println("StatisDaoImpl List<Statis> getSearchRankw start..");
		List<Statis> gListw = new ArrayList<Statis>();
		try {
			gListw = session.selectList("rightSearchw");
		} catch (Exception e) {
			System.out.println("StatisDaoImpl List<Statis> getSearchRankw Exception->"+e.getMessage());
		}
		return gListw;
	}

	
}
