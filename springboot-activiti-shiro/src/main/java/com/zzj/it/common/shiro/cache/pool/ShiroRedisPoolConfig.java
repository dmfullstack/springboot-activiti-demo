package com.zzj.it.common.shiro.cache.pool;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPoolConfig;


@Configuration
public class ShiroRedisPoolConfig {

	@Value("${redis.shiro.maxIdle}")
	private int maxIdle;

	@Value("${redis.shiro.maxTotal}")
	private int maxTotal;

	@Value("${redis.shiro.maxWaitMillis}")
	private long maxWaitMillis;

	@Value("${redis.shiro.minIdle}")
	private int minIdle;

	@Value("${redis.shiro.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${redis.shiro.testOnReturn}")
	private boolean testOnReturn;

	@Value("${redis.shiro.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${redis.shiro.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${redis.shiro.numTestsPerEvictionRun}")
	private int numTestsPerEvictionRun;

	@Value("${redis.shiro.minEvictableIdleTimeMillis}")
	private long minEvictableIdleTimeMillis;

	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxTotal(maxTotal);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		jedisPoolConfig.setMinIdle(minIdle);
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		jedisPoolConfig.setTestOnReturn(testOnReturn);
		jedisPoolConfig.setTestWhileIdle(testWhileIdle);
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);

		return jedisPoolConfig;
	}
}
