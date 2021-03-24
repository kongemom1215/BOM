package com.spring.bom.service.iron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.iron.User_InfoDao;
import com.spring.bom.model.iron.User_Info;

@Service
public class User_InfoServiceImpl implements User_InfoService {

	@Autowired
	private User_InfoDao ud;
	
	@Override
	public User_Info getLoginUserInfo(int ucode) {
		System.out.println("[iron] User_InfoServiceImpl getLoginUserInfo start...");
		User_Info user = ud.getLoginUserInfo(ucode); 
		if(user==null) System.out.println("[iron] ud.getLoginUserInfo -> null;;");
		else System.out.println("[iron] ud.getLoginUserInfo -> exist!!");
		return user;
	}

	@Override
	public User_Info getUserInfoUatid(User_Info user) {
		System.out.println("[iron] User_InfoServiceImpl getUserInfoUatid start...");
		user = ud.getUserInfoUatid(user);
		if(user.getUnickName()==null) System.out.println("[iron] ud.getUserInfoUatid -> null;;");
		else System.out.println("[iron] ud.getLoginUserInfo -> exist!! -> "+user.getUcode()+user.getUnickName()
		);
		return user;
	}

	@Override
	public int getUserfollowCount(User_Info someone) {
		System.out.println("[iron] User_InfoServiceImpl getUserfollowCount start...");
		return ud.getUserfollowCount(someone);
	}

	@Override
	public int getUserfollowerCount(User_Info someone) {
		System.out.println("[iron] User_InfoServiceImpl getfollowerCount start...");
		return ud.getUserfollowerCount(someone);
	}

	@Override
	public int editProfileData(User_Info ui) {
		System.out.println("[iron] User_InfoServiceImpl fileName __ For EditProfile start...");
		return ud.editProfileData(ui);
	}

}
