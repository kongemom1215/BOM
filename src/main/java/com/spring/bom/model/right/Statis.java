package com.spring.bom.model.right;

public class Statis {		
	private String search;	
	private int ucode; 
	private int sage;	 
	private String stype;
	private String stime;
	
	//Extra DB data
	private int count;	
	private int rank;
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getUcode() {
		return ucode;
	}
	public void setUcode(int ucode) {
		this.ucode = ucode;
	}
	public int getSage() {
		return sage;
	}
	public void setSage(int sage) {
		this.sage = sage;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	
	//Extra DB data
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
}
