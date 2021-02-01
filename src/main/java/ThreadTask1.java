import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;


public class ThreadTask1 extends Thread {
	public ReportJobTask task;
	
	
	public static List<String> getSqlList (String startTime ,int intervalTime) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sfDateFormat.parse(startTime); // 20180709160000
			Date newDate = null;
			for (int i = 1; i < 9600; i++) {
				newDate = DateUtils.addDays(date, intervalTime);
				if (newDate.compareTo(sfDateFormat.parse("2020-11-15")) > 0) {
					// String sql
					// ="SELECT datacenter.t_payment_info_summarization('"+sfDateFormat.format(date)+"','"+sfDateFormat.format(newDate)+"')";
//					String sql = "call report.SP_SETTLE_REBATE_CONFIG_DAILY('"+sfDateFormat.format(date)+"')";
//					String sql = "call report.SP_KJSD_FUND_JOBS_DAILY('"+sfDateFormat.format(date)+"')";
//					String sql = "call SP_T_ACCT_BALANCE_DAILY(to_date('"+sfDateFormat.format(date)+"','yyyy-mm-dd'))";
					String sql = "call SP_T_FUND_SETTLEMENT(to_date('"+sfDateFormat.format(newDate)+"','yyyy-mm-dd'))";
//					String sql = "call SP_TMP_PURCHASE_ORDER(to_date('"+sfDateFormat.format(date)+"','yyyy-mm-dd'),to_date('"+sfDateFormat.format(newDate)+"','yyyy-mm-dd'))";
					
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
	
	public ThreadTask1(ReportJobTask task){
		this.task = task;
	}
	@Override
	public void run() {
		List<String> sqlList = getSqlList("2020-12-21", -1);
		for(String sql : sqlList){
			try {
				synchronized (task) {
//					task.notify();
//			    	System.out.println(sql);
					task.collectDataStageReport(sql);
//			    	task.wait();
			    	
				}
			} catch (Exception e) {
				System.out.println("ThreadTask1 error");
				e.printStackTrace();
			}
		}

		

	}

}
