package com.sh.lang.retry.pg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.rabbitmq.client.AMQP.Basic.Return;

public class JobTask {
	String driver;
	String url;
	String user;
	String password;
	String successPath; 
	String failPath	;
	String startTime;
	String sql;
	int intervalTime;
	
	List<String> sqlList;
	
	
	public JobTask(String driver,String url,String user,String password,String startTime, String sql, int intervalTime,
			String successPath, String failPath) {
		System.out.println("##########################work start#########################");
		this.intervalTime = intervalTime;
		this.startTime = startTime;
		this.sql = sql;
		
		this.successPath = successPath;
		this.failPath = failPath;
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	
	public void writeLogFile(String fileName, String text) {
		File file = new File(fileName);

		FileWriter writer = null;
		try {
			writer = new FileWriter(file, true);
			writer.write(text + System.getProperty("line.separator"));
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void runInDb() {
		
		sqlList = getSqlList(startTime, sql, intervalTime);
		
		for(String sql : sqlList){
			CallableStatement statement = null;
			Connection conn = null;
			// 上次执行时间点 20180618180000
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url, user, password);
				conn.setAutoCommit(false);
				statement = conn.prepareCall(sql);// 准备PreparedStatement
				statement.executeQuery();// 执行查询
				conn.commit();
				writeLogFile(successPath, sql + ";");
				System.out.println(sql + " 执行完成");
			} catch (Exception e) {
				e.printStackTrace();
				writeLogFile(failPath, sql + ";");

			} finally {
				try {
					if (conn != null) {
						conn.close();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<String> queryData(String sql) {
		List<String> list = new ArrayList<String>();
			Statement statement = null;
			Connection conn = null;
			// 上次执行时间点 20180618180000
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url, user, password);
				statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()){
					list.add(rs.getString("acsorderid"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				writeLogFile(failPath, sql + ";");

			} finally {
				try {
					if (conn != null) {
						conn.close();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	
	   return list;

	}
	

	public static List<String> getSqlList(String startTime, String targetSql,
			int intervalTime) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			Date date = sfDateFormat.parse(startTime); // 20180709160000
			Date newDate = null;
			for (int i = 1; i < 9600; i++) {
				newDate = DateUtils.addDays(date, intervalTime);
				if (newDate.compareTo(sfDateFormat.parse("20190101000000")) <= 0) {
					// String sql
					// ="SELECT datacenter.t_payment_info_summarization('"+sfDateFormat.format(date)+"','"+sfDateFormat.format(newDate)+"')";
					String sql = targetSql + "('" + sfDateFormat.format(date)
							+ "','" + sfDateFormat.format(newDate) + "')";
					list.add(sql);
					date = newDate;
				} else {
					break;
				}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public static void main(String[] args) {
		// JobTask oldPaymentJobTask = new JobTask("20150101000000",
		// "SELECT datacenter.payment_list_summarization",24
		// ,"D://log//payment_list_success.txt","D://log//payment_list_error.txt");
		// JobTask newPaymentJobTask = new JobTask("20150101000000",
		// "SELECT datacenter.t_payment_info_summarization","D://log//payment_info_success.txt","D://log//payment_info_error.txt");

	}


}
