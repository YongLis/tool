package com.sh.lang.retry.pg;

import java.util.List;

public class PGSyncTask {

	public void insertDev() {

	}

	public static void main(String[] args) {
		String sql = "SELECT acsorderid FROM bigdata.t_acquire_detail where acsorderid in (SELECT  acsorderid from bigdata.t_acquire_detail GROUP BY acsorderid HAVING count(1) > 1) and createtime >= CAST('20200320' as TIMESTAMP) and createtime < CAST('20200401' as TIMESTAMP)";
		JobTask jobTask = new JobTask(PostgresqlDataSource.driver,
				PostgresqlDataSource.url, PostgresqlDataSource.user,
				PostgresqlDataSource.password, "", null, 0,
				"D://log//query_success.txt",
				"D://log//query_fail.txt");
		
		List<String> list = jobTask.queryData(sql);
		StringBuilder builder = new StringBuilder();
		for	(int i=0; i< list.size(); i++){
			builder.append("'").append(list.get(i)).append("'");
			if(i >0 && i%100 == 0){
				System.out.println(builder.toString());
				
				System.out.println("--------");
				builder = new StringBuilder();
			}
			else{
				builder.append(",");
			}
			
		}
	}

}
