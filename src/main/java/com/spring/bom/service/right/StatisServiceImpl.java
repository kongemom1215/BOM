package com.spring.bom.service.right;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.right.StatisDao;
import com.spring.bom.model.right.Statis;

@Service
public class StatisServiceImpl implements StatisService {
	@Autowired
	private StatisDao sd;
	//10대 통계 가져오기
	@Override
	public List<Statis> getSearchRank1() {
		System.out.println("StatisServiceImpl getSearchRank1() ...");
		return sd.getSearchRank1();
	}
	//20대 통계 가져오기
	@Override
	public List<Statis> getSearchRank2() {
		System.out.println("StatisServiceImpl getSearchRank2 ...");
		return sd.getSearchRank2();
	}
	//30대 통계 가져오기
	@Override
	public List<Statis> getSearchRank3() {
		System.out.println("StatisServiceImpl getSearchRank3 ...");
		return sd.getSearchRank3();
	}
	//남자 통계 가져오기
	@Override
	public List<Statis> getSearchRankm() {
		System.out.println("StatisServiceImpl getSearchRankm ...");
		return sd.getSearchRankm();
	}
	//여자 통계 가져오기
	@Override
	public List<Statis> getSearchRankw() {
		System.out.println("StatisServiceImpl getSearchRankw ...");
		return sd.getSearchRankw();
	}

	
}
