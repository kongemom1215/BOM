package com.spring.bom.model.yeah;

public class YeahFollow {
	private int ucode;
	private int fopcode; // 팔로우 한 상대
	private String ftime; // 팔로우 시간
	private int fblockstate; // 차단여부

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

}
