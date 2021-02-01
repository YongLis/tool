package com.sh.lang.retry.pg;


public class PaymentInfoJob {

	public static void main(String[] args) {
		//20190421220000-----20191129220000----20200310000000
		JobTask oldPaymentJobTask = new JobTask(PostgresqlDataSource.driver, PostgresqlDataSource.url, PostgresqlDataSource.user, PostgresqlDataSource.password,"20200404031200", "SELECT datacenter.t_payment_info_summarization",60*12 ,"D://log//payment_info_success.txt","D://log//payment_info_error.txt");
		oldPaymentJobTask.runInDb();	
	}

}
