package com.spring.bom.service.coffee;

import java.util.List;

import com.spring.bom.model.coffee.CoffeeUser_info;

public interface User_infoService {
	List<CoffeeUser_info> user_infoSensorList();
	List<CoffeeUser_info> user_infoRestoreList();
	List<CoffeeUser_info> user_infoAccusationList();
	int updateUstate(int ucode, int updateValue);
	int memConfirmManager(int getuCode);
	List<CoffeeUser_info> user_infoAccusationList(String search);
	List<CoffeeUser_info> user_infoSensorList(String search);
	List<CoffeeUser_info> user_infoRestoreList(String search);
}
