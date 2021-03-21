package com.spring.bom.model.yeah;

public class YeahBlock {
   int blcode; // 차단번호
   int ucode; // 회원코드
   int bopcode; // 차단회원코드
   String bword; // 단어

	public int getBlcode() {
		return blcode;
	}

	public void setBlcode(int blcode) {
		this.blcode = blcode;
	}

	public int getUcode() {
		return ucode;
	}

	public void setUcode(int ucode) {
		this.ucode = ucode;
	}

	public int getBopcode() {
		return bopcode;
	}

	public void setBopcode(int bopcode) {
		this.bopcode = bopcode;
	}

	public String getBword() {
		return bword;
	}

	public void setBword(String bword) {
		this.bword = bword;
	}
}
