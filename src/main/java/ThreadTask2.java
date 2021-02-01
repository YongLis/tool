import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;


public class ThreadTask2 extends Thread {
	public ReportJobTask task;
	
	public ThreadTask2(ReportJobTask task){
		this.task = task;
	}
	
	
	public static List<String> getSqlList(String startTime ,int intervalTime) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sfDateFormat.parse(startTime); // 20180709160000
			Date newDate = null;
			for (int i = 1; i < 9600; i++) {
				newDate = DateUtils.addDays(date, intervalTime);
				if (newDate.compareTo(sfDateFormat.parse("2019-12-01")) <= 0) {
					// String sql
					// ="SELECT datacenter.t_payment_info_summarization('"+sfDateFormat.format(date)+"','"+sfDateFormat.format(newDate)+"')";
					String sql = "call report.SP_TMP_ORDER_STAGE_REPORT(to_date('"+sfDateFormat.format(date)+"','yyyy-mm-dd'),to_date('"+sfDateFormat.format(newDate)+"','yyyy-mm-dd'))";
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
	
	
	@Override
	public void run() {
		List<String> sqlList = getSqlList("2016-01-01", 5);
		
		for(String sql : sqlList){
			try {
				synchronized (task) {
					task.notify();
//			    	System.out.println(sql);
					task.collectDataTmpStageReport(sql);
			    	task.wait();
			    	
				}
			} catch (Exception e) {
				System.out.println("ThreadTask2 error");
				e.printStackTrace();
			}
		}

	}

}
