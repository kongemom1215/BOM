package com.spring.bom.service.coffee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.coffee.CoffeeBoardDao;
import com.spring.bom.model.coffee.BoardUser_info;

@Service
public class CoffeeBoardServiceImpl implements CoffeeBoardService {
	@Autowired
	private CoffeeBoardDao bd;
	@Override
	public List<BoardUser_info> sensorList() {
		List<BoardUser_info> list = null;
		list = bd.sensorList();
		return list;
	}

	@Override
	public List<BoardUser_info> restoreList() {
		List<BoardUser_info> list = null;
		list = bd.restoreList();
		return list;
	}

	@Override
	public List<BoardUser_info> accusationList() {
		List<BoardUser_info> list = null;
		list = bd.accusationList();
		return list;
	}

	@Override
	public int updateBstate(int bcode, int updateValue) {
		int result = bd.updateBstate(bcode, updateValue);
		return result;
	}

	@Override
	public List<BoardUser_info> accusationList(String search) {
		List<BoardUser_info> list = null;
		list = bd.accusationList(search);
		return list;
	}

	@Override
	public List<BoardUser_info> sensorList(String search) {
		List<BoardUser_info> list = null;
		list = bd.sensorList(search);
		return list;
	}

	@Override
	public List<BoardUser_info> restoreList(String search) {
		List<BoardUser_info> list = null;
		list = bd.restoreList(search);
		return list;
	}

}
