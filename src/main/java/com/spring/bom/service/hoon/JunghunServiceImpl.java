package com.spring.bom.service.hoon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.hoon.JunghunDao;
import com.spring.bom.model.hoon.Junghun;

@Service
public class JunghunServiceImpl implements JunghunService {
	@Autowired
	private JunghunDao jd;
	
	@Override
	public List<Junghun> listSearch(Junghun junghun) {
		System.out.println("Service Search :: "+junghun);
		return jd.listSearch(junghun);
	}

	@Override
	public List<Junghun> listUser(Junghun junghun) {
		return jd.listUser(junghun);
	}

	@Override
	public List<Junghun> listNew(Junghun junghun) {
		return jd.listNew(junghun);
	}

	@Override
	public List<Junghun> listCount(Junghun junghun) {
		return jd.listCount(junghun);
	}

	@Override
	public int searchData(Junghun junghun) {
		return jd.searchData(junghun);
	}

	@Override
	public List<Junghun> listHash(Junghun junghun) {
		return jd.listHash(junghun);
	}

	@Override
	public List<Junghun> listTrend(Junghun junghun) {
		return jd.listTrend(junghun);
	}

	@Override
	public List<Junghun> searchkeyword(Junghun junghun) {
		return jd.searchkeyword(junghun);
	}

	@Override
	public int deleterow(int ucode) {
		System.out.println("del sa::1");
		return jd.deleterow(ucode);
	}

	@Override
	public List<Junghun> searchblock(Junghun junghun) {
		// TODO Auto-generated method stub
		return jd.searchblock(junghun);
	}

	@Override
	public int searchlike(Junghun junghun) {
		return jd.searchlike(junghun);
	}


}
