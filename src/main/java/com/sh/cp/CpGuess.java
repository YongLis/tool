package com.sh.cp;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class CpGuess {
	
	// 获取网站数据，存入文件中
	public void writeDataFromWebSit(CpResult record){
		
		try {
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D://tmp/cpresult.txt", true));
			bufferedWriter.write(record.toString());
			bufferedWriter.newLine();
			
			bufferedWriter.flush();
			bufferedWriter.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

	public static void main(String[] args) {
		CpGuess cpGuess = new CpGuess();
		CpResult record = new CpResult("02","07","09","12","17","18","24");
        cpGuess.writeDataFromWebSit(record);  
	}

}
