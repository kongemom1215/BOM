package com.spring.bom.dao.god;

import java.util.List;

import com.spring.bom.model.god.JHBoard;

public interface JHUserDao {

	String getReserveNum(int ucode);

	List<JHBoard> getReserveList(int ucode);

	List<JHBoard> getSaveList(int ucode);

	String getSaveNum(int ucode);
}
