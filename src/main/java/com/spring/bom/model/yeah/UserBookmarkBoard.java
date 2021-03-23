package com.spring.bom.model.yeah;

public class UserBookmarkBoard {
	// 북마크
	private int ucode; // 회원코드
	private int bcode; // 글코드
	private String ltype;
	private String bbtype;
	private String ldate;
	private String bdate;

	// user_info
	private String uimage; // 프로필
	private String unickname; // 닉네임
	private String uatid; // 아이디

	// Board
	private String bregdate; // 작성시간
	private String bcontent; // 글 내용
	private String battach; // 첨부파일
	private int blikecount; // 좋아요 횟수
	private int breplycount; // 댓글 갯수
	private int bquotecount; // 스크랩 / 인용 갯수
	private int bbcode; // 스크랩 / 인용시 부여
	private String btype;

	// 인용 조회용
	private String q_uimage;
	private String q_nickname;
	private String q_atid;
	private String q_content;
	private String q_attach;
	private String q_attachsrc;
	private String q_attachtype;
	private String q_regdate;
	
	
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

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

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

	public int getBbcode() {
		return bbcode;
	}

	public void setBbcode(int bbcode) {
		this.bbcode = bbcode;
	}

	// battach Type 조회용
	String battachType;
	String battachSrc;

	// 인용 조회용
	String uimage3;
	String unickname3;
	String uatid3;
	

	public String getUimage3() {
		return uimage3;
	}

	public void setUimage3(String uimage3) {
		this.uimage3 = uimage3;
	}

	public String getUnickname3() {
		return unickname3;
	}

	public void setUnickname3(String unickname3) {
		this.unickname3 = unickname3;
	}

	public String getUatid3() {
		return uatid3;
	}

	public void setUatid3(String uatid3) {
		this.uatid3 = uatid3;
	}

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

	public String getUimage() {
		return uimage;
	}

	public void setUimage(String uimage) {
		this.uimage = uimage;
	}

	public String getUnickname() {
		return unickname;
	}

	public void setUnickname(String unickname) {
		this.unickname = unickname;
	}

	public String getUatid() {
		return uatid;
	}

	public void setUatid(String uatid) {
		this.uatid = uatid;
	}

	public String getBregdate() {
		return bregdate;
	}

	public void setBregdate(String bregdate) {
		this.bregdate = bregdate;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBattach() {
		return battach;
	}

	public void setBattach(String battach) {
		this.battach = battach;
	}

	public int getBlikecount() {
		return blikecount;
	}

	public void setBlikecount(int blikecount) {
		this.blikecount = blikecount;
	}

	public int getBreplycount() {
		return breplycount;
	}

	public void setBreplycount(int breplycount) {
		this.breplycount = breplycount;
	}

	public int getBquotecount() {
		return bquotecount;
	}

	public void setBquotecount(int bquotecount) {
		this.bquotecount = bquotecount;
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

}