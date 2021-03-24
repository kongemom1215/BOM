package com.spring.bom.service.god;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.god.JHUserDao;
import com.spring.bom.model.god.JHBoard;
import com.spring.bom.model.god.JHLike;
import com.spring.bom.model.god.JHUser_info;

@Service
public class JHUserServiceImpl implements JHUserService{
	@Autowired
	private JHUserDao ud;

	@Override
	public String getReserveNum(int ucode) {
		return ud.getReserveNum(ucode);
	}

	@Override
	public String getSaveNum(int ucode) {
		// TODO Auto-generated method stub
		return ud.getSaveNum(ucode);
	}
	
	@Override
	public List<JHBoard> getReserveList(int ucode) {
		// TODO Auto-generated method stub
		return ud.getReserveList(ucode);
	}

	@Override
	public List<JHBoard> getSaveList(int ucode) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl getSaveList()");
		return ud.getSaveList(ucode);
	}

	@Override
	public List<JHUser_info> getFollowerList(int bcode) {
		// TODO Auto-generated method stub
		return ud.getFollowerList(bcode);
	}

	@Override
	public List<JHUser_info> getSearchList(String search_value) {
		// TODO Auto-generated method stub
		return ud.getSearchList(search_value);
	}

	@Override
	public JHLike getUserLike(int bcode, int ucode) {
		// TODO Auto-generated method stub
		return ud.getUserLike(bcode, ucode);
	}

}
