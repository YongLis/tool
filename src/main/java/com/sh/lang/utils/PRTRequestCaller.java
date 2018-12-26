package com.sh.lang.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PRTRequestCaller {
	
	private static final Logger logger = LoggerFactory.getLogger(PRTRequestCaller.class);
	/**
	 * url	http地址
	 * param	json字符串
	 */
	public static String sendPost(String url,String param){
		PrintWriter out = null;
		BufferedReader in =null;
		String result = "";
		try{
			URL realUrl = new URL(url);
			//打开连接
			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
			conn.setRequestMethod("POST");
			//设置请求
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0;Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-type", "application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			byte[] b=param.getBytes();
			//发送请求参数
			conn.getOutputStream().write(b,0,b.length);
			//输出流缓冲
			conn.getOutputStream().flush();
			conn.getOutputStream().close();
			//定义输入流得到响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line = in.readLine())!=null){
				result += line;
			}
		}catch(Exception e){
			logger.error("发送POST请求错误!"+e);
		}finally{
			try{
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			}catch(Exception e){
				logger.error("关闭POST请求错误!"+e);
			}
		}
		return result;
	}
	/**
	 * url	http地址
	 * param	请求参数	sysname=""&lasthash=""
	 * return	Map	[hash=""][configJson=""]
	 */	
	public static Map sendPostParam(String url,StringBuffer param){
		Map map = new HashMap();
		PrintWriter out = null;
		BufferedReader in =null;
		String result = "";
		String hash = "";
		String statusCode = "";
		try{
			URL realUrl = new URL(url);
			//打开连接
			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
			conn.setRequestMethod("POST");
			//设置请求
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0;Windows NT 5.1;SV1)");
			conn.setDoOutput(true);
			conn.setDoInput(true);

			byte[] b=param.toString().getBytes();
			//发送请求参数
			conn.getOutputStream().write(b,0,b.length);
			//输出流缓冲
			conn.getOutputStream().flush();
			conn.getOutputStream().close();
			
			//定义输入流得到响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line = in.readLine())!=null){
				result += line;
			}
			statusCode = conn.getResponseCode()+"";
			hash = conn.getHeaderField("hash");		
		}catch(Exception e){
			logger.error("发送POST请求错误!"+e);
		}finally{
			map.put("statusCode", statusCode);
			map.put("hash", hash);
			map.put("configJson", result);
			try{
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			}catch(Exception e){
				logger.error("关闭POST请求错误!"+e);
			}
		}
		return map;
	}
}
