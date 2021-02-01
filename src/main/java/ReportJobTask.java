import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ReportJobTask {
	private static final String driver="oracle.jdbc.OracleDriver";                                               
	private static final String url="jdbc:oracle:thin:@52.80.216.149:10005:report";                                     
	private static final String user="dev_acquire";                                                                
	private static final String password="Rc4EWEzosJ6gnOAlr6q3";   

	
	public void collectDataTmpStageReport(String sql){
		// call report.SP_TMP_ORDER_STAGE_REPORT(to_date('','yyyy-mm-dd'), to_date('','yyyy-mm-dd'));
		try {                                                                                                    
			Class.forName(driver);                                                                               
			Connection connection = DriverManager.getConnection(url, user, password);                            
//			Statement createStatement = connection.createStatement();                                            
//			boolean bool = createStatement.execute(sql);                
			CallableStatement cs = connection.prepareCall(sql);
			cs.execute();                    
			System.out.println(sql+"执行成功");
			writeLogFile("D:/log/SP_OLD_PAYMENT_ORDER_success.txt", sql);
		} catch (Exception e) {                                                                                  
			e.printStackTrace();    
			System.out.println(sql+"--执行失败");
			writeLogFile("D:/log/SP_OLD_PAYMENT_ORDER_fail.txt", sql);
		}                 
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
			writeLogFile("D:/log/SP_T_ACCT_BALANCE_DAILY_success.txt", sql);
		} catch (Exception e) {                                                                                  
			e.printStackTrace();    
			System.out.println(sql+"--执行失败");
			writeLogFile("D:/log/SP_T_ACCT_BALANCE_DAILY_fail.txt", sql);
		}         
		
	}
	

	public static void main(String[] args) {
		
		ReportJobTask task = new ReportJobTask();
		new ThreadTask1(task).start();
		
		
	}
	

}
