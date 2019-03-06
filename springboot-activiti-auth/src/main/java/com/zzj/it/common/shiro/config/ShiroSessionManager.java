package com.zzj.it.common.shiro.config;


import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

public class ShiroSessionManager extends DefaultWebSessionManager {

	private final static String AUTH="authorization";
	 
	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		String sessionId=WebUtils.toHttp(request).getHeader(AUTH);
		request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
		request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
		request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
		return sessionId;
	}

}
