package com.zzj.it.common.shiro.cache;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzj.it.common.shiro.contants.ShiroSessionRedisConstant;
@SuppressWarnings({"unchecked","rawtypes"})
public class ShiroRedisCache<K, V> implements Cache<K, V> {
	@Autowired
	private ShiroRedisTemplete shiroRedisTemplete;
	
	private int globErxpirTime=30;
	public ShiroRedisCache() {

	}    

	public ShiroRedisCache(int erxpirTime,ShiroRedisTemplete shiroRedisTemplete) {
		this.globErxpirTime=erxpirTime;
		this.shiroRedisTemplete=shiroRedisTemplete;
	}

	
	@Override
	public Object get(Object key) throws CacheException {
		return shiroRedisTemplete.getShiroData(getKey(key));
	}

	@Override
	public Object put(Object key, Object value) throws CacheException {
		shiroRedisTemplete.setShiroData(getKey(key), value, this.globErxpirTime);
		return shiroRedisTemplete.getShiroData(getKey(key));
	}

	@Override
	public Object remove(Object key) throws CacheException {
		Object obj=shiroRedisTemplete.getShiroData(getKey(key));
		if(obj!=null) {
			shiroRedisTemplete.delShiroData(getKey(key));
		}
		return obj;
	}

	@Override
	public void clear() throws CacheException {
		shiroRedisTemplete.batchDelShiroData();

	}

	@Override
	public int size() {
		return shiroRedisTemplete.shiroCacheSize();
	}

	@Override
	public Set keys() {
		return shiroRedisTemplete.keys();
	}

	@Override
	public Collection values() {
		Set keys=this.keys();
		List<Object> values=new ArrayList<Object>();
		for (Object key : keys) {
			values.add(shiroRedisTemplete.getShiroData(getKey(key)));
		}
		
		return values;
	}
	
	private Object getKey(Object key) {
		
		return ShiroSessionRedisConstant.SSOTOKEN_SUFFIX + key+ ShiroSessionRedisConstant.SSOTOKEN_PREFIX;
	}

}
