package com.sh.lang.jedis;

import java.lang.reflect.Method;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description: RedisCacheConfig配置类； 缓存主要有几个要实现的类： 其一就是CacheManager缓存管理器；
 *               其二就是具体操作实现类；
 *               其三就是CacheManager工厂类（这个可以使用配置文件配置的进行注入，也可以通过编码的方式进行实现）；
 *               其四就是缓存key生产策略
 * @author Administrator
 * @date:2017年12月18日 下午2:28:42
 * @Version: V1.0
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
	private Logger logger = LoggerFactory.getLogger(RedisCacheConfig.class);
	@Value("${spring.redis.host}")
	private String redisHost;


	@Bean
	public KeyGenerator wiselyKeyGenerator() {
		logger.info("KeyGenerator create start");
		return new KeyGenerator() {
			@Override
			public Object generate(Object arg0, Method arg1, Object... arg2) {
				StringBuilder builder = new StringBuilder();
				builder.append(arg0.getClass().getName()).append("_");
				for (Object object : arg2) {
					builder.append(object.toString());
				}
				return builder.toString();
			}
		};
	}

	public JedisConnectionFactory jedisConnectionFactory() {
		logger.info("connect to redis server, redisHost={}, port={}",
				redisHost);
		// jedis池配置
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


		return null;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(
			RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate redisTemplate = new StringRedisTemplate(
				redisConnectionFactory);
		// 序列化redis的值
		Jackson2JsonRedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<Object>(
				Object.class);
		logger.info("RedisTemplate start");

		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL,
				JsonAutoDetect.Visibility.ANY);
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		redisSerializer.setObjectMapper(mapper);
		redisTemplate.setValueSerializer(redisSerializer);
		// 使用StringRedisSerializer来序列化和反序列化redis的key值
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.afterPropertiesSet();

		return redisTemplate;

	}

	// @Bean
	// public CacheManager cacheManager(RedisTemplate redisTemplate) {
	// logger.info("create CacheManager start");
	// RedisCacheManager redisCacheManager = new
	// RedisCacheManager(redisTemplate);
	// redisCacheManager.setDefaultExpiration(90*24*60*60); // 数据有效时间90天
	// return redisCacheManager;
	// }
}
