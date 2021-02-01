package com.sh.lang.retry.pg;


public class PurchaseOrderJob {

	public static void main(String[] args) {
		// TODO Auto-generated method stub-----20200401033000
		JobTask purchaseOrderJobTask = new JobTask(PostgresqlDataSource.driver, PostgresqlDataSource.url, PostgresqlDataSource.user, PostgresqlDataSource.password,"20180110000000","SELECT datacenter.t_purchase_order_summarization", 5,
				"D://log//purchase_order_success2.txt",
				"D://log//purchase_order_error2.txt");
		
		purchaseOrderJobTask.runInDb();

	}

}
