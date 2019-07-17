package com.sh.lang.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 主键生成器 
 */
public class IDGenerateUtil {
	
	/**
	 * 返回固定长度的数字字符串
	 * @param length 长度
	 * @return String 
	 */
	public static String getFixLengthNumbericString(int length){
		return RandomStringUtils.randomNumeric(length);
	}
	
	/**
	 * 返回普通的字符串（格式yyyyMMddHHmmss+6位随机数）
	 * @param length 长度
	 * @return String 
	 */
	public static String getFixLengthNumbericString(){
		return DateUtil.getCurrentDate(DateUtil.YYYYMMDDHHMMSS)+RandomStringUtils.randomNumeric(6);
	}
	
}
