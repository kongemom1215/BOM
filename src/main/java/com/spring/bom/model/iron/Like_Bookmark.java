package com.spring.bom.model.iron;

public class Like_Bookmark {
	//Mirror columns
	private int ucode;
	private int bcode;
	private String ltype;
	private String bbtype;
	private String ldate;
	private String bdate;
	
	//No Dto
	private int likeCount;
	private int likeLogicStep;
	
	//Mirror Getter/Setter
	public int getUcode() {
		return ucode;
	}
	public void setUcode(int ucode) {
		this.ucode = ucode;
	}
	public int getBcode() {
		return bcode;
	}
	public void setBcode(int bcode) {
		this.bcode = bcode;
	}
	public String getLtype() {
		return ltype;
	}
	public void setLtype(String ltype) {
		this.ltype = ltype;
	}
	public String getBbtype() {
		return bbtype;
	}
	public void setBbtype(String bbtype) {
		this.bbtype = bbtype;
	}
	public String getLdate() {
		return ldate;
	}
	public void setLdate(String ldate) {
		this.ldate = ldate;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	//No Dto
	
	public int getLikeLogicStep() {
		return likeLogicStep;
	}
	public void setLikeLogicStep(int likeLogicStep) {
		this.likeLogicStep = likeLogicStep;
	}
	
	
	
}
