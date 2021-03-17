package com.spring.bom.service.coffee;

import java.util.List;

import com.spring.bom.model.coffee.ReportUser_infoBoard;

public interface ReportService {
	List<ReportUser_infoBoard> accusationList();
	List<ReportUser_infoBoard> uncensoredList();
	int updateRaction(int rcode, int updateValue);
	List<ReportUser_infoBoard> accusationList(String search);
	List<ReportUser_infoBoard> uncensoredList(String search);
}
