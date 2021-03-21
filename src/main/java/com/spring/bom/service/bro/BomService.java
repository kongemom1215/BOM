package com.spring.bom.service.bro;

import java.util.List;

import com.spring.bom.model.bro.User_info;

public interface BomService {
	public User_info loginCheck(User_info ui) throws Exception;

	public int logout(String uEmail);

	public int join(User_info ui);

	public int checkEmail(String uEmail);

	public int interestAction(List<String> iCode);

	public int checkAtid(String uAtid);

	public int fileName(String fileName);

	public int loginClear(String uEmail);

	public int findPw(String uEmail, String tempPassword);

}
