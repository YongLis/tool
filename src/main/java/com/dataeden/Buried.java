package com.dataeden;

import java.io.Serializable;

public class Buried implements Serializable{
	private String code;   // 埋点统计模型编码
	private String item;   // 埋点数据
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
