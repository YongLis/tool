package com.dataeden;

import java.io.Serializable;

public class Device implements Serializable {
	private String fingerPrintId;

	public String getFingerPrintId() {
		return fingerPrintId;
	}

	public void setFingerPrintId(String fingerPrintId) {
		this.fingerPrintId = fingerPrintId;
	}
	
}
