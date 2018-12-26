
package com.sh.lang.utils;

import java.io.UnsupportedEncodingException;

public class HEXUtil {

    private static final String HEX_CODE = "0123456789ABCDEF";

    /**
     * 字符串转换成十六进制字符串
     * 
     * @param str 待转换的ASCII字符串
     * @param charsetName 字符集，默认UTF-8
     * @return
     */
    public static String str2HexStr( String str,String charsetName ) {

        char[] chars = HEX_CODE.toCharArray( );
        StringBuilder sb = new StringBuilder( );
        try {
            byte[] bs;
            if (StringUtil.isNotEmpty( charsetName )) {
                bs = str.getBytes( charsetName );
            } else {
                bs = str.getBytes( "UTF-8" );
            }
            for ( int i = 0; i < bs.length; i++ ) {
                int bit = ( bs[i] & 0x0f0 ) >> 4;
                sb.append( chars[bit] );
                bit = bs[i] & 0x0f;
                sb.append( chars[bit] );
            }
            return sb.toString( ).trim( );
        }catch ( UnsupportedEncodingException e ) {
            e.printStackTrace();
        }
        return null;
    }

}