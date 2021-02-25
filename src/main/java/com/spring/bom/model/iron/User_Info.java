package com.spring.bom.model.iron;

public class User_Info {
	//Mirror
	private int ucode; // 회원코드
	private String uemail; // 회원이메일
	private String upassword;// 비밀번호
	private String uatid; //@아이디
	private String unickName;	//닉네임
	private String ubirth; //회원생일 yyyy/mm/dd
	private String ugender; // 성별 w/m
	private String unation;	// 국가
	private int uidentify;	//2단계인증여부
	private int uchat;	// 쪽지
	private int ualarm;	// 알람여부
	private int uprivate;	//공개여부
	private String uregDate;	//가입일
	private String uintro;	//회원소개
	private String uloc;	//회원위치
	private String uimage;	//회원프로필사진
	private String ubg;		//배경사진
	private String uprofileLink;	//회원 링크
	private int ustate;	//회원상태 여부	0:탈퇴, 1: 정상, 2:활동정지
	private int ureportCount;	//신고횟수
	private int uloginCount;	//로그인 시도횟수
	private int uonline;	//온라인 여부
	//Extra columns
	
	public int getUcode() {
		return ucode;
	}

	public void setUcode(int ucode) {
		this.ucode = ucode;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
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

	public String getUbirth() {
		return ubirth;
	}

	public void setUbirth(String ubirth) {
		this.ubirth = ubirth;
	}

	public String getUgender() {
		return ugender;
	}

	public void setUgender(String ugender) {
		this.ugender = ugender;
	}

	public String getUnation() {
		return unation;
	}

	public void setUnation(String unation) {
		this.unation = unation;
	}

	public int getUidentify() {
		return uidentify;
	}

	public void setUidentify(int uidentify) {
		this.uidentify = uidentify;
	}

	public int getUchat() {
		return uchat;
	}

	public void setUchat(int uchat) {
		this.uchat = uchat;
	}

	public int getUalarm() {
		return ualarm;
	}

	public void setUalarm(int ualarm) {
		this.ualarm = ualarm;
	}

	public int getUprivate() {
		return uprivate;
	}

	public void setUprivate(int uprivate) {
		this.uprivate = uprivate;
	}

	public String getUregDate() {
		return uregDate;
	}

	public void setUregDate(String uregDate) {
		this.uregDate = uregDate;
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

	public String getUbg() {
		return ubg;
	}

	public void setUbg(String ubg) {
		this.ubg = ubg;
	}

	public String getUprofileLink() {
		return uprofileLink;
	}

	public void setUprofileLink(String uprofileLink) {
		this.uprofileLink = uprofileLink;
	}

	public int getUstate() {
		return ustate;
	}

	public void setUstate(int ustate) {
		this.ustate = ustate;
	}

	public int getUreportCount() {
		return ureportCount;
	}

	public void setUreportCount(int ureportCount) {
		this.ureportCount = ureportCount;
	}

	public int getUloginCount() {
		return uloginCount;
	}

	public void setUloginCount(int uloginCount) {
		this.uloginCount = uloginCount;
	}

	public int getUonline() {
		return uonline;
	}

	public void setUonline(int uonline) {
		this.uonline = uonline;
	}
	
}
