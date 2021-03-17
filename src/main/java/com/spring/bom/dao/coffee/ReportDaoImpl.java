package com.spring.bom.dao.coffee;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.coffee.ReportUser_infoBoard;

@Repository
public class ReportDaoImpl implements ReportDao {
	@Autowired
	private  SqlSession  session;

	@Override
	public List<ReportUser_infoBoard> accusationList() {
		List<ReportUser_infoBoard> list = null;
		System.out.println("ReportDaoImpl accusationList start..");

		try {
			list = session.selectList("accusationSelectReportUser_infoBoard");
			for(int i=0; i<list.size();i++) {
				list.get(i).setUfollowing(session.selectOne("coffeeSensorFollowingReport", list.get(i).getRucode()));
				list.get(i).setUfollower(session.selectOne("coffeeSensorFollowerReport", list.get(i).getRucode()));
				
			}
		}catch (Exception e) {
			System.out.println("ReportDaoImpl accusationList ->"+e.getMessage());
		}
		
		
		return list;
	}

	@Override
	public List<ReportUser_infoBoard> uncensoredList() {
		List<ReportUser_infoBoard> list = null;
		System.out.println("ReportDaoImpl uncensoredList start..");

		try {
			list = session.selectList("accusationSelectUncensoredReportUser_infoBoard");
			for(int i=0; i<list.size();i++) {
				list.get(i).setUfollowing(session.selectOne("coffeeSensorFollowingReport", list.get(i).getRucode()));
				list.get(i).setUfollower(session.selectOne("coffeeSensorFollowerReport", list.get(i).getRucode()));
				
			}
		}catch (Exception e) {
			System.out.println("ReportDaoImpl uncensoredList ->"+e.getMessage());
		}
		return list;
	}

	@Override
	public int updateRaction(int rcode, int updateValue) {
		int result = 0;
		ReportUser_infoBoard ruib = new ReportUser_infoBoard();
		ruib.setRcode(rcode);
		ruib.setRaction(updateValue);
		try{
			result = session.update("coffeeUpdateRaction", ruib);
		}catch (Exception e) {
			System.out.println("ReportDaoImpl updateRaction"+e.getMessage());
		}
		return result;
	}

	@Override
	public List<ReportUser_infoBoard> accusationList(String search) {
		List<ReportUser_infoBoard> list = null;
		System.out.println("ReportDaoImpl accusationList start..");

		try {
			list = session.selectList("accusationSelectReportUser_infoBoardSearch", search);
			for(int i=0; i<list.size();i++) {
				list.get(i).setUfollowing(session.selectOne("coffeeSensorFollowingReport", list.get(i).getRucode()));
				list.get(i).setUfollower(session.selectOne("coffeeSensorFollowerReport", list.get(i).getRucode()));
				
			}
		}catch (Exception e) {
			System.out.println("ReportDaoImpl accusationList ->"+e.getMessage());
		}
		
		
		return list;
	}

	@Override
	public List<ReportUser_infoBoard> uncensoredList(String search) {
		List<ReportUser_infoBoard> list = null;
		System.out.println("ReportDaoImpl uncensoredList start..");

		try {
			list = session.selectList("accusationSelectUncensoredReportUser_infoBoardSearch", search);
			for(int i=0; i<list.size();i++) {
				list.get(i).setUfollowing(session.selectOne("coffeeSensorFollowingReport", list.get(i).getRucode()));
				list.get(i).setUfollower(session.selectOne("coffeeSensorFollowerReport", list.get(i).getRucode()));
				
			}
		}catch (Exception e) {
			System.out.println("ReportDaoImpl uncensoredList ->"+e.getMessage());
		}
		return list;
	}

	
}
