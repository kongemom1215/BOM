package com.spring.bom.model.right;

public class RFollow {
	//Mirror columns
	private int ucode;
	private int fopcode;
	private String ftime;
	private int fblockState;
	
	//Extra columns
	private int uucode; // 회원코드
	private String uatid; //@아이디
	private String unickName;	//닉네임
	private String uintro;	//회원소개
	private String uloc;	//회원위치
	private String uimage;	//회원프로필사진
	private int ustate;	//회원상태 여부	0:탈퇴, 1: 정상, 2:활동정지
	private int uonline;// 로그인 상태 여부
	private int followercnt; //팔로우 수
	private int followingcnt; //팔로잉 수
	private int bfollowercnt; //내가 차단한 팔로워 수
	private int bfollowingcnt; //나를 차단한 팔로잉 수
	private int bopcode; //내가 차단한 사람
	
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
	public int getFblockState() {
		return fblockState;
	}
	public void setFblockState(int fblockState) {
		this.fblockState = fblockState;
	}
	public int getUucode() {
		return uucode;
	}
	public void setUucode(int uucode) {
		this.uucode = uucode;
	}
	public String getUatid() {
		return uatid;
	}
	public void setUatid(String uatid) {
		this.uatid = uatid;
	}
	public String getUnickName() {
		return unickName;
	}
	public void setUnickName(String unickName) {
		this.unickName = unickName;
	}
	public String getUintro() {
		return uintro;
	}
	public void setUintro(String uintro) {
		this.uintro = uintro;
	}
	public String getUloc() {
		return uloc;
	}
	public void setUloc(String uloc) {
		this.uloc = uloc;
	}
	public String getUimage() {
		return uimage;
	}
	public void setUimage(String uimage) {
		this.uimage = uimage;
	}
	public int getUstate() {
		return ustate;
	}
	public void setUstate(int ustate) {
		this.ustate = ustate;
	}
	public int getUonline() {
		return uonline;
	}
	public void setUonline(int uonline) {
		this.uonline = uonline;
	}
	public int getFollowercnt() {
		return followercnt;
	}
	public void setFollowercnt(int followercnt) {
		this.followercnt = followercnt;
	}
	public int getFollowingcnt() {
		return followingcnt;
	}
	public void setFollowingcnt(int followingcnt) {
		this.followingcnt = followingcnt;
	}
	public int getBfollowercnt() {
		return bfollowercnt;
	}
	public void setBfollowercnt(int bfollowercnt) {
		this.bfollowercnt = bfollowercnt;
	}
	public int getBfollowingcnt() {
		return bfollowingcnt;
	}
	public void setBfollowingcnt(int bfollowingcnt) {
		this.bfollowingcnt = bfollowingcnt;
	}
	public int getBopcode() {
		return bopcode;
	}
	public void setBopcode(int bopcode) {
		this.bopcode = bopcode;
	}
	
	
	
}