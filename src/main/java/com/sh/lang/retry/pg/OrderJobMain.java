package com.sh.lang.retry.pg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import com.sh.lang.retry.OracleDataSource;


public class OrderJobMain {

	
	public Connection getConnection() throws Exception{
		Class.forName(OracleDataSource.driver).newInstance();
		return DriverManager.getConnection(OracleDataSource.url, OracleDataSource.user, OracleDataSource.password);
	}
	
	public static List<String> getSqlList(String startTime){
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = sfDateFormat.parse(startTime); //20200207060000
			Date newDate = null;
			for(int i=1; i<9600; i++){
				newDate = DateUtils.addDays(date, 1);
				if(newDate.compareTo(sfDateFormat.parse("20190101")) <= 0){
					//String sql ="SELECT datacenter.t_payment_info_summarization('"+sfDateFormat.format(date)+"','"+sfDateFormat.format(newDate)+"')";
					String sql = "{call report.SP_TMP_PURCHASE_ORDER(to_date('"+sfDateFormat.format(date)+"','yyyymmdd'),to_date('"+sfDateFormat.format(newDate)+"','yyyymmdd'))}";
	                list.add(sql);
					date=newDate;
				}
				else{
					break;
				}
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void writeErrorFile(String fileName, String text){
		File file = new File(fileName);
		
		FileWriter writer = null;
		try {
			writer = new FileWriter(file, true);
			writer.write(text+System.getProperty("line.separator"));
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	

	public static void main(String[] args) {
//		writeErrorFile("D://tmp//payment_info_error.txt", "hewewe");
//		writeErrorFile("D://tmp//payment_info_error.txt", "hewewe");
//		
		OrderJobMain jobMain =  new OrderJobMain();
		CallableStatement statement = null;
		Connection conn = null;
		// 上次执行时间点 20191214140000
		List<String> sqlList = getSqlList("20181014");
		
		//String sql = "SELECT datacenter.t_payment_info_summarization('20181206160000','20181206220000')";
		
		for(String sql : sqlList){
			try {
				conn = jobMain.getConnection();
				conn.setAutoCommit(false);
				statement = conn.prepareCall(sql);//准备PreparedStatement 
	            statement.executeQuery();//执行查询
	            conn.commit();
	            writeErrorFile("D://tmp//t_purchase_order_success.txt", sql+";");
	            System.out.println(sql+" 执行完成");
	        } catch (Exception e) {
	            e.printStackTrace();
	            writeErrorFile("D://tmp//t_purchase_order_error.txt", sql+";");
	            
	        } finally {
	            try {
	            	if(conn != null){
	            		conn.close();
	            	}
	                
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
			
		}
		
		
		System.out.println("end");
		

	}

}
