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

	@Override
	public User_Info getUserInfoUatid(User_Info user) {
		System.out.println("[iron] User_InfoDaoImpl getUserInfoUatid start...");
		user = session.selectOne("getUserInfoUatid",user);
		if(user.getUnickName()==null) System.out.println("[iron] User_InfoDaoImpl getLoginUserInfo -> null;;");
		else System.out.println("[iron] User_InfoDaoImpl getLoginUserInfo -> exist!!");
		return user;
	}

	@Override
	public int getUserfollowCount(User_Info someone) {
		System.out.println("[iron] User_InfoDaoImpl getUserfollowCount start...");
		int result = session.selectOne("UserFollowCount", someone);
		System.out.println("UserfollowCount -> "+result);
		return result;
	}

	@Override
	public int getUserfollowerCount(User_Info someone) {
		System.out.println("[iron] User_InfoDaoImpl getUserfollowerCount start...");
		int result = session.selectOne("UserFollowerCount", someone);
		System.out.println("UserfollowerCount -> "+result);
		return result;
	}

	@Override
	public int editProfileData(User_Info ui) {
		System.out.println("[iron] User_InfoDaoImpl editProfileData start...");
		int result = session.update("editProfile",ui);
		System.out.println("####### check result -> " + result);
		return result;
	}
	
}
