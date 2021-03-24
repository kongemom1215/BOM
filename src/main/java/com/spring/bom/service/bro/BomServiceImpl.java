package com.spring.bom.service.bro;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bom.dao.bro.BomDao;

import com.spring.bom.model.bro.User_info;
@Service
public class BomServiceImpl implements BomService {
	@Autowired
	private BomDao bd;
	

	@Override
	public User_info loginCheck(User_info ui) throws Exception{
		System.out.println("service ui      "+ui);
		return bd.loginCheck(ui);
	}

	@Override
	public int logout(String uEmail) {
		System.out.println("service logout uEmail"+uEmail);
		return bd.logout(uEmail);
		
	}

	@Override
	public int join(User_info ui) {
		System.out.println("service join start");
		return bd.join(ui);
		
	}

	@Override
	public int checkEmail(String uEmail) {
		System.out.println("service uEmail--"+uEmail);
		int i = bd.checkEmail(uEmail);
		System.out.println(i + "i는");
		return i;
	    }

	@Override
	public int interestAction(List<String> iCode) {
	    int j = bd.interestAction(iCode);
		return j;
	}

	@Override
	public int checkAtid(String uAtid) {
		System.out.println("service uEmail--"+uAtid);
		int i = bd.checkAtid(uAtid);
		System.out.println(i + "i는");
		return i;
	   
	}

	@Override
	public int fileName(String fileName) {
		System.out.println("service fileName"+fileName);
		return bd.fileName(fileName);
	}

	@Override
	public int loginClear(String uEmail) {
		System.out.println("service loginClear uEmail"+uEmail);
		return bd.loginClear(uEmail);
	}

	@Override
	public int findPw(String uEmail, String tempPassword) {
		
		return bd.findPw(uEmail, tempPassword);
	}

	@Override
	public int online(String uEmail) {
	
		return bd.online(uEmail);
	}

	@Override
	public int state(String uEmail) {
		
		return bd.state(uEmail);
	}

	@Override
	public int loginCount(String uEmail) {
		
		return bd.loginCount(uEmail);
	}


	

}
