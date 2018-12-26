package com.sh.lang.jedis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.JedisPool;


public class JedisPoolFactory {
//	private Map<String, JedisPool> poolMap = new HashMap<String, JedisPool>();
	LinkedList<JedisPool> poolList = new LinkedList<JedisPool>();
	private JedisPool masterJedisPool;
	private String master;
	private String slave; // slave服务地址用英文逗号隔开
	public JedisPoolFactory(String master, String slave){
		this.master = master;
		this.slave = slave;
	}
	
	
	public void initPool() throws Exception{
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		// 设置最大连接数为默认值的 5 倍
		poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL * 4); // 默认8
		// 设置最大空闲连接数为默认值的 3 倍
		poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE * 2); // 默认8
		// 设置最小空闲连接数为默认值的 2 倍
		poolConfig.setMinIdle(1); // 初始化1
		// 设置开启 jmx 功能
		poolConfig.setJmxEnabled(true);
		// 设置连接池没有连接后客户端的最大等待时间 ( 单位为毫秒 )
		poolConfig.setMaxWaitMillis(10*60*1000); // 10分钟无访问释放对象
		
		if(StringUtils.isEmpty(master) && StringUtils.isEmpty(slave)){
			throw new Exception("redis主从服务地址不允许都为空");
		}
		
		if(StringUtils.isNotEmpty(master)){
			masterJedisPool = new JedisPool(poolConfig, master);
			// 
			String[] slaves = slave.split(",");
			for(String tmp : slaves ){
				JedisPool slaveJedisPool = new JedisPool(poolConfig, tmp);
				poolList.add(slaveJedisPool);
			}
		}
		else{
			// 未指定主服务地址，从从服务中选一个作为主服务器
			String[] slaves = slave.split(",");
			for(int i=0; i<slaves.length; i++){
				if(i==0){
					masterJedisPool = new JedisPool(poolConfig, slaves[i]);
				}
				else{
					JedisPool jedisPool = new JedisPool(poolConfig, slaves[i]);
					poolList.add(jedisPool);
				}
			}
		}
	}


	public JedisPool getMasterJedisPool() {
		return masterJedisPool;
	}
	
	public JedisPool getSlaveJedisPool(){
		return null;
	}
	

}
