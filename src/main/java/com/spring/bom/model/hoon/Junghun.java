package com.spring.bom.model.hoon;

public class Junghun {
	// search
	private String search;
	private int uCode;
	private int sage;
	private String stime;
	private String stype;
	private int srecord;
	private int scount;

	// board
	private String bcontent;
	private int blikecount;
	private String breplycount;
	private String bquotecount;
	private String battach;
	private String bregdate;
	private int bcode;
	private int bbcode;
	private String btype;
	
	// hastag
	private String hname;
	private String hbcode;

	// Extra DB column data
	private int hcount;
	// Not DTO
	private int hrank;

	// user_info
	private String uNickName;
	private String uintro;
	private String uimage;

	// block
	private int bopcode;
	private String bword;

	// Like_bookmark
	private String ltype;
	private String ldate;
	private String bbtype;
	private String bdate;

	// login된 Ucode
	static private int loginUcode; // 로그인 확인용

	// 첨부파일 데이터
	private String battachType;
	private String battachSrc;

	// Mirror columns
	private int ucode;
	private int fopcode;
	private String ftime;
	private int fblockState;

	// Extra columns
	private int uucode; // 회원코드
	private String uatid; // @아이디
	private String unickName; // 닉네임
	private String ubirth; // 회원생일 yyyy/mm/dd
	private String unation; // 국가
	private String uloc; // 회원위치
	private int ustate; // 회원상태 여부 0:탈퇴, 1: 정상, 2:활동정지
	private int uonline;// 로그인 상태 여부

	// For Quote
	private String q_uimage;
	private String q_nickname;
	private String q_atid;
	private String q_content;
	private String q_attach;
	private String q_attachsrc;
	private String q_attachtype;
	private String q_regdate;

	
	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public int getHcount() {
		return hcount;
	}

	public void setHcount(int hcount) {
		this.hcount = hcount;
	}

	public int getHrank() {
		return hrank;
	}

	public void setHrank(int hrank) {
		this.hrank = hrank;
	}

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

	public String getUbirth() {
		return ubirth;
	}

	public void setUbirth(String ubirth) {
		this.ubirth = ubirth;
	}

	public String getUnation() {
		return unation;
	}

	public void setUnation(String unation) {
		this.unation = unation;
	}

	public String getUloc() {
		return uloc;
	}

	public void setUloc(String uloc) {
		this.uloc = uloc;
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

	public String getUimage() {
		return uimage;
	}

	public void setUimage(String uimage) {
		this.uimage = uimage;
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

	public String getLdate() {
		return ldate;
	}

	public void setLdate(String ldate) {
		this.ldate = ldate;
	}

	public String getBbtype() {
		return bbtype;
	}

	public void setBbtype(String bbtype) {
		this.bbtype = bbtype;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getBattachType() {
		return battachType;
	}

	public void setBattachType(String battachType) {
		this.battachType = battachType;
	}

	public String getBattachSrc() {
		return battachSrc;
	}

	public void setBattachSrc(String battachSrc) {
		this.battachSrc = battachSrc;
	}

	public String getBregdate() {
		return bregdate;
	}

	public void setBregdate(String bregdate) {
		this.bregdate = bregdate;
	}

	public String getBattach() {
		return battach;
	}

	public void setBattach(String battach) {
		this.battach = battach;
	}

	public String getBword() {
		return bword;
	}

	public void setBword(String bword) {
		this.bword = bword;
	}

	public int getBopcode() {
		return bopcode;
	}

	public void setBopcode(int bopcode) {
		this.bopcode = bopcode;
	}

	public String getBreplycount() {
		return breplycount;
	}

	public void setBreplycount(String breplycount) {
		this.breplycount = breplycount;
	}

	public String getBquotecount() {
		return bquotecount;
	}

	public void setBquotecount(String bquotecount) {
		this.bquotecount = bquotecount;
	}

	public int getLoginUcode() {
		return loginUcode;
	}

	public void setLoginUcode(int loginUcode) {
		this.loginUcode = loginUcode;
	}

	public int getScount() {
		return scount;
	}

	public void setScount(int scount) {
		this.scount = scount;
	}

	public String getUintro() {
		return uintro;
	}

	public void setUintro(String uintro) {
		this.uintro = uintro;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getuCode() {
		return uCode;
	}

	public void setuCode(int ucode) {
		this.uCode = ucode;
	}

	public int getSage() {
		return sage;
	}

	public void setSage(int sage) {
		this.sage = sage;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public int getSrecord() {
		return srecord;
	}

	public void setSrecord(int srecord) {
		this.srecord = srecord;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public int getBlikecount() {
		return blikecount;
	}

	public void setBlikecount(int blikecount) {
		this.blikecount = blikecount;
	}

	public String getuNickName() {
		return uNickName;
	}

	public void setuNickName(String uNickName) {
		this.uNickName = uNickName;
	}

	public int getBbcode() {
		return bbcode;
	}

	public void setBbcode(int bbcode) {
		this.bbcode = bbcode;
	}

	//For Quote
	public String getQ_uimage() {
		return q_uimage;
	}

	public void setQ_uimage(String q_uimage) {
		this.q_uimage = q_uimage;
	}

	public String getQ_nickname() {
		return q_nickname;
	}

	public void setQ_nickname(String q_nickname) {
		this.q_nickname = q_nickname;
	}

	public String getQ_atid() {
		return q_atid;
	}

	public void setQ_atid(String q_atid) {
		this.q_atid = q_atid;
	}

	public String getQ_content() {
		return q_content;
	}

	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}

	public String getQ_attach() {
		return q_attach;
	}

	public void setQ_attach(String q_attach) {
		this.q_attach = q_attach;
	}

	public String getQ_attachsrc() {
		return q_attachsrc;
	}

	public void setQ_attachsrc(String q_attachsrc) {
		this.q_attachsrc = q_attachsrc;
	}

	public String getQ_attachtype() {
		return q_attachtype;
	}

	public void setQ_attachtype(String q_attachtype) {
		this.q_attachtype = q_attachtype;
	}

	public String getQ_regdate() {
		return q_regdate;
	}

	public void setQ_regdate(String q_regdate) {
		this.q_regdate = q_regdate;
	}

}
