package com.spring.bom.model.right;

public class RBlock {
	private int ucode;
	private int fopcode;		//팔로우 상대 아이디
	private String ftime;		//팔로우 시간?
	private int fblockstate;  //차단상태 -> follow	
	private String uatid;	  //아이디
	private String unickname; //닉네임
	private String uimage;	//프로필이미지
	private int blcode;		//차단번호
	private int bcopcode; //차단회원코드
	private String bhashtag; //해시태그
	private String bword;  //단어
	
	public int getUcode() {
		return ucode;
	}
	public void setUcode(int ucode) {
		this.ucode = ucode;
	}
	public int getFopcode() {
		return fopcode;
	}
	public void setFopcode(int fopcode) {
		this.fopcode = fopcode;
	}
	public String getFtime() {
		return ftime;
	}
	public void setFtime(String ftime) {
		this.ftime = ftime;
	}
	public int getFblockstate() {
		return fblockstate;
	}
	public void setFblockstate(int fblockstate) {
		this.fblockstate = fblockstate;
	}
	public String getUatid() {
		return uatid;
	}
	public void setUatid(String uatid) {
		this.uatid = uatid;
	}
	public String getUnickname() {
		return unickname;
	}
	public void setUnickname(String unickname) {
		this.unickname = unickname;
	}
	public String getUimage() {
		return uimage;
	}
	public void setUimage(String uimage) {
		this.uimage = uimage;
	}
	public int getBlcode() {
		return blcode;
	}
	public void setBlcode(int blcode) {
		this.blcode = blcode;
	}
	public int getBcopcode() {
		return bcopcode;
	}
	public void setBcopcode(int bcopcode) {
		this.bcopcode = bcopcode;
	}
	public String getBhashtag() {
		return bhashtag;
	}
	public void setBhashtag(String bhashtag) {
		this.bhashtag = bhashtag;
	}
	public String getBword() {
		return bword;
	}
	public void setBword(String bword) {
		this.bword = bword;
	}
	
	
}
