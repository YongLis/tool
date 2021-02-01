import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;


public class AwsReportJobTask2 {
	private static final String driver="oracle.jdbc.OracleDriver";                                               
	private static final String url="jdbc:oracle:thin:@52.80.216.149:10005:report";                                     
	private static final String user="dev_acquire";                                                                
	private static final String password="Rc4EWEzosJ6gnOAlr6q3";   
	
	
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
	
	public void collectDataStageReport(String sql){
		// sp_t_order_stage_report
		try {                                                                                                    
			Class.forName(driver);                                                                               
			Connection connection = DriverManager.getConnection(url, user, password);                            
//			Statement createStatement = connection.createStatement();                                            
//			boolean bool = createStatement.execute(sql);                
			CallableStatement cs = connection.prepareCall(sql);
			cs.execute();                    
			System.out.println(sql+"执行成功");
			writeLogFile("D:/log/T_PURCHASE_ORDER_success.txt", sql);
		} catch (Exception e) {                                                                                  
			e.printStackTrace();    
			System.out.println(sql+"--执行失败");
			writeLogFile("D:/log/T_PURCHASE_ORDER_fail.txt", sql);
		}         
		
	}
	
	public static List<String> getSqlList (String startTime ,int intervalTime) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sfDateFormat.parse(startTime); // 20180709160000
			Date newDate = null;
			for (int i = 1; i < 9600; i++) {
				newDate = DateUtils.addDays(date, intervalTime);
				if (newDate.compareTo(sfDateFormat.parse("2020-01-01")) >= 0) {
					// String sql
					// ="SELECT datacenter.t_payment_info_summarization('"+sfDateFormat.format(date)+"','"+sfDateFormat.format(newDate)+"')";
//					String sql = "call report.SP_SETTLE_REBATE_CONFIG_DAILY('"+sfDateFormat.format(date)+"')";
//					String sql = "call report.SP_KJSD_FUND_JOBS_DAILY('"+sfDateFormat.format(date)+"')";
//					String sql = "call SP_T_ERS_DAY_HIS_RATE(to_date('"+sfDateFormat.format(date)+"','yyyy-mm-dd'))";
					String sql = "call sp_t_all_channel_order(to_date('"+sfDateFormat.format(newDate)+"','yyyy-mm-dd'),to_date('"+sfDateFormat.format(date)+"','yyyy-mm-dd'))";
//					String sql = "call SP_TMP_PURCHASE_ORDER(to_date('"+sfDateFormat.format(newDate)+"','yyyy-mm-dd'),to_date('"+sfDateFormat.format(date)+"','yyyy-mm-dd'))";
//					String sql = "call report.SP_TMP_REFUND_FEE_INFO(to_date('"+sfDateFormat.format(newDate)+"','yyyy-mm-dd'),to_date('"+sfDateFormat.format(date)+"','yyyy-mm-dd'))";

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
		AwsReportJobTask2 task = new AwsReportJobTask2();
		
		
		try {
//			Thread.currentThread().sleep(1000*3600*6);
			List<String> sqlList = task.getSqlList("2020-10-16", -1);
			for(String sql : sqlList){
				task.collectDataStageReport(sql);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		List<String> sqlList = new ArrayList<String>();
//		sqlList.add("call report.SP_OLD_PAYMENT_ORDER(to_date('2016-01-01','yyyy-mm-dd'), to_date('2017-01-01','yyyy-mm-dd'))");
//		sqlList.add("call report.SP_OLD_PAYMENT_ORDER(to_date('2017-01-01','yyyy-mm-dd'), to_date('2017-06-01','yyyy-mm-dd'))");
//		sqlList.add("call report.SP_OLD_PAYMENT_ORDER(to_date('2017-06-01','yyyy-mm-dd'), to_date('2018-01-01','yyyy-mm-dd'))");
//		sqlList.add("call report.SP_OLD_PAYMENT_ORDER(to_date('2018-01-01','yyyy-mm-dd'), to_date('2018-04-01','yyyy-mm-dd'))");
//		sqlList.add("call report.SP_OLD_PAYMENT_ORDER(to_date('2018-04-01','yyyy-mm-dd'), to_date('2018-06-01','yyyy-mm-dd'))");
//		sqlList.add("call report.SP_KJSD_FUND_JOBS_DAILY('2020-05-27')");
//		sqlList.add("call report.SP_KJSD_FUND_JOBS_DAILY('2020-05-28')");
//		sqlList.add("call report.SP_KJSD_FUND_JOBS_DAILY('2020-05-29')");
//		sqlList.add("call report.SP_KJSD_FUND_JOBS_DAILY('2020-05-30')");
//		sqlList.add("call report.SP_KJSD_FUND_JOBS_DAILY('2020-05-31')");
//		sqlList.add("call report.SP_KJSD_FUND_JOBS_DAILY('2020-06-01')");
//
//		for(String sql : sqlList){
//			ReportJobTask task = new ReportJobTask();
//			task.collectDataStageReport(sql);
//		}
		
		
		
	}
	

}
