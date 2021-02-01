package com.dataeden;

import java.io.Serializable;

public class Trade implements Serializable {
	private String code;  // 贸易代码
	private String item;  // 贸易参数集合
	
	public Trade (String code, String item){
		this.code = code;
		this.item = item;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	

}
