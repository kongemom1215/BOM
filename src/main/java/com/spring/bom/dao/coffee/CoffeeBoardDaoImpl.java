package com.spring.bom.dao.coffee;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.coffee.BoardUser_info;

@Repository
public class CoffeeBoardDaoImpl implements CoffeeBoardDao {
	@Autowired
	private  SqlSession  session;
	
	@Override
	public List<BoardUser_info> sensorList() {
		List<BoardUser_info> list = new ArrayList<BoardUser_info>();
		list = session.selectList("coffeeSensorSelectBoardUser_info");
		
		return list;
	}

	@Override
	public List<BoardUser_info> restoreList() {
		List<BoardUser_info> list = new ArrayList<BoardUser_info>();
		list = session.selectList("coffeeRestoreSelectBoardUser_info");
		return list;
	}

	@Override
	public List<BoardUser_info> accusationList() {
		List<BoardUser_info> list = new ArrayList<BoardUser_info>();
		list = session.selectList("coffeeAccusationSelectBoardUser_info");
		return list;
	}

	@Override
	public int updateBstate(int bcode, int updateValue) {
		int result = 0;
		BoardUser_info bui = new BoardUser_info();
		bui.setBcode(bcode);
		bui.setBstate(updateValue);
		try{
			result = session.update("coffeeUpdateBstate", bui);
		}catch (Exception e) {
			System.out.println("BoardDaoImpl updateBstate"+e.getMessage());
		}
		return result;
	}

	@Override
	public List<BoardUser_info> accusationList(String search) {
		List<BoardUser_info> list = new ArrayList<BoardUser_info>();
		list = session.selectList("coffeeAccusationSelectBoardUser_infoSearch", search);
		return list;
	}

	@Override
	public List<BoardUser_info> sensorList(String search) {
		List<BoardUser_info> list = new ArrayList<BoardUser_info>();
		list = session.selectList("coffeeSensorSelectBoardUser_infoSearch", search);
		
		return list;
	}

	@Override
	public List<BoardUser_info> restoreList(String search) {
		List<BoardUser_info> list = new ArrayList<BoardUser_info>();
		list = session.selectList("coffeeRestoreSelectBoardUser_infoSearch", search);
		return list;
	}

}
