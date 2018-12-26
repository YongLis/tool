package com.sh.lang.jedis;

import java.util.HashMap;

public enum RedisType {
	CLUSTER("cluster", "集群模式"), MASTER_SLAVE("master_slave", "主从模式");

	private String code;
	private String desc;

	RedisType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	static {
		HashMap<String, RedisType> map = new HashMap<String, RedisType>();
		for (RedisType tmp : RedisType.values()) {
			map.put(tmp.getCode(), tmp);
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
