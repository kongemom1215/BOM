package com.spring.bom.service.coffee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.coffee.User_infoDao;
import com.spring.bom.model.coffee.CoffeeUser_info;

@Service
public class User_infoServiceImpl implements User_infoService {
	@Autowired
	private User_infoDao uid;
	@Override
	public List<CoffeeUser_info> user_infoSensorList() {
		List<CoffeeUser_info> user_infoList = null;
		user_infoList = uid.user_infoSensorList();
		return user_infoList;
	}
	@Override
	public List<CoffeeUser_info> user_infoRestoreList() {
		List<CoffeeUser_info> user_infoList = null;
		user_infoList = uid.user_infoRestoreList();
		return user_infoList;
	}
	@Override
	public List<CoffeeUser_info> user_infoAccusationList() {
		List<CoffeeUser_info> user_infoList = null;
		user_infoList = uid.user_infoAccusationList();
		return user_infoList;
	}
	@Override
	public int updateUstate(int ucode, int updateValue) {
		int result = uid.updateUstate(ucode, updateValue);
		return result;
	}
	@Override
	public int memConfirmManager(int getuCode) {
		int result = uid.memConfirmManager(getuCode);
		return result;
	}
	@Override
	public List<CoffeeUser_info> user_infoAccusationList(String search) {
		List<CoffeeUser_info> user_infoList = null;
		user_infoList = uid.user_infoAccusationList(search);
		return user_infoList;
	}
	@Override
	public List<CoffeeUser_info> user_infoSensorList(String search) {
		List<CoffeeUser_info> user_infoList = null;
		user_infoList = uid.user_infoSensorList(search);
		return user_infoList;
	}
	@Override
	public List<CoffeeUser_info> user_infoRestoreList(String search) {
		List<CoffeeUser_info> user_infoList = null;
		user_infoList = uid.user_infoRestoreList(search);
		return user_infoList;
	}

}
