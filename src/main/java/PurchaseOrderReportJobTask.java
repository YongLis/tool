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


public class PurchaseOrderReportJobTask {
	private static final String driver="oracle.jdbc.OracleDriver";                                               
	private static final String url="jdbc:oracle:thin:@106.14.49.137:51084:ReportDB";                                     
	private static final String user="report";                                                                
	private static final String password="klAj1CWB#0lRys%z";   

	
	
	
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
			writeLogFile("D:/log/new_payment_success3.txt", sql);
		} catch (Exception e) {                                                                                  
			e.printStackTrace();    
			System.out.println(sql+"--执行失败");
			writeLogFile("D:/log/new_payment_fail3.txt", sql);
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
				if (newDate.compareTo(sfDateFormat.parse("2019-01-01")) >= 0) {
					// String sql
					// ="SELECT datacenter.t_payment_info_summarization('"+sfDateFormat.format(date)+"','"+sfDateFormat.format(newDate)+"')";
//					String sql = "call report.SP_SETTLE_REBATE_CONFIG_DAILY('"+sfDateFormat.format(date)+"')";
//					String sql = "call report.SP_KJSD_FUND_JOBS_DAILY('"+sfDateFormat.format(date)+"')";
//					String sql = "call report.SP_SETTLE_ALL_PROCESS_DAILY('"+sfDateFormat.format(date)+"')";
					
					String sql = "call report.SP_TMP_PURCHASE_ORDER(to_date('"+sfDateFormat.format(newDate)+"','yyyy-mm-dd'),to_date('"+sfDateFormat.format(date)+"','yyyy-mm-dd'))";
					
//					String sql = "call report.SP_OLD_PAYMENT_ORDER(to_date('"+sfDateFormat.format(newDate)+"','yyyy-mm-dd'),to_date('"+sfDateFormat.format(date)+"','yyyy-mm-dd'))";

					
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
		PurchaseOrderReportJobTask task = new PurchaseOrderReportJobTask();

		
		
		try {
			
			List<String> sqlList = task.getSqlList("2020-06-10", -1);
			for(String sql : sqlList){
				task.collectDataStageReport(sql);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	

}
