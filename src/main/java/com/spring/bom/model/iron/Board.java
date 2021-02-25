package com.spring.bom.model.iron;

public class Board {
	//Mirror - 디비 컬럼 데이터
	private int bcode; // 글 코드
	private int ucode; // 작성 회원 코드
	private String bcontent; // 글 내용
	private String bregDate; // 글 등록시간, 스크랩 경우 스크랩글의 작성 시간을 가져옴/ 글예약시 설정한 시간대로 설정
	private String btype; // 글타입 : normal, reply, scrap, quote
	private int banchor; // 고정글인지 0,1
	private String bpermission; // 댓글 작성 범위 구분 : all, follower, nobody
	private String bbcode; // scrap or quote 시 부여
	private String battach; // attach photo or movie
	private int bvoteCode; // 투표코드
	private int btmpSav; // 임시저장여부
	private String btmpSavTime; // 임시저장된 시각
	private int blikeCount; // 좋아요 횟수
	private int bexpCount; // 노출 횟수
	private int bjoinCount; // 글 참여수
	private int bstate; // 글상태 -> 0: 삭제, 1: 정상, 2: 차단
	private int breportCount; // 글 신고 횟수
	
	//Extra DB column data
	private int loginUcode;
	private String uatid;
	private String unickName;
	private String uimage;
	
	public String getUimage() {
		return uimage;
	}
	public void setUimage(String uimage) {
		this.uimage = uimage;
	}
	//Mirror getter/setter
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
	public String getBregDate() {
		return bregDate;
	}
	public void setBregDate(String bregDate) {
		this.bregDate = bregDate;
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
	public String getBbcode() {
		return bbcode;
	}
	public void setBbcode(String bbcode) {
		this.bbcode = bbcode;
	}
	public String getBattach() {
		return battach;
	}
	public void setBattach(String battach) {
		this.battach = battach;
	}
	public int getBvoteCode() {
		return bvoteCode;
	}
	public void setBvoteCode(int bvoteCode) {
		this.bvoteCode = bvoteCode;
	}
	public int getBtmpSav() {
		return btmpSav;
	}
	public void setBtmpSav(int btmpSav) {
		this.btmpSav = btmpSav;
	}
	public String getBtmpSavTime() {
		return btmpSavTime;
	}
	public void setBtmpSavTime(String btmpSavTime) {
		this.btmpSavTime = btmpSavTime;
	}
	public int getBlikeCount() {
		return blikeCount;
	}
	public void setBlikeCount(int blikeCount) {
		this.blikeCount = blikeCount;
	}
	public int getBexpCount() {
		return bexpCount;
	}
	public void setBexpCount(int bexpCount) {
		this.bexpCount = bexpCount;
	}
	public int getBjoinCount() {
		return bjoinCount;
	}
	public void setBjoinCount(int bjoinCount) {
		this.bjoinCount = bjoinCount;
	}
	public int getBstate() {
		return bstate;
	}
	public void setBstate(int bstate) {
		this.bstate = bstate;
	}
	public int getBreportCount() {
		return breportCount;
	}
	public void setBreportCount(int breportCount) {
		this.breportCount = breportCount;
	}
	
	//Extra getter/setter
	public int getLoginUcode() {
		return loginUcode;
	}
	public void setLoginUcode(int loginUcode) {
		this.loginUcode = loginUcode;
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
	
}
