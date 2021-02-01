package com.sh.lang.retry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StrTest {
	
	public static String changeStr(String tmp){
		String result = null;
		
		System.out.println("when t1.payment_service_code ="+tmp.substring(tmp.indexOf("(")+1, tmp.indexOf(",")) +" then "+ tmp.substring(tmp.indexOf(",")+1, tmp.indexOf("\", ")));
		
		
		return result;
		
	}

	public static void main(String[] args) {
		
		String tmp = "UNSETTLED_ACCOUNT_SAR(331, \"待结算账户-沙特阿拉伯里亚尔\", \"SAR\"),";
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(new File("D:\\tmp.txt")));
			String line = null;
			while((line=reader.readLine()) != null){
				changeStr(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
