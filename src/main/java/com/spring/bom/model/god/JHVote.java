package com.spring.bom.model.god;

public class JHVote {
	private int vcode;
	private String vselect1;
	private String vselect2;
	private String vselect3;
	private String vselect4;
	private String vendtime;
	private int vmulti;
	
	//조회용
	private int date;
	private int hour;
	private int min;
	
	
	//?
	private int myvcode;
	
	public int getMyvcode() {
		return myvcode;
	}
	public void setMyvcode(int myvcode) {
		this.myvcode = myvcode;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getVcode() {
		return vcode;
	}
	public void setVcode(int vcode) {
		this.vcode = vcode;
	}
	public String getVselect1() {
		return vselect1;
	}
	public void setVselect1(String vselect1) {
		this.vselect1 = vselect1;
	}
	public String getVselect2() {
		return vselect2;
	}
	public void setVselect2(String vselect2) {
		this.vselect2 = vselect2;
	}
	public String getVselect3() {
		return vselect3;
	}
	public void setVselect3(String vselect3) {
		this.vselect3 = vselect3;
	}
	public String getVselect4() {
		return vselect4;
	}
	public void setVselect4(String vselect4) {
		this.vselect4 = vselect4;
	}
	public String getVendtime() {
		return vendtime;
	}
	public void setVendtime(String vendtime) {
		this.vendtime = vendtime;
	}
	public int getVmulti() {
		return vmulti;
	}
	public void setVmulti(int vmulti) {
		this.vmulti = vmulti;
	}
	
}
