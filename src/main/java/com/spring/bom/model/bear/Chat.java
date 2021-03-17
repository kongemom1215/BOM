package com.spring.bom.model.bear;

public class Chat {
	//ucode : 회원코드 , ccode: 쪽지방코드 , uopcode : 상대방코드 , udelstate : 삭제여부
	private int ucode,ccode,udelstate,uopcode ;
	// cdmessage : 대화 메세지 , 톡 시간 , 유저이미지, 유저아이디 
	private String cdmessage , cdtime , uimage, uatid;
	
	public int getUcode() {
		return ucode;
	}
	public void setUcode(int ucode) {
		this.ucode = ucode;
	}
	public int getCcode() {
		return ccode;
	}
	public void setCcode(int ccode) {
		this.ccode = ccode;
	}
	public int getUopcode() {
		return uopcode;
	}
	public void setUopcode(int uopcode) {
		this.uopcode = uopcode;
	}
	public int getUdelstate() {
		return udelstate;
	}
	public void setUdelstate(int udelstate) {
		this.udelstate = udelstate;
	}
	public String getCdmessage() {
		return cdmessage;
	}
	public void setCdmessage(String cdmessage) {
		this.cdmessage = cdmessage;
	}
	public String getCdtime() {
		return cdtime;
	}
	public void setCdtime(String cdtime) {
		this.cdtime = cdtime;
	}
	public String getUimage() {
		return uimage;
	}
	public void setUimage(String uimage) {
		this.uimage = uimage;
	}
	public String getUatid() {
		return uatid;
	}
	public void setUatid(String uatid) {
		this.uatid = uatid;
	} 
	
	
	
	

}
