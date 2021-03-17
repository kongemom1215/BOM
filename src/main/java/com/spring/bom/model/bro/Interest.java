package com.spring.bom.model.bro;

import java.util.List;

public class Interest {
	int uCode;  // 사용자
	// List<String> iCode; // 관심사
	String  iCode; // 관심사
	public int getuCode() {
		return uCode;
	}
	public void setuCode(int uCode) {
		this.uCode = uCode;
	}
	public String getiCode() {
		return iCode;
	}
	public void setiCode(String iCode) {
		this.iCode = iCode;
	}


}
