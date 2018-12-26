package com.sh.lang.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

public class SqlUtil {
	/**
	 * true 不合法
	 * false 合法
	 * @param sql
	 * @return
	 */
	public static boolean isSQLInject(String sql) {
		return SQLFilter.isSQLInject(sql);
	}
	/**
	 * true 不合法
	 * false 合法
	 * @param sql
	 * @return
	 */
	public static boolean isSQLInject(List<String> sqls) {
		for (Iterator iterator = sqls.iterator(); iterator.hasNext();) {
			String sql = (String) iterator.next();
			if(SQLFilter.isSQLInject(sql)){
				return true;
			}
		}
		return false;
	}
	public static String in(List<String> strs, boolean isString) {
		if (strs.isEmpty()) {
			return "('')";
		}
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		if (isString) {
			builder.append("'").append(StringUtils.join(strs, "','"))
					.append("'");
		} else {
			builder.append(StringUtils.join(strs, "','"));
		}
		builder.append(")");
		return builder.toString();
	}
	
	public static String genInCondition(List<Long> collect) {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (Long s : collect) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(String.valueOf(s));
			i++;
		}
		return sb.toString();
	}
	   public static String genInCondition2(List<String> collect) {
	        StringBuffer sb = new StringBuffer();
	        int i = 0;
	        for (String s : collect) {
	            if (i > 0) {
	                sb.append(",");
	            }
	            sb.append(s.toString());
	            i++;
	        }
	        return sb.toString();
	    }
	/**
     * 将数据库查询出来的数据的字段名替换成驼峰形式
     * 
     * @param Map
     */
    public static Map convertSqlResult2CamelCase(Map data) {
        if (MapUtils.isNotEmpty(data)) {
            try {
                // 保留原来map的类型
                Map newMap = data.getClass().newInstance();
                for (Object key : data.keySet()) {
                    newMap.put(StringUtil.underScore2CamelCase(key.toString()), data.get(key));
                }
                return newMap;
            } catch (Exception e) {
                // 如果发生错误直接返回原map
                return data;
            }
        }
        return data;
    }

    /**
     * 将数据库查询出来的数据的字段名替换成驼峰形式
     * 
     * @param List
     */
    public static List<Map> convertSqlResult2CamelCase(List<Map> dataList) {
        if (CollectionUtils.isNotEmpty(dataList)) {
            List<Map> newList = new ArrayList<Map>();
            for (Map data : dataList) {
                newList.add(convertSqlResult2CamelCase(data));
            }
            return newList;
        }
        return dataList;
    }

	public static void main(String[] args) {
		List<String> strs = new ArrayList<String>();
		strs.add("1");
		strs.add("and");
		System.out.println(StringUtils.join(strs, "','"));
		System.out.println(SqlUtil.in(strs, false));
		System.out.println(SqlUtil.isSQLInject("and"));
		System.out.println(SqlUtil.isSQLInject(strs));
	}
}
