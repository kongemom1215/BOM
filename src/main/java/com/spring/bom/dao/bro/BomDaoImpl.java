package com.spring.bom.dao.bro;





import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.bro.Interest;
import com.spring.bom.model.bro.User_info;

@Repository
public class BomDaoImpl implements BomDao {
	@Autowired
	private SqlSession session;

	@Override	
	public User_info loginCheck(User_info ui) throws Exception{
		System.out.println("dao ui      "+ui);
		ui = session.selectOne("login",ui);
		return ui;
	}

	@Override
	public int logout(String uEmail) {
		System.out.println("dao logout uEmail"+uEmail);
		int result = session.update("uLoginCount",uEmail);
		System.out.println("result -> "+result);
		return result;
		
		
	}

	@Override
	public int join(User_info ui) {
		System.out.println("dao join start");
		int succ = 0;
		succ = session.insert("join", ui); 
		
		return succ;
		
	}

	@Override
	public int checkEmail(String uEmail) {
		System.out.println("dao uEmail--"+uEmail);
		int result = session.selectOne("checkEmail",uEmail);
		System.out.println("dao result-- "+ result );
		return result;
	}

	@Override
	public int interestAction(List<String> iCodeList) {
	
	    int uCode = session.selectOne("uCode2");
	    System.out.println("interestAction ucode -- "+uCode); 
		Interest in = new Interest();
		in.setuCode(uCode);
	//	in.setiCode(iCode);
		for(String iCode : iCodeList) {
			System.out.println("BomDaoImpl interestAction icode -- "+iCode);
			in.setiCode(iCode);
			int suc = session.insert("interestValue" , in );
			System.out.println("BomDaoImpl interestAction interestValue -->"+suc);
		}
		// 
	    
		return  iCodeList.size();
	}

	@Override
	public int checkAtid(String uAtid) {
		System.out.println("dao uAtid--"+uAtid);
		int result = session.selectOne("checkAtid",uAtid);
		System.out.println("dao result-- "+ result );
		return result;
	
	}

	@Override
	public int fileName(String uImage) {
		System.out.println("dao uImage--"+uImage);
		int uCode =session.selectOne("uCode1");
		System.out.println("dao fileName uCode1-->"+uCode);
		User_info ui = new User_info();
		ui.setuCode(uCode);
		ui.setuImage(uImage);
		return session.update("fileName",ui);
	}

	@Override
	public int loginClear(String uEmail) {
		System.out.println("dao logout uEmail"+uEmail);
		int result = session.update("uLoginClear",uEmail);
		System.out.println("result -> "+result);
		return result;
	}

	@Override
	public int findPw(String uEmail , String tempPassword) {
		
		User_info ui = new User_info();
		ui.setuEmail(uEmail);
		ui.setuPassword(tempPassword);
		return session.update("findPw", ui);
	}

	

	

	
		
	
	
	

}
