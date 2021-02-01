package com.sh.lang.retry;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.sh.lang.retry.pg.BiTask;
import com.sh.lang.utils.DateUtil;

public class Test {
	public static String url = "jdbc:oracle:thin:@//106.14.49.137:51084/ReportDB";
	public static String user = "report";
	public static String password = "klAj1CWB#0lRys%z";
	
	public Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		return DriverManager.getConnection(url, user, password);
	}
	
	public static List<String> getSqlList(String startTime){
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sfDateFormat.parse(startTime); //2019-10-25
			Date newDate = null;
			for(int i=1; i<300; i++){
				newDate = DateUtils.addDays(date, 1);
				if(newDate.compareTo(sfDateFormat.parse("2020-01-08")) <= 0){
					//String sql ="SELECT datacenter.t_payment_info_summarization('"+sfDateFormat.format(date)+"','"+sfDateFormat.format(newDate)+"')";
					String sql = "{ call SP_KJSD_FUND_JOBS_DAILY('"+sfDateFormat.format(newDate)+"') }";
	                list.add(sql);
					date=newDate;
				}
				else{
					break;
				}
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	

	public static void main(String[] args) {
		Test test =  new Test();
		CallableStatement statement = null;
		Connection conn = null;
		
		List<String> sqlList = getSqlList("2019-12-31");
//		String sql = "{ call SP_KJSD_FUND_JOBS_DAILY('2019-10-27') }";
		for(String sql : sqlList){
			long start = System.currentTimeMillis();
			try {
				
				conn = test.getConnection();
				conn.setAutoCommit(false);
				statement = conn.prepareCall(sql);//准备PreparedStatement 
	            statement.execute();//执行查询
	            conn.commit();
	            BiTask.writeErrorFile("D://tmp//资金正负向_success.txt", sql+";");
	            long end = System.currentTimeMillis();
	            System.out.println(sql+" 执行完成,耗时（秒）="+ (end-start)/1000);
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(sql+" ---error");
	            BiTask.writeErrorFile("D://tmp//资金正负向_fail.txt", sql+";");
	            
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
