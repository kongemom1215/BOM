package com.spring.bom.service.coffee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.coffee.ReportDao;
import com.spring.bom.model.coffee.ReportUser_infoBoard;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportDao rd;
	@Override
	public List<ReportUser_infoBoard> accusationList() {
		System.out.println("ReportServiceImpl accusationList start..");

		List<ReportUser_infoBoard> list = rd.accusationList();
		return list;
	}
	@Override
	public List<ReportUser_infoBoard> uncensoredList() {
		List<ReportUser_infoBoard> list = rd.uncensoredList();
		return list;
	}
	@Override
	public int updateRaction(int rcode, int updateValue) {
		int result = rd.updateRaction(rcode, updateValue);
		return result;
	}
	@Override
	public List<ReportUser_infoBoard> accusationList(String search) {
		List<ReportUser_infoBoard> list = rd.accusationList(search);
		return list;
	}
	@Override
	public List<ReportUser_infoBoard> uncensoredList(String search) {
		List<ReportUser_infoBoard> list = rd.uncensoredList(search);
		return list;
	}
	
	

}
