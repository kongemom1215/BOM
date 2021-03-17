package com.spring.bom.dao.coffee;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.coffee.CoffeeUser_info;

@Repository
public class User_infoDaoImpl implements User_infoDao {
	@Autowired
	private  SqlSession  session;
	@Override
	public List<CoffeeUser_info> user_infoSensorList() {
		List<CoffeeUser_info> list = null;
		try {
			list = session.selectList("coffeeSensorSelectUser_info");
			for(int i=0; i<list.size();i++) {
				list.get(i).setUfollowing(session.selectOne("coffeeSensorFollowing", list.get(i).getUcode()));
				list.get(i).setUfollower(session.selectOne("coffeeSensorFollower", list.get(i).getUcode()));
			}
			
		}catch (Exception e) {
			System.out.println("User_infoDaoImpl user_infoSensorList Error ->"+e.getMessage());
		}
		return list;
	}
	@Override
	public List<CoffeeUser_info> user_infoRestoreList() {
		List<CoffeeUser_info> list = null;
		try {
			list = session.selectList("coffeeRestoreSelectUser_info");
			for(int i=0; i<list.size();i++) {
				list.get(i).setUfollowing(session.selectOne("coffeeSensorFollowing", list.get(i).getUcode()));
				list.get(i).setUfollower(session.selectOne("coffeeSensorFollower", list.get(i).getUcode()));
				
			}
			
		}catch (Exception e) {
			System.out.println("User_infoDaoImpl user_infoRestoreList Error ->"+e.getMessage());
		}
		return list;
	}
	@Override
	public List<CoffeeUser_info> user_infoAccusationList() {
		List<CoffeeUser_info> list = null;
		try {
			list = session.selectList("coffeeAccusationSelectUser_info");
			for(int i=0; i<list.size();i++) {
				list.get(i).setUfollowing(session.selectOne("coffeeSensorFollowing", list.get(i).getUcode()));
				list.get(i).setUfollower(session.selectOne("coffeeSensorFollower", list.get(i).getUcode()));
				
			}
			
		}catch (Exception e) {
			System.out.println("User_infoDaoImpl user_infoAccusationList Error ->"+e.getMessage());
		}
		return list;
	}
	@Override
	public int updateUstate(int ucode, int updateValue) {
		int result = 0;
		CoffeeUser_info ui = new CoffeeUser_info();
		ui.setUcode(ucode);
		ui.setUstate(updateValue);
//		System.out.println(ucode);
//		System.out.println(updateValue);
		try{
			result = session.update("coffeeUpdateUstate", ui);
		}catch (Exception e) {
			System.out.println("User_infoDaoImpl updateUstate Error ->"+e.getMessage());
		}
		return result;
	}
	@Override
	public int memConfirmManager(int getuCode) {
		int result = 0;
		try {
			result = session.selectOne("coffeeSelectManager", getuCode);
		}catch (Exception e) {
			System.out.println("User_infoDaoImpl memConfirmManager"+e.getMessage());
		}
		return result;
	}
	@Override
	public List<CoffeeUser_info> user_infoAccusationList(String search) {
		List<CoffeeUser_info> list = null;
		try {
			list = session.selectList("coffeeAccusationSelectUser_infoSearch", search);
			for(int i=0; i<list.size();i++) {
				list.get(i).setUfollowing(session.selectOne("coffeeSensorFollowing", list.get(i).getUcode()));
				list.get(i).setUfollower(session.selectOne("coffeeSensorFollower", list.get(i).getUcode()));
				
			}
			
		}catch (Exception e) {
			System.out.println("User_infoDaoImpl user_infoAccusationList Error ->"+e.getMessage());
		}
		return list;
	}
	@Override
	public List<CoffeeUser_info> user_infoSensorList(String search) {
		List<CoffeeUser_info> list = null;
		try {
			list = session.selectList("coffeeSensorSelectUser_infoSearch", search);
			for(int i=0; i<list.size();i++) {
				list.get(i).setUfollowing(session.selectOne("coffeeSensorFollowing", list.get(i).getUcode()));
				list.get(i).setUfollower(session.selectOne("coffeeSensorFollower", list.get(i).getUcode()));
				
			}
			
		}catch (Exception e) {
			System.out.println("User_infoDaoImpl user_infoSensorList Error ->"+e.getMessage());
		}
		return list;
	}
	@Override
	public List<CoffeeUser_info> user_infoRestoreList(String search) {
		List<CoffeeUser_info> list = null;
		try {
			list = session.selectList("coffeeRestoreSelectUser_infoSearch", search);
			for(int i=0; i<list.size();i++) {
				list.get(i).setUfollowing(session.selectOne("coffeeSensorFollowing", list.get(i).getUcode()));
				list.get(i).setUfollower(session.selectOne("coffeeSensorFollower", list.get(i).getUcode()));
				
			}
			
		}catch (Exception e) {
			System.out.println("User_infoDaoImpl user_infoRestoreList Error ->"+e.getMessage());
		}
		return list;
	}

}
