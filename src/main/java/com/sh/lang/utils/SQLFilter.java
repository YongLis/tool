package com.sh.lang.utils;

import java.util.regex.Pattern;

/**
 * 检查SQL输入是否合法，防止SQL注入
 * 
 * @author 孙鑫
 * @version 1.0
 * @since 2014/09/15
 * */
public class SQLFilter {

	private static String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
			+ "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";

	private static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);

	/**
	 * 判断SQL是否合法
	 * 
	 * @param sql
	 */
	public static boolean isSQLInject(String sql) {
		if (sqlPattern.matcher(sql).find()) {
			return true;
		}
		return false;
	}
}
