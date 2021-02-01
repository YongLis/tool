package com.sh.lang.retry.pg;


public class PaymentListJob {

	public static void main(String[] args) {
		//20181128220000---20191130220000-----20200319000000
		JobTask newPaymentJobTask = new JobTask(PostgresqlDataSource.driver, PostgresqlDataSource.url, PostgresqlDataSource.user, PostgresqlDataSource.password,"20200405053600", "SELECT datacenter.payment_list_summarization",12*60 ,"D://log//payment_list_success.txt","D://log//payment_list_error.txt");
		newPaymentJobTask.runInDb();

	}

}
