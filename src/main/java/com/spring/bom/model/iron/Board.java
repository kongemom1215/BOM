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
	private int breplyCount;	// 댓글 횟수
	private int bquoteCount;	// 인용 횟수
	
	//Extra DB column data
	private int loginUcode;
	private String uatid;
	private String unickName;
	private String uimage;
	private String ltype; //좋아요 여부
	private String bbtype; //북마크 여부
	private String ldate; //좋아요 일시
	private String bdate; //북마크 일시
	private String orderData; //정렬용
	
	//Not DTO
	private String battachType;
	private String battachSrc;
	//For Quote
	private String q_uimage;
	private String q_nickname;
	private String q_atid;
	private String q_content;
	private String q_attach;
	private String q_attachsrc;
	private String q_attachtype;
	private String q_regdate;
	
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
	public int getBreplyCount() {
		return breplyCount;
	}
	public void setBreplyCount(int breplyCount) {
		this.breplyCount = breplyCount;
	}
	public int getBquoteCount() {
		return bquoteCount;
	}
	public void setBquoteCount(int bquoteCount) {
		this.bquoteCount = bquoteCount;
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
	public String getOrderData() {
		return orderData;
	}
	public void setOrderData(String orderData) {
		this.orderData = orderData;
	}
	//For quote getter/setter
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
