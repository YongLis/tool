package com.sh.lang.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public final class ValidatorUtils {
	/**
	 * 邮箱验证
	 */
	private static final String  emailType = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	/**
	 * 银行卡密码
	 */
	private static final String bankPasswordType = "^[0-9]{6}$";
	/**
	 * 正则验证银行卡(验证全数字)
	 */
	private static final String cardNoType = "^[0-9]*$";

	/**
	 * 验证http请求网址
	 */
	private static final String httpType = "(http://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*";

	/**
	 * 正则验证全数字
	 */
	private static final String digitType = "^[0-9]*$";
	
	/**
	 * 正则验证金额格式：保留两位小数的正实数
	 */
	private static final String moneyType = "^(([1-9]\\d{0,9})|0)(\\.\\d{1,2})?$";

	/**
	 * 正则验证手机号
	 */
    private static final String mobileType= "^((13[0-9])|(14[5|7])|(15[0-9])|(17[0|6-8])|(18[0-9]))\\d{8}$";
                                             
	/**
	 * 正则验证时间 yyyy-MM-dd
	 */
    private static final String dateType = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
    
    /**
     * 正则验证局域网ip
     */
    private static final String lanIpType = "(10|172|192)\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})";
    
    
    /**
     * 正则经度
     */
    private static final String longitudeType = "^[-]?((\\d|([1-9]\\d)|(1[0-7]\\d))(\\.\\d+)?|(180)(\\.0+)?)$";

    /**
     * 正则纬度
     */
    private static final String latitudeType = "^[-]?((\\d|([1-8]\\d))(\\.\\d+)?|(90)(\\.0+)?)$";

    
	/**
	 * 验证局域网ip
	 * @param ip
	 * @return
	 */
	public static boolean isLanIp(String ip) {
		return find(lanIpType, ip);
	}

	/**
	 * 验证邮箱
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isEmail(String email) {
		return find(emailType, email);
	}
	
	/**
	 * 验证数字
	 * 
	 * @param digit
	 * @return
	 */
	public static boolean isDigit(String digit) {
		return match(digitType, digit);
	}

	/**
	 * 正则验证金额格式：保留两位小数的正实数
	 * 
	 * @param amount
	 * @return
	 */
	public static boolean isMoney(String amount) {
		return match(moneyType, amount);
	}

	/**
	 * 验证BankCard(验证全数字)
	 * 
	 * @param cardNo
	 * @return
	 */
	public static boolean isCardNo(String cardNo) {
		return match(cardNoType, cardNo);
	}

	/**
	 * 验证http请求
	 * 
	 * @param numberId
	 * @return
	 */
	public static boolean isHttp(String http) {
		return match(httpType, http);
	}
	
	/**
	 * 验证字符串长度
	 * @param str
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean lengthBetween(String str,int min,int max) {
		if(str==null){
			return false;
		}
		if(str.length()>max||str.length()<min){
			return false;
		}
		return true;
	}
	
	/**
	 * 验证字符串是否是布尔值
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBoolean(String str) {
		if (StringUtils.isNotBlank(str)) {
			if (StringUtils.equalsIgnoreCase("true", str.trim()) || StringUtils.equalsIgnoreCase("false", str.trim())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 验证密码
	 * @param str
	 * @return
	 */
	public static boolean isBankPassword(String str) {
		if (StringUtils.isNotBlank(str)) {
			boolean result=match(bankPasswordType, str);
			return result;
		}
		return false;
	}
	
	/**
	 * 检查验证短信，和卡密一样6位数字。
	 * 
	 */
	public static boolean isSmsCode(String str){
		return match(bankPasswordType, str);
	}

	/**
	 * 验证IP地址
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isIP(String str) {
		String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
		return match(regex, str);
	}

	/**
	 * 验证网址Url
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isUrl(String str) {
		String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		return match(regex, str);
	}

	/**
	 * 验证电话号码
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isTelephone(String str) {
		String regex = "^(\\d{3,4}-)?\\d{6,8}$";
		return match(regex, str);
	}
	
	/**
	 * 验证输入手机号码
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isHandset(String str) {
		String regex = "^[1]+[3,5]+\\d{9}$";
		return match(regex, str);
	}
	
	/**
	 * 验证手机格式。
	 * 
	 * @param mobile
	 * @return 是手机号返回 true ;不是手机号返回 false 。
	 */
	public static boolean isMobile(String mobile) {	
		return match(mobileType, mobile);
    }     

	/**
	 * 验证输入密码条件(字符与数据同时出现)
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isPassword(String str) {
		String regex = "(?![a-z|_]+$|[0-9]+$)^[a-zA-Z0-9,_]{6,18}$";
		return match(regex, str);
	}

	/**
	 * 验证输入密码长度 (6-18位)
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isPasswLength(String str) {
		String regex = "^\\d{6,18}$";
		return match(regex, str);
	}

	/**
	 * 验证输入邮政编号
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isPostalcode(String str) {
		String regex = "^\\d{6}$";
		return match(regex, str);
	}

	/**
	 * 验证输入身份证号
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isIDcard(String str) {
		return IdHolder.certNoValidate(str);
	}

	/**
	 * 验证输入两位小数
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isDecimal(String str) {
		String regex = "^[0-9]+(.[0-9]{2})?$";
		return match(regex, str);
	}

	/**
	 * 验证输入一年的12个月
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isMonth(String str) {
		String regex = "^(0?[[1-9]|1[0-2])$";
		return match(regex, str);
	}

	/**
	 * 验证输入一个月的31天
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsDay(String str) {
		String regex = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
		return match(regex, str);
	}
	
    /**
     * 验证时间  yyyy-MM-dd
     * @param date
     * @return
     */
    public static boolean isTime(String date){
    	return match(dateType,date);
    }

	/**
	 * 验证日期时间
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合网址格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isDate(String str) {
		// 严格验证时间格式的(匹配[2002-01-31], [1997-04-30],
		// [2004-01-01])不匹配([2002-01-32], [2003-02-29], [04-01-01])
		// String regex =
		// "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((01,3-9])|(1[0-2]))-(29|30)))))$";
		// 没加时间验证的YYYY-MM-DD
		// String regex =
		// "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
		// 加了时间验证的YYYY-MM-DD 00:00:00
		String regex = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
		return match(regex, str);
	}

	/**
	 * 验证数字输入
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isNumber(String str) {
		String regex = "^[0-9]*$";
		return match(regex, str);
	} 
	
	/**
	 * 验证6位数字密码。
	 * 
	 * @param str
	 * @return
	 */
	public static boolean is6Number(String str) {
		String regex = "^\\d{6}$";
		return match(regex, str);
	}
	
	/**
	 * 验证3位数字密码。
	 * 
	 * @param str
	 * @return
	 */
	public static boolean is3Number(String str) {
		String regex = "^\\d{3}$";
		return match(regex, str);
	}	
	
	/**
	 * 验证信用卡4位有效期数字。
	 * 
	 * @param str
	 * @return
	 */
	public static boolean is4Number(String str) {
		String regex = "^\\d{4}$";
		return match(regex, str);
	}

	/**
	 * 验证非零的正整数
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isIntNumber(String str) {
		String regex = "^\\+?[1-9][0-9]*$";
		return match(regex, str);
	}

	/**
	 * 验证大写字母
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isUpChar(String str) {
		String regex = "^[A-Z]+$";
		return match(regex, str);
	}

	/**
	 * 验证小写字母
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isLowChar(String str) {
		String regex = "^[a-z]+$";
		return match(regex, str);
	}

	/**
	 * 验证验证输入字母
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isLetter(String str) {
		String regex = "^[A-Za-z]+$";
		return match(regex, str);
	}

	/**
	 * 验证验证输入汉字
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isChinese(String str) {
		String regex = "^[\u4e00-\u9fa5],{0,}$";
		return match(regex, str);
	}

	/**
	 * 字符串长度大于指定的长度
	 * @param str
	 * @param length
	 * @return
	 */
	public static boolean isLengthGreaterThan(String str,int length) {
		String regex = "^.{"+length+",}$";
		return match(regex, str);
	}
	
	/**
	 * 字符串长度小于指定的长度
	 * @param str
	 * @param length
	 * @return
	 */
	public static boolean isLengthLowerThan(String str,int length) {
		String regex = "^.{0,"+length+"}$";
		return match(regex, str);
	}

	/**
	 * @param regex
	 *            正则表达式字符串
	 * @param str
	 *            要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	private static boolean match(String regex, String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * @param regex
	 *            正则表达式字符串
	 * @param str
	 *            要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	private static boolean find(String regex, String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
	    return matcher.find();
	}
	
	   /**
     * 验证经度
     * 坐标范围<b>-180~180</b>
     * @param longitude
     * @return
     */
    public static boolean isLongitude(String longitude){
        return find(longitudeType, longitude);
    }

    /**
     * 验证纬度
     * 坐标范围<b>-90~90</b>
     * @param latitude
     * @return
     */
    public static boolean isLatitude(String latitude){
        return find(latitudeType, latitude);
    }

    /**
     * 是否包含中英文特殊字符，除英文"-_@."字符外
     *
     * @param str
     * @return
     */
    public static boolean isContainsSpecialChar(String str) {
        if(StringUtils.isBlank(str)) return false;
        String[] chars={"[","`","~","!","#","$","%","^","&","*","(",")","+","=","|","{","}","'",
                ":",";","'",",","[","]","<",">","/","?","~","！","#","￥","%","…","&","*","（","）",
                "—","+","|","{","}","【","】","‘","；","：","”","“","’","。","，","、","？","]"};
        for(String ch : chars){
            if(str.contains(ch)) return true;
        }
        return false;
    }
}
