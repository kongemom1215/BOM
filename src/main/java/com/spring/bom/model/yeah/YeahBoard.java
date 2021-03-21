package com.spring.bom.model.yeah;

public class YeahBoard {
	int bcode; // 글코드
	int ucode; // 회원코드
	String bcontent; // 글 내용
	String bregdate; // 글 작성시간
	String btype; // 글 종류
	int banchor; // 고정글
	String bpermission; // 답글 권한
	int bbcode; // 스크랩,인용,댓글 글코드
	String battach; // 첨부파일
	int bvotecode; // 투표코드
	int bsaveorrsvd;
	int blikecount; // 좋아요 횟수
	int bexpcount; // 글 노출수
	int bjoincount; // 글 참여수
	int breplycount; // 댓글 갯수
	int bquotecount; // 스크랩 / 인용 갯수

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

	public int getBcode() {
		return bcode;
	}

	public void setBcode(int bcode) {
		this.bcode = bcode;
	}

	public int getUcode() {
		return ucode;
	}

	public void setUcode(int ucode) {
		this.ucode = ucode;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBregdate() {
		return bregdate;
	}

	public void setBregdate(String bregdate) {
		this.bregdate = bregdate;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public int getBanchor() {
		return banchor;
	}

	public void setBanchor(int banchor) {
		this.banchor = banchor;
	}

	public String getBpermission() {
		return bpermission;
	}

	public void setBpermission(String bpermission) {
		this.bpermission = bpermission;
	}

	public int getBbcode() {
		return bbcode;
	}

	public void setBbcode(int bbcode) {
		this.bbcode = bbcode;
	}

	public String getBattach() {
		return battach;
	}

	public void setBattach(String battach) {
		this.battach = battach;
	}

	public int getBvotecode() {
		return bvotecode;
	}

	public void setBvotecode(int bvotecode) {
		this.bvotecode = bvotecode;
	}

	public int getBsaveorrsvd() {
		return bsaveorrsvd;
	}

	public void setBsaveorrsvd(int bsaveorrsvd) {
		this.bsaveorrsvd = bsaveorrsvd;
	}

	public int getBlikecount() {
		return blikecount;
	}

	public void setBlikecount(int blikecount) {
		this.blikecount = blikecount;
	}

	public int getBexpcount() {
		return bexpcount;
	}

	public void setBexpcount(int bexpcount) {
		this.bexpcount = bexpcount;
	}

	public int getBjoincount() {
		return bjoincount;
	}

	public void setBjoincount(int bjoincount) {
		this.bjoincount = bjoincount;
	}

}
