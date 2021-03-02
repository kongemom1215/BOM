package com.spring.bom.dao.iron;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.iron.User_Info;

@Repository
public class User_InfoDaoImpl implements User_InfoDao {
	@Autowired
	private SqlSession session;
	
	@Override
	public User_Info getLoginUserInfo(int ucode) {
		System.out.println("[iron] User_InfoDaoImpl getLoginUserInfo start...");
		User_Info user = session.selectOne("loginInfo", ucode);
		if(user==null) System.out.println("[iron] User_InfoDaoImpl getLoginUserInfo -> null;;");
		else System.out.println("[iron] User_InfoDaoImpl getLoginUserInfo -> exist!!");
		return user;
	}
	
}
