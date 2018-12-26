package com.sh.lang.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * 类名称：AES加密解密工具
 * 类描述：用一句话描述作用
 * 作者：liyong
 * 创建时间：2018-04-09
 */
public class AESutil {
    private static final String KEY="A1E4C668VH328GIP";
    private static SecretKey secretKey;
    private static Cipher cipher;
    
    private static void init() throws Exception {
        cipher = Cipher.getInstance("AES");
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(KEY.getBytes());
        keyGenerator.init(secureRandom);
        secretKey = keyGenerator.generateKey(); 
    }
    
    /**
     * 加密 
     * @throws Exception 
     */
    public static String encrypt(String content) throws Exception {
        init();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] result = cipher.doFinal(content.getBytes("UTF-8"));
        return Base64.encode(result);
    }
    
    /**
     * 解密 
     * @throws Exception 
     */
    public static String decrypt(String content) throws Exception {
        init();
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.decode(content)), "UTF-8");
        
    }
}
