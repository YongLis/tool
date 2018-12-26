/*******************************************************************************
 * Copyright (c) 2014 Aemoney Corporation. All rights reserved.
 * Aemoney PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Http://www.aemoney.com
 *******************************************************************************/

package com.sh.lang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间工具类
 */

public class DateUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

	/** 时间格式:yyyyMMdd */
	public static final String YYYYMMDD = "yyyyMMdd";

	/** 时间格式:yyyyMMddHHmmss */
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	
	/** 时间格式:yyyyMMddHHmmsssss */
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmsssss";

	/** 时间格式:yyyy-MM-dd */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	/** 时间格式:yyyyMMdd HH:mm:ss */
	public static final String YYYYMMDD_HHMMSS = "yyyyMMdd HH:mm:ss";

	/** 时间格式:yyyy-MM-dd HH:mm:ss */
	public static final String YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	
	/** 时间格式:HHmmss */
	public static final String HHmmss = "HHmmss";

	/**
	 * 日期格式转换
	 * 
	 * @param dateStr
	 * @param srcFormat
	 * @param destFormat
	 * @return
	 * @throws ParseException
	 */
	public static String convertDateFormat(String dateStr, String srcFormat,
			String destFormat) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(srcFormat);
			Date date = dateFormat.parse(dateStr);
			SimpleDateFormat dateFormat2 = new SimpleDateFormat(destFormat);
			return dateFormat2.format(date);
		} catch (ParseException e) {
			LOGGER.error("错误：",e);
		}
		return null;
	}

	/**
	 * 获取系统当前日期
	 * 
	 * @param format
	 * @return 返回字符串
	 */
	public static String getCurrentDate(String format) {
		if (StringUtil.isEmpty(format)) {
			format = YYYYMMDD;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}

	/**
	 * 
	 * 根据天，计算时间
	 * 
	 * @param date Date
	 * @param format
	 * @param day
	 * @return
	 */
	public static String calcuateDate(Date date, String format, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		Date time = calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(time);
	}
	
	/**
	 * 
	 * 根据天，计算时间
	 * 
	 * @param date String
	 * @param format
	 * @param day
	 * @return
	 */
	public static String calcuateDate(String date, String format, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(string2Date(date, format));
		calendar.add(Calendar.DATE, day);
		Date time = calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(time);
	}

	/**
	 * 
	 * 根据月，计算时间
	 * 
	 * @param date
	 * @param format
	 * @param month
	 * @return
	 */
	public static String calcuateMonth(Date date, String format, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		Date time = calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(time);
	}

	/**
	 * 
	 * 根据年，计算时间
	 * 
	 * @param date
	 * @param format
	 * @param month
	 * @return
	 */
	public static String calcuateYear(Date date, String format, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		Date time = calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(time);
	}

	/**
	 * 
	 * String转换成date
	 * 
	 * @param date
	 *            格式：YYYYMMDD
	 * @return
	 */
	public static Date date2String(String date, String format) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.parse(date);
		} catch (ParseException e) {
		    LOGGER.error("错误：",e);
		}
		return null;
	}

	public static void main(String[] args) {
		String calcuateDate2 = calcuateDate("20150407000000", YYYYMMDDHHMMSS, 1);
		System.out.println(calcuateDate2);
	}

	/**
	 * 格式化日期，默认使用yyyyMMdd格式
	 * 
	 * @param date
	 * */
	public static String date2String(Date date) {
		return date2String(date, YYYYMMDD);
	}

	/**
	 * 格式化日期，使用指定的日期格式
	 * 
	 * @param date
	 * @param format
	 * */
	public static String date2String(Date date, String format) {
		if (date != null) {
			if (StringUtils.isBlank(format)) {
				format = YYYYMMDD;
			}
			return new SimpleDateFormat(format).format(date);
		}
		return null;
	}

	/**
	 * 
	 * 格式化日期，使用指定的日期格式
	 * 
	 * @param dateStr
	 * @param format
	 * @throws ParseException
	 * */
	public static Date string2Date(String dateStr, String format) {
		if (dateStr != null) {
			try {
				Date date = new SimpleDateFormat(format).parse(dateStr);
				return date;
			} catch (ParseException e) {
			    LOGGER.error("错误：",e);
			}
		}
		return null;
	}
	
	/**
	 * 在开始时间点后面加上00:00:00
	 *
	 * @return
	 */
	public static Date addStartTime(Date startDate) {
		return string2Date(addStartTime2String(startDate), YYYY_MM_DD_HHMMSS);
	}
	
	   /**
     * 在结束时间点后面加上23:59:59
     *
     * @return
     */
    public static Date addEndTime(Date endDate) {
        return string2Date(addEndTime2String(endDate), YYYY_MM_DD_HHMMSS);
    }
    
    /**
     * 在开始时间点后面加上00:00:00
     *
     * @return
     */
    public static String addStartTime2String(Date startDate) {
    	return date2String(startDate, YYYY_MM_DD) + " 00:00:00";
    }
    
    /**
     * 在结束时间点后面加上23:59:59
     *
     * @return
     */
    public static String addEndTime2String(Date endDate) {
    	return date2String(endDate, YYYY_MM_DD) + " 23:59:59";
    }
}
