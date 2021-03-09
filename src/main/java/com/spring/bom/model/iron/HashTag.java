package com.spring.bom.model.iron;

public class HashTag {
	//Mirror - 디비 컬럼 데이터
	private String hname;
	private String hbcode;
	
	//Extra DB column data
	private int hcount;
	//Not DTO
	private int hrank;
	
	//Mirror getter/setter
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getHbcode() {
		return hbcode;
	}
	public void setHbcode(String hbcode) {
		this.hbcode = hbcode;
	}
	
	//Extra DB columns getter/setter
	public int getHcount() {
		return hcount;
	}
	public void setHcount(int hcount) {
		this.hcount = hcount;
	}
	
	//Not DTO
	public int getHrank() {
		return hrank;
	}
	public void setHrank(int hrank) {
		this.hrank = hrank;
	}
	
	
}
