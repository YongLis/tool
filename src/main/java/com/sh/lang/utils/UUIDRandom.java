package com.sh.lang.utils;

import java.util.UUID;

public class UUIDRandom {
	private UUIDRandom() {
	}
	/**
	 * 取得一个UUID的方法
	 * 
	 * @return 一个UUID
	 */
	public static String generate() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	public static void main(String[] args) {
		for(int i=0; i<5000000; i++){
			System.out.println(UUIDRandom.generate());
		}
	}
}
