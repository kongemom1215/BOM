package com.spring.bom.model.right;

import java.util.List;

public class RInterest {
	private int ucode;
	private int icode;
	private String icontent;
	private int icode1;
	private int icode2;
	private int icode3;
	
	//Extra DB data
	private List<Integer> interestList;
	
	public int getUcode() {
		return ucode;
	}
	public void setUcode(int ucode) {
		this.ucode = ucode;
	}
	public int getIcode() {
		return icode;
	}
	public void setIcode(int icode) {
		this.icode = icode;
	}
	public String getIcontent() {
		return icontent;
	}
	public void setIcontent(String icontent) {
		this.icontent = icontent;
	}

	
	//Extra DB getter/setter
	public List<Integer> getInterestList() {
		return interestList;
	}
	public void setInterestList(List<Integer> interestList) {
		this.interestList = interestList;
	}
	public int getIcode1() {
		return icode1;
	}
	public void setIcode1(int icode1) {
		this.icode1 = icode1;
	}
	public int getIcode2() {
		return icode2;
	}
	public void setIcode2(int icode2) {
		this.icode2 = icode2;
	}
	public int getIcode3() {
		return icode3;
	}
	public void setIcode3(int icode3) {
		this.icode3 = icode3;
	}
	
}
