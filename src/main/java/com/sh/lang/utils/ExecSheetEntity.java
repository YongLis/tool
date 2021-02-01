package com.sh.lang.utils;

import java.util.List;

/**
 * @Auther: liyong
 * @Date:2019/3/19 12:11
 * @Desc: the description of class
 */
public class ExecSheetEntity {
    private String sheetName;       // sheet名称
    private List<String> title;         // 列标题
    private List<List<String>> dataList;    // 行数据

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

	public List<String> getTitle() {
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	public List<List<String>> getDataList() {
		return dataList;
	}

	public void setDataList(List<List<String>> dataList) {
		this.dataList = dataList;
	}

    
}