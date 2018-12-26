package com.sh.lang.jedis;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * Redis手动存储缓存
 */
@Service
public class RedisCacheTemplate {
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheTemplate.class);
	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
	 * 存储字符串 
	 * @param key
	 * @param value
	 * @param liveTime 存活时间 （单位秒）
	 */
	public boolean set(final String key, Object value, long liveTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value, liveTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			logger.error("redis cache exception", e);
		}		
		return result;
	}
	
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			logger.error("redis cache exception", e);
		}		
		return result;
	}
	
	/**
	 * 获取数据 
	 */
	public Object get(String key) {
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		if(redisTemplate.hasKey(key)) {
			return operations.get(key);
		}
		else {
			return null;
		}
	}

	public void remove(String key) {
		redisTemplate.delete(key);
	}
	
	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

}
