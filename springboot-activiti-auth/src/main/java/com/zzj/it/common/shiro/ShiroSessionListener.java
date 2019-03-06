package com.zzj.it.common.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zzj.it.common.shiro.cache.ShiroRedisTemplete;
import com.zzj.it.common.shiro.contants.ShiroSessionRedisConstant;
/**
 * session监听器
 * @author zhouzj
 */
public class ShiroSessionListener implements SessionListener{
	private static final Logger logger=LoggerFactory.getLogger(ShiroSessionListener.class);
	ShiroRedisTemplete redisTemplete;
	public ShiroSessionListener(ShiroRedisTemplete redisTemplete) {
		this.redisTemplete=redisTemplete;
	}
	@Override
	public void onStart(Session session) {
		logger.info("shiroSessionListener sessionId={}被创建",session.getId());
	}

	@Override
	public void onStop(Session session) {
		redisTemplete.delShiroData(ShiroSessionRedisConstant.SSOTOKEN_SUFFIX+session.getId()+ShiroSessionRedisConstant.SSOTOKEN_PREFIX);
		logger.info("shiroSessionListener sessionId={}被销毁",session.getId());
	}

	@Override
	public void onExpiration(Session session) {
		redisTemplete.delShiroData(ShiroSessionRedisConstant.SSOTOKEN_SUFFIX+session.getId()+ShiroSessionRedisConstant.SSOTOKEN_PREFIX);
		logger.info("shiroSessionListener sessionId={}过期",session.getId());
	}

}
