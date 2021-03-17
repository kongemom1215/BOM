package com.spring.bom.dao.right;

import java.util.List;

import com.spring.bom.model.right.Statis;

public interface StatisDao {
		//10대 통계 가져오기
		List<Statis> getSearchRank1();
		//20대 통계 가져오기
		List<Statis> getSearchRank2();
		//30대 통계 가져오기
		List<Statis> getSearchRank3();
		//남자 통계 가져오기
		List<Statis> getSearchRankm();
		//여자 통계 가져오기
		List<Statis> getSearchRankw();
}
