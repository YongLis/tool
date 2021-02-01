package com.sh.cp;

import java.io.Serializable;

public class CpResult implements Serializable{
	private static final long serialVersionUID = -4047839256069865236L;
	private String redball1;
	private String redball2;
	private String redball3;
	private String redball4;
	private String redball5;
	private String redball6;
	private String blueball;
	
	public CpResult(){}
	public CpResult(String redball1, String redball2, String redball3, String redball4, String redball5,String redball6, String blueball){
		this.redball1 = redball1;
		this.redball2 = redball2;
		this.redball3 = redball3;
		this.redball4 = redball4;
		this.redball5 = redball5;
		this.redball6 = redball6;
		this.blueball = blueball;
	}
	
	public String getRedball1() {
		return redball1;
	}
	public void setRedball1(String redball1) {
		this.redball1 = redball1;
	}
	public String getRedball2() {
		return redball2;
	}
	public void setRedball2(String redball2) {
		this.redball2 = redball2;
	}
	public String getRedball3() {
		return redball3;
	}
	public void setRedball3(String redball3) {
		this.redball3 = redball3;
	}
	public String getRedball4() {
		return redball4;
	}
	public void setRedball4(String redball4) {
		this.redball4 = redball4;
	}
	public String getRedball5() {
		return redball5;
	}
	public void setRedball5(String redball5) {
		this.redball5 = redball5;
	}
	public String getRedball6() {
		return redball6;
	}
	public void setRedball6(String redball6) {
		this.redball6 = redball6;
	}
	public String getBlueball() {
		return blueball;
	}
	public void setBlueball(String blueball) {
		this.blueball = blueball;
	}
	
	
	public String toString(){
		return this.redball1+","+this.redball2+","+this.redball3+","+this.redball4+","+this.redball5+","+this.redball6+","+this.blueball;
	}

}
