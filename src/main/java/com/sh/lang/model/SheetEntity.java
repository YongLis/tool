package com.sh.lang.model;

import java.util.List;

public class SheetEntity {
	private String sheetName;
	private String[] title;
	private List<String[]> dataList;
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String[] getTitle() {
		return title;
	}
	public void setTitle(String[] title) {
		this.title = title;
	}
	public List<String[]> getDataList() {
		return dataList;
	}
	public void setDataList(List<String[]> dataList) {
		this.dataList = dataList;
	}
	
	

}
