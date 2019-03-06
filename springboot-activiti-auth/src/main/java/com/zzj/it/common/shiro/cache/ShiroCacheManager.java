package com.zzj.it.common.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroCacheManager implements CacheManager {

	private static final Logger logger = LoggerFactory.getLogger(ShiroCacheManager.class);

	private int expirTime = 30;
	@Autowired
	private ShiroRedisTemplete shiroRedisTemplete; 

	public int getExpirTime() {
		return expirTime;
	}

	public void setExpirTime(int expirTime) {
		this.expirTime = expirTime;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		logger.info("进入缓存服务类");
		return new ShiroRedisCache<K, V>(expirTime,shiroRedisTemplete);
	}

}
