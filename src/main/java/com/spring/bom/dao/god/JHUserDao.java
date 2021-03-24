package com.spring.bom.dao.god;

import java.util.List;

import com.spring.bom.model.god.JHBoard;
import com.spring.bom.model.god.JHLike;
import com.spring.bom.model.god.JHUser_info;

public interface JHUserDao {

	String getReserveNum(int ucode);

	List<JHBoard> getReserveList(int ucode);

	List<JHBoard> getSaveList(int ucode);

	String getSaveNum(int ucode);

	List<JHUser_info> getFollowerList(int bcode);

	List<JHUser_info> getSearchList(String search_value);

	JHLike getUserLike(int bcode, int ucode);
}
