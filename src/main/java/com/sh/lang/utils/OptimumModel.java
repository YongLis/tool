package com.sh.lang.utils;

import java.io.Serializable;
import java.math.BigDecimal;

public class OptimumModel implements Serializable, Comparable<OptimumModel>{
	private String nodeId;
	private BigDecimal nodeValue;
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public BigDecimal getNodeValue() {
		return nodeValue;
	}
	public void setNodeValue(BigDecimal nodeValue) {
		this.nodeValue = nodeValue;
	}
	@Override
	public int compareTo(OptimumModel o) {
		// TODO Auto-generated method stub
		return o.getNodeValue().compareTo(this.nodeValue);
	}

}
