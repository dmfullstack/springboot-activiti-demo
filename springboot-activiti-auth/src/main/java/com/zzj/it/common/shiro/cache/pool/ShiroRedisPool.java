package com.zzj.it.common.shiro.cache.pool;




import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;

@Configuration
public class ShiroRedisPool {

	@Value("${redis.host}")
	private String host;
	
	@Value("${redis.port}")
	private int port;
	
	@Value("${redis.timeout}")
	private int timeout;
	
	@Value("${redis.password}")
	private String password;
	
	@Value("${redis.shiro.database}")
	private int database;
	
	@Resource
	private ShiroRedisPoolConfig config;
	
	
	
	@Bean
	public JedisPool jedisPool(){
		JedisPool jedisPool= new JedisPool(config.jedisPoolConfig(), host, port, timeout, password, database);
		return jedisPool;
	}
}
