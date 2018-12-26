package com.sh.lang.jedis;
/**
 * redis运行监控器：
 * 负责监测和维护Redis服务器集群中的服务器运行状态； 
 */
public class RedisRunTimeListener implements Runnable {
	private RedisClusterManager redisClusterManager;
	public RedisRunTimeListener(RedisClusterManager redisClusterManager) {
		this.redisClusterManager = redisClusterManager;
	}
	
	@Override
	public void run() {
		if(RedisClusterManager.workList.size() > 0){
			
		}
		
	}

}
