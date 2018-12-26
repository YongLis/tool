package com.sh.lang.jedis;

import java.util.ArrayList;
import java.util.List;

public class RedisClusterManager {
	public static List<String> workList = new ArrayList<String>();
	public static List<String> unworkList = new  ArrayList<String>();
	
	/**
	 * 添加节点 
	 */
	public void addNode(String redisHost){
		// 检测服务
		
		// 添加节点
		workList.add(redisHost);
	}
	
	/**
	 * 删除节点 
	 */
	public void removeNode(String redisHost){
		// 添加节点
		workList.remove(redisHost);
		unworkList.add(redisHost);
	}

}
