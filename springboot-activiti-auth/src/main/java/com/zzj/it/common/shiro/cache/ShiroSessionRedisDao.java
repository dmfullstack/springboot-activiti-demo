package com.zzj.it.common.shiro.cache;


import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.context.annotation.Configuration;

import com.zzj.it.common.shiro.contants.ShiroSessionRedisConstant;

@Configuration
public class ShiroSessionRedisDao extends EnterpriseCacheSessionDAO {

	@Resource
	private ShiroRedisTemplete shiroRedisTemplete;

	@Resource
	private ShiroSessionRedisConstant sessionRedisConstant;

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = super.doCreate(session);
		String key = ShiroSessionRedisConstant.SSOTOKEN_SUFFIX + sessionId.toString()
				+ ShiroSessionRedisConstant.SSOTOKEN_PREFIX;
		shiroRedisTemplete.setShiroData(key, session, sessionRedisConstant.getSessionTimeout());
		return sessionId;
	}

	@Override
	public Session readSession(Serializable sessionId) throws UnknownSessionException {
		return this.doReadSession(sessionId);
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session = null;
		String key = ShiroSessionRedisConstant.SSOTOKEN_SUFFIX + sessionId.toString()
				+ ShiroSessionRedisConstant.SSOTOKEN_PREFIX;
		if (shiroRedisTemplete.existsShiro(key)) {
			session = (Session) shiroRedisTemplete.getShiroData(key);
		}
		return session;
	}

	@Override
	protected void doUpdate(Session session) {
		super.doUpdate(session);
		if (session.getId() != null) {
			String key = ShiroSessionRedisConstant.SSOTOKEN_SUFFIX + session.getId().toString()
					+ ShiroSessionRedisConstant.SSOTOKEN_PREFIX;
			if (shiroRedisTemplete.existsShiro(key)) {
				shiroRedisTemplete.setShiroData(key, session, sessionRedisConstant.getSessionTimeout());
			}
		}
	}
	@Override
	public void update(Session session) throws UnknownSessionException {
		// TODO Auto-generated method stub
		super.update(session);
	}

	@Override
	protected void doDelete(Session session) {
		super.doDelete(session);
		String key = ShiroSessionRedisConstant.SSOTOKEN_SUFFIX + session.getId().toString()
				+ ShiroSessionRedisConstant.SSOTOKEN_PREFIX;
		shiroRedisTemplete.delShiroData(key);
	}

	@Override
	public void delete(Session session) {
		uncache(session);
	}
}
