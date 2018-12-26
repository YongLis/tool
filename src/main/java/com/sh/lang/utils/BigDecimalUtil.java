package com.sh.lang.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 类名称：用一句话描述名称
 * 类描述：用一句话描述作用
 * 作者：liyong
 * 创建时间：2018-03-14
 */
public class BigDecimalUtil {

    /**
     * 分转元(保留两位小数)
     */
    public static String fen2Yuan(long amt) {
        BigDecimal bigDecimal = new BigDecimal(amt);
        BigDecimal result = bigDecimal.divide(new BigDecimal(100));
        result.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        return result.toString();
    }
    
    /**
     * 元转分 
     */
    public static String yuan2Fen(String amt) {
        BigDecimal bigDecimal = new BigDecimal(amt);
        BigDecimal result = bigDecimal.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
        return result.toString();
    }
    
}
