package com.sh.lang.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Description: Gson工具类
 * @Author: liyong
 * @date: 2017年12月4日 下午8:52:14 
 */
public class GsonUtil {
    /**
     * 对象转字符串 
     */
    public static String ObjectToString(Object object){
        if(object == null){
            return null;
        }
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(object);
    }
    
    /**
     * json转对象 
     */
    
    public static <T> T jsonToObject(String jsonStr, Class<T> t) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, t);
    }
}
