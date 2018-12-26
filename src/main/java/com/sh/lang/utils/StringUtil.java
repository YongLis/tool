package com.sh.lang.utils;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class StringUtil {

	public static final String EMPTY_STRING = "";

	public static boolean isBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return true;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	public static String repeat(String str, int repeat) {
		if (str == null) {
			return null;
		}

		if (repeat <= 0) {
			return EMPTY_STRING;
		}

		int inputLength = str.length();

		if ((repeat == 1) || (inputLength == 0)) {
			return str;
		}

		int outputLength = inputLength * repeat;

		switch (inputLength) {
		case 1:

			char ch = str.charAt(0);
			char[] output1 = new char[outputLength];

			for (int i = repeat - 1; i >= 0; i--) {
				output1[i] = ch;
			}

			return new String(output1);

		case 2:

			char ch0 = str.charAt(0);
			char ch1 = str.charAt(1);
			char[] output2 = new char[outputLength];

			for (int i = (repeat * 2) - 2; i >= 0; i--, i--) {
				output2[i] = ch0;
				output2[i + 1] = ch1;
			}

			return new String(output2);

		default:

			StringBuffer buf = new StringBuffer(outputLength);

			for (int i = 0; i < repeat; i++) {
				buf.append(str);
			}

			return buf.toString();
		}
	}

	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

	public static boolean equals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}

		return str1.equals(str2);
	}

	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	public static String replace(String text, String repl, String with) {
		return replace(text, repl, with, -1);
	}

	public static String replace(String text, String repl, String with, int max) {
		if ((text == null) || (repl == null) || (with == null) || (repl.length() == 0) || (max == 0)) {
			return text;
		}

		StringBuffer buf = new StringBuffer(text.length());
		int start = 0;
		int end = 0;

		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();

			if (--max == 0) {
				break;
			}
		}

		buf.append(text.substring(start));
		return buf.toString();
	}

	public static String substringBetween(String str, String tag) {
		return substringBetween(str, tag, tag, 0);
	}

	public static String substringBetween(String str, String open, String close) {
		return substringBetween(str, open, close, 0);
	}

	public static String substringBetween(String str, String open, String close, int fromIndex) {
		if ((str == null) || (open == null) || (close == null)) {
			return null;
		}

		int start = str.indexOf(open, fromIndex);

		if (start != -1) {
			int end = str.indexOf(close, start + open.length());

			if (end != -1) {
				return str.substring(start + open.length(), end);
			}
		}

		return null;
	}

	public static boolean isNotBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	public static boolean contains(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return false;
		}

		return str.indexOf(searchStr) >= 0;
	}

	public static String fillChar(String str, int length, boolean isLeft, String specialChar) {

		String returnValue = null;

		if (str == null)
			return returnValue;

		int diff = length - str.getBytes().length;

		if (diff == 0) {
			returnValue = str;
		} else if (diff > 0) {
			StringBuffer temp = new StringBuffer();
			while (diff > 0) {
				temp.append(specialChar);
				diff--;
			}

			if (isLeft) {
				returnValue = temp + str;
			} else {
				returnValue = str + temp;
			}
		}
		return returnValue;
	}

	public static boolean isNotEmpty(String str) {
		if (str == null || str.length() < 1)
			return false;
		if (str.equals(""))
			return false;
		if (str.trim().equals(""))
			return false;
		if (str.trim().equals("null"))
			return false;
		if (str.trim().equals("NULL"))
			return false;
		if (str.trim().equals("undefined"))
			return false;

		return true;

	}

	public static String alignLefts(String str, int size, String padStr) {
		if (str == null) {
			return null;
		}

		if ((padStr == null) || (padStr.length() == 0)) {
			padStr = " ";
		}

		int padLen = padStr.length();
		int strLen = str.getBytes().length;
		int pads = size - strLen;

		if (pads <= 0) {
			return str;
		}

		if (pads == padLen) {
			return str.concat(padStr);
		} else if (pads < padLen) {
			return str.concat(padStr.substring(0, pads));
		} else {
			char[] padding = new char[pads];
			char[] padChars = padStr.toCharArray();

			for (int i = 0; i < pads; i++) {
				padding[i] = padChars[i % padLen];
			}

			return str.concat(new String(padding));
		}
	}

	public static String alignRights(String str, int size, String padStr) {

		if (str == null) {
			return null;
		}

		if ((padStr == null) || (padStr.length() == 0)) {
			padStr = " ";
		}

		int padLen = padStr.length();
		int strLen = str.getBytes().length;
		int pads = size - strLen;

		if (pads <= 0) {
			return str;
		}

		if (pads == padLen) {
			return padStr.concat(str);
		} else if (pads < padLen) {
			return padStr.substring(0, pads).concat(str);
		} else {
			char[] padding = new char[pads];
			char[] padChars = padStr.toCharArray();

			for (int i = 0; i < pads; i++) {
				padding[i] = padChars[i % padLen];
			}

			return new String(padding).concat(str);
		}
	}

	public static String substringByByte(String str, int start, int end) {

		if (str == null) {
			return null;
		}

		int length = end - start;
		byte[] dest = new byte[length];
		System.arraycopy(str.getBytes(), start, dest, 0, length);

		return new String(dest);
	}

	/**
	 * 根据参数列表动态替换字符串模板中的变量内容
	 * */
	public static String composeString(String template, Map<String, Object> data) {
		if (StringUtils.isNotBlank(template)) {
			String regex = "\\$\\{(.+?)\\}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(template);

			StringBuffer sb = new StringBuffer();
			while (matcher.find()) {
				String name = matcher.group(1);
				String value = null;
				if (MapUtils.isNotEmpty(data)) {
					value = data.get(name) == null ? null : data.get(name).toString();
				}
				if (value == null) {
					value = "";
				} else {
					value = value.replaceAll("\\$", "\\\\\\$");
				}
				matcher.appendReplacement(sb, value);
			}
			matcher.appendTail(sb);
			return sb.toString();
		}
		return null;
	}
	
	public static String[] split(String source, String regex) {
		if (isNotEmpty(source)) {
			return source.split(regex);
		}
		return null;
	}
	
    public static String null2String(String str) {
		return str == null ? "" : str;
    }
    
    /**
     * 取指定字符串的子串。    
     * 
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        // handle negatives
        if (end < 0) {
            end = str.length() + end; // remember end is negative
        }
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        // check length next
        if (end > str.length()) {
            end = str.length();
        }

        // if start is greater than end, return ""
        if (start > end) {
            return "";
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }
    
    /**
     * 根据长度length把字符串切成两段，保存数组
     * 确保中文不要被切成两半
     * 
     */
    public static String[] cutString(String message, int length) {
        String normal = substring(message, 0, length);
        if (isContainChinese(message, length)) {
            normal = substring(message, 0, length - 1);
        }

        return new String[] { normal, StringUtils.substringAfter(message, normal) };
    }
    
    /**
     * 字符串是否包含中文
     * 
     */
    public static boolean isContainChinese(String message) {
        char[] chars = message.toCharArray();
        byte[] bytes;
        try {
            bytes = message.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            bytes = message.getBytes();
        }
        return (chars.length != bytes.length);
    }
    
    /**
     * 字符串起始长度length的字符串是否包含中文
     * 
     */
    public static boolean isContainChinese(String message, int length) {
        char[] chars = substring(message, 0, length).toCharArray();
        char[] charsPlus = substring(message, 0, length + 1).toCharArray();
        return ArrayUtils.isSameLength(chars, charsPlus);
    }
    
    /**
     * 过滤要输出到xml的字符串，将<,>,&,"进行转义输出
     * 
     */
    public static String filterXMLString(String input) {
        if (input == null) {
            return EMPTY_STRING;
        }
        int length = input.length();
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            switch (c) {
                case '<': {
                    result.append("&lt;");
                    break;
                }
                case '>': {
                    result.append("&gt;");
                    break;
                }
                case '\"': {
                    result.append("&quot;");
                    break;
                }
                case '&': {
                    result.append("&amp;");
                    break;
                }
                default: {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }
    
    /**
     * @param strs
     *            待转化字符串
     * @return
     * @description 下划线格式字符串转换成驼峰格式字符串 eg: player_id -> playerId;<br>
     *              player_name -> playerName;
     */
    public static String underScore2CamelCase(String strs) {
        String[] elems = strs.split("_");
        for (int i = 0; i < elems.length; i++) {
            elems[i] = elems[i].toLowerCase();
            if (i != 0) {
                String elem = elems[i];
                char first = elem.toCharArray()[0];
                elems[i] = "" + (char) (first - 32) + elem.substring(1);
            }
        }
        return StringUtils.join(elems, "");
    }

    /**
     * @param param
     *            待转换字符串
     * @return
     * @description 驼峰格式字符串 转换成 下划线格式字符串 eg: playerId -> player_id;<br>
     *              playerName -> player_name;
     */
    public static String camelCase2Underscore(String param) {
        Pattern p = Pattern.compile("[A-Z]");
        if (param == null || param.equals("")) {
            return "";
        }
        StringBuilder builder = new StringBuilder(param);
        Matcher mc = p.matcher(param);
        int i = 0;
        while (mc.find()) {
            builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
            i++;
        }
        if ('_' == builder.charAt(0)) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    /**
     * 去除空白
     * @param input
     * @return
     */
    public static String[] trimString(String[] input){
        for (int i = 0; i < input.length; i++) {
            input[i] = input[i].replaceAll("\\s*", "");
        }
        return input;
    }
    
    public static final String BASE64_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private static final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
    private static final int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
    private static int[] ai = new int[18];

    private static String[] bigNum = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };

    private static ThreadLocal<HashMap<String, SimpleDateFormat>> threadLocal = new ThreadLocal<HashMap<String, SimpleDateFormat>>();

    public static String format(Long timeMillis, String... format) {
        if (timeMillis == null) {
            return null;
        }
        String formatPattern = null;
        if (format == null || format.length == 0) {
            formatPattern = "yyyy-MM-dd";
        } else {
            formatPattern = format[0];
        }
        SimpleDateFormat sdf = getFormat(formatPattern);
        return sdf.format(new Date(timeMillis));
    }

    public static Long parse(String timeStr, String... format) {
        if (isNullOrEmpty(timeStr)) {
            return null;
        }
        String formatPattern = null;
        if (format == null || format.length == 0) {
            formatPattern = "yyyy-MM-dd";
        } else {
            formatPattern = format[0];
        }
        SimpleDateFormat sdf = getFormat(formatPattern);
        try {
            return sdf.parse(timeStr).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long addYear(Long startTime, int addNum) {
        Calendar bef = Calendar.getInstance();
        bef.setTimeInMillis(startTime);
        bef.add(Calendar.YEAR, addNum);
        return bef.getTimeInMillis();
    }

    public static long addMonth(Long startTime, int addNum) {
        Calendar bef = Calendar.getInstance();
        bef.setTimeInMillis(startTime);
        bef.add(Calendar.MONTH, addNum);
        return bef.getTimeInMillis();
    }

    public static long addWeek(Long startTime, int addNum) {
        Calendar bef = Calendar.getInstance();
        bef.setTimeInMillis(startTime);
        bef.add(Calendar.WEEK_OF_YEAR, addNum);
        return bef.getTimeInMillis();
    }

    public static long addDay(Long startTime, int addNum) {
        Calendar bef = Calendar.getInstance();
        bef.setTimeInMillis(startTime);
        bef.add(Calendar.DAY_OF_MONTH, addNum);
        return bef.getTimeInMillis();
    }

    public static long addHour(Long startTime, int addNum) {
        Calendar bef = Calendar.getInstance();
        bef.setTimeInMillis(startTime);
        bef.add(Calendar.HOUR_OF_DAY, addNum);
        return bef.getTimeInMillis();
    }

    public static long addMinute(Long startTime, int addNum) {
        Calendar bef = Calendar.getInstance();
        bef.setTimeInMillis(startTime);
        bef.add(Calendar.MINUTE, addNum);
        return bef.getTimeInMillis();
    }

    public static String getYear() {
        Calendar bef = Calendar.getInstance();
        return format(bef.getTimeInMillis(), "yyyy");
    }

    public static String getMonth() {
        Calendar bef = Calendar.getInstance();
        return format(bef.getTimeInMillis(), "MM");
    }

    public static Long dealEndTime(Long endTime) {
        return endTime + 1;
    }

    public static Long dealMultEndTime(Long endTime) {
        return endTime - 1;
    }

    public static int calculateMonth(Long startTime, Long endTime1) {
        int iMonth = 0;
        try {
            Long endTime = endTime1 + 1;
            Calendar objCalendarDate1 = Calendar.getInstance();
            objCalendarDate1.setTimeInMillis(startTime);
            Calendar objCalendarDate2 = Calendar.getInstance();
            objCalendarDate2.setTimeInMillis(endTime);
            if (objCalendarDate2.equals(objCalendarDate1))
                return 0;
            /*
             * if (objCalendarDate1.after(objCalendarDate2)){ Calendar temp =
             * objCalendarDate1; objCalendarDate1 = objCalendarDate2; objCalendarDate2 =
             * temp; }
             */
            // 判断月份数不足一个月
            /*
             * Calendar tempMonth = Calendar.getInstance();
             * tempMonth.setTimeInMillis(startTime); tempMonth.add(Calendar.MONTH, 1);
             * if((tempMonth.getTimeInMillis()-1) > objCalendarDate2.getTimeInMillis()){
             * return 0; }
             */
            if (objCalendarDate2.get(Calendar.YEAR) == objCalendarDate1.get(Calendar.YEAR)
                    && objCalendarDate2.get(Calendar.MONTH) == objCalendarDate1.get(Calendar.MONTH)) {
                return 1;
            } else if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR)) {
                iMonth = (objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12
                        + objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH);
            } else {
                iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("iMonth:" + iMonth);
        return iMonth;
    }

    public static int calculateWeek(Long startTime, Long endTime) {
        Long time = endTime - startTime;
        return (int) (time / (7 * 24 * 3600 * 1000));
    }

    public static int calculateHour(Long startTime, Long endTime) {
        Long time = endTime - startTime;
        return (int) (time / (3600 * 1000));
    }

    public static int calculateMinute(Long startTime, Long endTime) {
        Long time = endTime - startTime;
        return (int) (time / (60 * 1000));
    }

    public static Long getMinutesTime(long currentTimeMillis) {
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(currentTimeMillis);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        return time.getTimeInMillis();
    }

    private synchronized static SimpleDateFormat getFormat(String format) {
        HashMap<String, SimpleDateFormat> hashMap = threadLocal.get();
        if (hashMap == null) {
            hashMap = new HashMap<String, SimpleDateFormat>();
            threadLocal.set(hashMap);
        }
        SimpleDateFormat sdf = hashMap.get(format);
        if (sdf == null) {
            sdf = new SimpleDateFormat(format);
            hashMap.put(format, sdf);
        }
        return sdf;
    }

    public static String convertChineseNum(int num) {
        try {
            String strbig = new String("");
            String temp = String.valueOf(num);
            int b = temp.indexOf(".");
            int s = temp.length() - (b + 1);

            int j = b;
            for (int i = 0; i < b; i++) {
                int t = Integer.parseInt(temp.substring(i, i + 1));
                strbig += bigNum[t];
                j--;
            }
            temp = temp.substring(b + 1, temp.length());
            for (int i = 0; i < s; i++) {
                int t = Integer.parseInt(temp.substring(i, i + 1));
                strbig += bigNum[t];
            }
            return strbig;
        } catch (Exception ex) {
        }
        return null;
    }

    public static String mark(String str, int start, int length) {
        StringWriter sw = new StringWriter();
        sw.append(str.substring(0, start));
        for (int i = 0; i < length; i++) {
            sw.append("*");
        }
        sw.append(str.substring(start + length));
        return sw.toString();
    }

    public StringUtil() {
    }

    /**
     * 判断两个字符串是否值相等
     *
     * @param a
     *            设置第一个字符串
     * @param b
     *            设置第二个字符串
     * @return boolean 返回比较的结果
     */
    public static boolean compare(String a, String b) {
        if (isEmpty(a) && isEmpty(b))
            return true;
        if (!isEmpty(a) && !isEmpty(b))
            return a.equals(b);
        else
            return false;
    }

    /**
     * 判断两个字符串是否值相等，忽略大小写
     *
     * @param a
     *            设置第一个字符串
     * @param b
     *            设置第二个字符串
     * @return boolean 返回比较的结果
     */
    public static boolean compareIgnoreCase(String a, String b) {
        if (isEmpty(a) && isEmpty(b))
            return true;
        if (!isEmpty(a) && !isEmpty(b))
            return a.equalsIgnoreCase(b);
        else
            return false;
    }

    /**
     * 复制字符串中从开始到指定的位置
     *
     * @param src
     *            设置字符串
     * @param len
     *            指定复制到某个位置
     * @return String 返回结果
     */
    public static String copy(String src, int len) {
        if (src == null)
            return null;
        if (src.length() > len)
            return len <= 0 ? null : src.substring(0, len);
        else
            return src;
    }

    /**
     * 删除字符串中指定的一段字符串内容
     *
     * @param src
     *            设置原字符串
     * @param delStr
     *            设置需要删除的字符串
     * @return String 返回结果
     */
    public static String delete(String src, String delStr) {
        if (isEmpty(src) || isEmpty(delStr))
            return src;
        StringBuffer buffer = new StringBuffer(src);
        for (int index = src.length(); (index = src.lastIndexOf(delStr, index)) >= 0; index -= delStr.length())
            buffer.delete(index, index + delStr.length());

        return buffer.toString();
    }

    /**
     * 将指定的字符和位置插入到原字符串中
     *
     * @param src
     *            设置原字符串
     * @param anotherStr
     *            设置需要插入的字符串
     * @param offset
     *            位置
     * @return String 返回结果
     */
    public static String insert(String src, String anotherStr, int offset) {
        if (isEmpty(src) || isEmpty(anotherStr))
            return src;
        StringBuffer buffer = new StringBuffer(src);
        if (offset >= 0 && offset <= src.length())
            buffer.insert(offset, anotherStr);
        return buffer.toString();
    }

    /**
     * 追加指定的字符串到原字符串中
     *
     * @param src
     *            设置原字符串
     * @param appendStr
     *            设置需要追加的字符串
     * @return String 返回结果
     */
    public static String append(String src, String appendStr) {
        if (isEmpty(src) || isEmpty(appendStr)) {
            return src;
        } else {
            StringBuffer buffer = new StringBuffer(src);
            buffer.append(appendStr);
            return buffer.toString();
        }
    }

    /**
     * 根据参数替换字符串内容功能
     *
     * @param src
     *            设置原字符串
     * @param oldStr
     *            指定替换字符串
     * @param newStr
     *            将要替换的内容
     * @param isCaseSensitive
     *            是否区分大小写
     * @return String 返回结果
     */
    public static String replace(String src, String oldStr, String newStr, boolean isCaseSensitive) {
        if (isEmpty(src) || isEmpty(oldStr) || newStr == null)
            return src;
        String s = isCaseSensitive ? src : src.toLowerCase();
        String o = isCaseSensitive ? oldStr : oldStr.toLowerCase();
        StringBuffer buffer = new StringBuffer(src);
        for (int index = s.length(); (index = s.lastIndexOf(o, index)) >= 0; index -= o.length())
            buffer.replace(index, index + o.length(), newStr);

        return buffer.toString();
    }

    /**
     * 根据参数替换字符串内容功能，可指定位置
     *
     * @param src
     *            设置原字符串
     * @param oldStr
     *            指定替换字符串
     * @param newStr
     *            将要替换的内容
     * @param isCaseSensitive
     *            是否区分大小写
     * @param index
     *            指定位置
     * @return String 返回结果
     */
    public static String replace(String src, String oldStr, String newStr, boolean isCaseSensitive, int index) {
        if (src == null || src.length() == 0 || oldStr == null || oldStr.length() == 0 || index <= 0)
            return src;
        if (newStr == null)
            newStr = "";
        String s = isCaseSensitive ? src : src.toLowerCase();
        String o = isCaseSensitive ? oldStr : oldStr.toLowerCase();
        StringBuffer buffer = new StringBuffer(src);
        int length = o.length();
        int pos;
        for (pos = s.indexOf(o, 0); pos >= 0; pos = s.indexOf(o, pos + length))
            if (--index == 0)
                break;

        if (pos >= 0 && index == 0)
            buffer.replace(pos, pos + length, newStr);
        return buffer.toString();
    }

    /**
     * 获取指定符号分割的字符串数组，非空
     *
     * @param str
     *            设置原字符串
     * @param delimiter
     *            设置分割符号
     * @return String[] 返回字符串数组
     */
    public static String[] splitWithoutEmpty(String str, String delimiter) {
        ArrayList array = new ArrayList();
        int index = 0;
        int begin = 0;
        String result[] = new String[0];
        if (isEmpty(str))
            return new String[0];
        do {
            index = str.indexOf(delimiter, begin);
            if (index == begin) {
                if (index > 0)
                    array.add("");
                begin += delimiter.length();
                continue;
            }
            if (index <= begin)
                break;
            int end = index;
            array.add(str.substring(begin, end));
            begin = index + delimiter.length();
        } while (true);
        if (begin >= 0 && begin < str.length())
            array.add(str.substring(begin));
        if (array.size() > 0) {
            result = new String[array.size()];
            array.toArray(result);
        }
        return result;
    }

    /**
     * 将指定字符串内容反转
     *
     * @param str
     *            设置原字符串
     * @return String 返回字符串
     */
    public static String reverse(String str) {
        if (isEmpty(str)) {
            return str;
        } else {
            StringBuffer buffer = new StringBuffer(str);
            buffer.reverse();
            return buffer.toString();
        }
    }

    /**
     * 给传入的字符串前后加双引号
     *
     * @param str
     *            设置原字符串
     * @return String 返回结果
     */
    public static String quote(String str) {
        if (isEmpty(str))
            return "\"\"";
        StringBuffer buffer = new StringBuffer(str);
        if (!str.startsWith("\""))
            buffer.insert(0, "\"");
        if (!str.endsWith("\""))
            buffer.append("\"");
        return buffer.toString();
    }

    /**
     * 去除字符串中的双引号
     *
     * @param str
     *            设置原字符串
     * @return String 返回结果
     */
    public static String extractQuotedStr(String str) {
        if (isEmpty(str))
            return str;
        StringBuffer buffer = new StringBuffer(str);
        int index = str.length();
        while (buffer.charAt(buffer.length() - 1) == '"') {
            buffer.deleteCharAt(buffer.length() - 1);
            index = buffer.length();
            if (index <= 0)
                break;
        }
        if (buffer.length() == 0)
            return "";
        while (buffer.charAt(0) == '"') {
            buffer.deleteCharAt(0);
            index = buffer.length();
            if (index <= 0)
                break;
        }
        return buffer.toString();
    }

    /**
     * 截取字符串中到指定的字符的内容，从左边开始
     *
     * @param str
     *            设置原字符串
     * @param c
     *            设置指定字符
     * @return String 返回结果
     */
    public static String strChar(String str, char c) {
        if (str == null || str.length() == 0)
            return null;
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == c)
                return str.substring(i);

        return null;
    }

    /**
     * 截取字符串中到指定的字符的内容，从右边开始
     *
     * @param str
     *            设置原字符串
     * @param c
     *            设置指定字符
     * @return String 返回结果
     */
    public static String strRChar(String str, char c) {
        if (str == null || str.length() == 0)
            return null;
        for (int i = str.length() - 1; i >= 0; i--)
            if (str.charAt(i) == c)
                return str.substring(i);

        return null;
    }

    /**
     * 将Object对象数组转成字符串数组
     *
     * @param array
     *            设置Object对象数组
     * @return String[] 返回结果
     */
    public static String[] toArray(Object array[]) {
        if (array == null || array.length == 0)
            return null;
        String result[] = new String[array.length];
        for (int i = 0; i < array.length; i++)
            if (array[i] != null)
                result[i] = array[i].toString();

        return result;
    }

    /**
     * 将Vector对象数组元素转成字符串数组
     *
     * @param list
     *            设置Vector对象数组
     * @return String[] 返回结果
     */
    public static String[] toArray(Vector list) {
        if (list == null || list.size() == 0)
            return null;
        String result[] = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj != null)
                result[i] = list.get(i).toString();
        }

        return result;
    }

    /**
     * 将字符串数组复制到LIST中
     *
     * @param array
     *            设置字符串数组
     * @param list
     *            设置LIST集合对象
     * @param index
     *            设置复制到LIST位置
     * @return List 返回结果
     */
    public static List copyToList(String array[], List list, int index) {
        if (array == null || array.length == 0)
            return list;
        if (list == null || index < 0)
            return list;
        for (int i = 0; i < array.length; i++)
            if (list.size() <= i + index)
                list.add(index + i, array[i]);
            else
                list.set(index + i, array[i]);

        return list;
    }

    /**
     * 判断字符串数组是否包含指定字符串
     *
     * @param array
     *            设置字符串数组
     * @param str
     *            设置批量字符串
     * @return boolean 返回结果
     */
    public static boolean contains(Object array[], String str) {
        if (array == null || array.length == 0)
            return false;
        if (str == null)
            return false;
        for (int i = 0; i < array.length; i++) {
            Object obj = array[i];
            if (obj != null && str.equals(obj.toString()))
                return true;
        }

        return false;
    }

    /**
     * 获取字符串数组包含指定字符串的位置
     *
     * @param array
     *            设置字符串数组
     * @param str
     *            设置批量字符串
     * @return int 返回结果
     */
    public static int indexOf(Object array[], String str) {
        if (array == null || array.length == 0)
            return -1;
        if (str == null)
            return -1;
        for (int i = 0; i < array.length; i++) {
            Object obj = array[i];
            if (obj != null && str.equals(obj.toString()))
                return i;
        }

        return -1;
    }

    /**
     * 验证是否为电子邮件格式
     *
     * @param theEmail
     *            设置电子邮件地址字符串
     * @return boolean 返回是否合法
     */
    public static boolean isValidEmail(String theEmail) {
        boolean cbool = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(theEmail);
            boolean isMatched = matcher.matches();
            if (isMatched) {
                cbool = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return cbool;
        }
        return cbool;
    }

    /**
     * 去除字符串左边空格
     *
     * @param str
     *            设置原字符串
     * @return String 返回结果
     */
    public static String trimLeft(String str) {
        if (str == null)
            return null;
        int length = str.length();
        if (length == 0)
            return "";
        StringBuffer buffer = new StringBuffer(str);
        int index;
        for (index = 0; index < length && buffer.charAt(index) == ' '; index++)
            ;
        if (index == length)
            return "";
        else
            return buffer.substring(index);
    }

    /**
     * 去除字符串右边空格
     *
     * @param str
     *            设置原字符串
     * @return String 返回结果
     */
    public static String trimRight(String str) {
        if (str == null)
            return null;
        int length = str.length();
        if (length == 0)
            return "";
        StringBuffer buffer = new StringBuffer(str);
        int index;
        for (index = length - 1; index >= 0 && buffer.charAt(index) == ' '; index--)
            ;
        if (index < 0 && buffer.charAt(0) == ' ')
            return "";
        else
            return buffer.substring(0, index + 1);
    }

    /**
     * 去除字符串两边空格
     *
     * @param str
     *            设置原字符串
     * @return String 返回结果
     */
    public static String trimAll(String str) {
        return str.trim();
    }

    /**
     * 去除字符串NULL
     *
     * @param str
     *            设置原字符串
     * @return String 如果为NULL返回空字符串，否则返回原字符串
     */
    public static String removeNull(String str) {
        return str != null ? str : "";
    }

    /**
     * 去除对象NULL
     *
     * @param str
     *            设置原字符串
     * @return String 如果为NULL返回空字符串，否则返回原字符串
     */
    public static String removeNull(Object str) {
        return str != null ? str.toString() : "";
    }

    /**
     * 实现判断字符串是否在数组中存在
     *
     * @param strs
     *            设置字符串数组
     * @param str
     *            设置要查找的字符串
     * @param caseSensitive
     *            设置是否区分大小写
     * @return boolean 返回结果
     */
    public static boolean strInArray(String strs[], String str, boolean caseSensitive) {
        if (strs != null && strs.length > 0) {
            for (int i = 0; i < strs.length; i++) {
                if (caseSensitive) {
                    if (strs[i].equals(str))
                        return true;
                } else {
                    if (strs[i].equalsIgnoreCase(str))
                        return true;
                }
            }
        }

        return false;
    }

    /**
     * 验证身份证的合法性
     *
     * @param idcard
     *            设置身份证字符串
     * @return boolean 返回结果
     */
    public static boolean idCardVerify(String idcard) {
        if (idcard.length() == 15) {
            idcard = idCardUptoeighteen(idcard);
        }
        if (idcard.length() != 18) {
            return false;
        }
        String verify = idcard.substring(17, 18);
        if (verify.equals(getIdCardVerify(idcard))) {
            return true;
        }
        return false;
    }

    /**
     * 获得身份证的合法性
     *
     * @param eightcardid
     *            设置身份证字符串
     * @return String 返回结果
     */
    public static String getIdCardVerify(String eightcardid) {
        int remaining = 0;
        if (eightcardid.length() == 18) {
            eightcardid = eightcardid.substring(0, 17);
        }
        if (eightcardid.length() == 17) {
            int sum = 0;
            for (int i = 0; i < 17; i++) {
                String k = eightcardid.substring(i, i + 1);
                ai[i] = Integer.parseInt(k);
            }
            for (int i = 0; i < 17; i++) {
                sum = sum + wi[i] * ai[i];
            }
            remaining = sum % 11;
        }
        return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
    }

    /**
     * 获得身份证15转18位
     *
     * @param fifteencardid
     *            设置身份证字符串
     * @return String 返回结果
     */
    public static String idCardUptoeighteen(String fifteencardid) {
        if (fifteencardid.length() != 15)
            return null;
        String eightcardid = fifteencardid.substring(0, 6);
        eightcardid = eightcardid + "19";
        eightcardid = eightcardid + fifteencardid.substring(6, 15);
        eightcardid = eightcardid + getIdCardVerify(eightcardid);
        return eightcardid;
    }

    /**
     * 验证电话号码合法格式，格式为02584555112
     *
     * @param phoneCode
     *            设置电话号码字符串
     * @return boolean 返回结果
     */
    public static boolean isPhoneNum(String phoneCode) {
        Pattern p = Pattern.compile("[0][1-9]{2,3}[1-9]{6,8}");
        Matcher m = p.matcher(phoneCode);
        boolean b = m.matches();
        return b;
    }

    /**
     * 字符数组转换为字符串,用逗号隔开
     */
    public static String arrayToString(final String[] str) {
        if (str == null)
            return "";
        StringBuffer rStr = new StringBuffer("");
        for (int i = 0; i < str.length; i++) {
            rStr.append(str[i]);
            rStr.append(",");
        }
        // 截取逗号
        if (rStr.toString().length() > 0) {
            rStr.setLength(rStr.length() - 1);
        }
        return rStr.toString();
    }

    /**
     * 判定一个对象是否为null or empty
     *
     * @param o
     *            对象
     * @return true or false
     */
    public static boolean isNullOrEmpty(Object o) {
        return o == null || String.valueOf(o).trim().length() == 0;
    }

    /**
     * 手机号校验
     * 
     * @param bankcard
     * @return
     */
    public static boolean checkPhone(String mobile) {
        // Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        // Pattern p = Pattern.compile("^1[0-9]{10}$");
        Pattern p = Pattern.compile("^((13[0-9])|(14[0,5-9])|(15[^4,\\D])|(166)|(18[0-9])|(17[0-1,3-8])|(19[8-9]))\\d{8}$");
        Matcher m = p.matcher(mobile);
        if (!m.matches()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 必须由8~16位数字和字符组成
     * 
     * @param password
     * @return
     */
    public static boolean checkPassword(String password) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        return password.matches(regex);
    }

    /**
     * 银行卡位数：8-19位纯数字
     * 
     * @param password
     * @return
     */
    public static boolean checkBankCard(String bankcard) {
        String regex = "^[0-9]{8,19}$";
        return bankcard.matches(regex);
    }

    /**
     * 正整数校验
     * 
     * @param bankcard
     * @return
     */
    public static boolean checkNuber(String pricenumber) {
        String regex = "^[1-9][0-9]*$";
        return pricenumber.matches(regex);
    }

    /**
     * 数字校验
     * 
     * @param bankcard
     * @return
     */
    public static boolean checkisNuber(String pricenumber) {
        String regex = "^[0-9]*$";
        return pricenumber.matches(regex);
    }

    /**
     * 金额小数点后.两位校验
     * 
     * @param bankcard
     * @return
     */
    public static boolean checkPrice(String price) {
        // String regex="^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";
        // return price.matches(regex);
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
        Matcher match = pattern.matcher(price);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * MD5加密
     *
     * @param word
     * @return
     */
    public static String getMD5(String word) {
        byte[] source = null;
        try {
            source = word.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String s = null;
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };// 用来将字节转换成
        // 16
        // 进制表示的字符
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            s = new String(str); // 换后的结果转换为字符串

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static boolean isEqual(String str1, String str2) {
        boolean isEqal = str1 == null && str2 == null;
        if (isEqal) {
            return true;
        }

        return str1 != null ? str1.equals(str2) : str2.equals(str1);
    }

    public static String[] convertStrToArray(String str) {
        String[] strArray = null;
        strArray = str.split(","); // 拆分字符为"," ,然后把结果交给数组strArray
        return strArray;
    }

    /**
     * 判断字段真实长度(中文2个字符，英文1个字符)
     * 
     * @param value
     * @return
     */
    public static int string_length(String value) {
        int vlength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                vlength += 2;
            } else {
                vlength += 1;
            }
        }
        return vlength;
    }
    public static boolean isChinese(String str){
    	String chinese = "[\u4e00-\u9fa5]{2,4}";
    	if (str.matches(chinese)) {
    		return true;
    	}else {
			return false;
		}
    }

    /**
     * 获取当前系统时间
     * 
     * @return
     */
    public static String getcurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return df.format(new Date());
    }

    /**
     * 判断当前时间和给定的时间差是否大于5分钟
     * 
     * @return
     * @throws ParseException
     * @throws Exception 
     */
    public static boolean localdateLtDate(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date1 = sdf.parse(date);
        Date now = sdf.parse(sdf.format(new Date()));
        if (now.getTime() - date1.getTime() > 5 * 60) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断当前时间和给定的时间差是否大于一天
     * 
     * @return
     * @throws ParseException
     * @throws Exception 
     */
    public static boolean localdateLtDate2(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date1 = sdf.parse(date);
        Date now = sdf.parse(sdf.format(new Date()));
        if (now.getTime() - date1.getTime() > 24 * 60 * 60) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据传入String日期类型，转换为long 读毫秒数
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Long getTimeHMillisDate(String strdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = sdf.parse(strdate);
        return date.getTime();
    }

    /**
     * 根据传入String日期类型，转换为long 读秒数
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Long getTimeMillisDate(String strdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = sdf.parse(strdate);
        return date.getTime() / 1000;
    }

    /**
     * 产生随机的2位数
     * 
     * @return
     */
    public static String getTwo() {
        Random rad = new Random();
        String result = rad.nextInt(100) + "";
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }

    public static int get_batch_num_cout(String str) {
        // String str = "abcdefabhjlecababcab";
        String str1 = "#";
        int count = 0;
        int start = 0;
        while (str.indexOf(str1, start) >= 0 && start < str.length()) {
            count++;
            start = str.indexOf(str1, start) + str1.length();
        }
        System.out.println(str1 + "在: " + str + "  出现的次数为 " + count);
        return count;
    }

    public static void main(String[] args) throws Exception {
        // String startTime = "2016-08-01";
        // String endTime = "2016-08-31";
        // int num = calculateMonth(parse(startTime+" 00:00:00.000", "yyyy-MM-dd
        // HH:mm:ss.SSS"), parse(endTime+" 23:59:59.999", "yyyy-MM-dd HH:mm:ss.SSS"));

        Long timeout = Long.parseLong("3") * 60000;
        if ((System.currentTimeMillis() - getTimeHMillisDate("2017-09-14 10:42:10.999")) >= timeout) {
            System.out.println("xxx");
        }

        // System.out.println(checkBankCard("2222222222@@"));
    }

    /**
     * 获取文件的真实媒体类型。目前只支持JPG, GIF, PNG, BMP四种图片文件。
     * 
     * @param bytes
     *            文件字节流
     * @return 媒体类型(MEME-TYPE)
     */
    public static String getMimeType(byte[] bytes) {
        String suffix = getFileSuffix(bytes);
        String mimeType;

        if ("JPG".equals(suffix)) {
            mimeType = "image/jpeg";
        } else if ("GIF".equals(suffix)) {
            mimeType = "image/gif";
        } else if ("PNG".equals(suffix)) {
            mimeType = "image/png";
        } else if ("BMP".equals(suffix)) {
            mimeType = "image/bmp";
        } else {
            mimeType = "application/octet-stream";
        }

        return mimeType;
    }

    /**
     * 获取文件的真实后缀名。目前只支持JPG, GIF, PNG, BMP四种图片文件。
     * 
     * @param bytes
     *            文件字节流
     * @return JPG, GIF, PNG or null
     */
    public static String getFileSuffix(byte[] bytes) {
        if (bytes == null || bytes.length < 10) {
            return null;
        }

        if (bytes[0] == 'G' && bytes[1] == 'I' && bytes[2] == 'F') {
            return "GIF";
        } else if (bytes[1] == 'P' && bytes[2] == 'N' && bytes[3] == 'G') {
            return "PNG";
        } else if (bytes[6] == 'J' && bytes[7] == 'F' && bytes[8] == 'I' && bytes[9] == 'F') {
            return "JPG";
        } else if (bytes[0] == 'B' && bytes[1] == 'M') {
            return "BMP";
        } else {
            return null;
        }
    }

    /**
     * 检查指定的字符串列表是否不为空。
     */
    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }
     
        public static String getSignContent(Map<String, String> sortedParams) {
            StringBuffer content = new StringBuffer();
            List<String> keys = new ArrayList<String>(sortedParams.keySet());
            Collections.sort(keys);
            int index = 0;
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                String value = sortedParams.get(key);
                if (areNotEmpty(key, value)) {
                    content.append((index == 0 ? "" : "&") + key + "=" + value);
                    index++;
                }
            }
            return content.toString();
        }
        public static int timeoutExpressParser(String timeoutExpress ){
            if("1c".equals(timeoutExpress)){
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minite = cal.get(Calendar.MINUTE);
                return (23-hour)*60+60-minite;
            }else if(timeoutExpress.contains("m")){
                return Integer.parseInt(timeoutExpress.replace("m", ""));
            }else if(timeoutExpress.contains("h")){
                return Integer.parseInt(timeoutExpress.replace("h", ""))*60;
            }else if(timeoutExpress.contains("d")){
                return Integer.parseInt(timeoutExpress.replace("d", ""))*3600;
            }
            return 0;
        }
}
