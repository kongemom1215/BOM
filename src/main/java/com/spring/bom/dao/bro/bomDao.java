package com.spring.bom.dao.bro;

import javax.servlet.http.HttpSession;

import com.spring.bom.model.bro.user_info;

public interface bomDao {
	public user_info loginCheck(user_info ui) throws Exception;
	public void logout(HttpSession session);

}