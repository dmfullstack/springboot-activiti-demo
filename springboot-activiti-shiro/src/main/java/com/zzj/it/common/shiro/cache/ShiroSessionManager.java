package com.zzj.it.common.shiro.cache;


import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import com.zzj.it.util.StringUtils;

public class ShiroSessionManager extends DefaultWebSessionManager {

	private final static String AUTTHORIZATION="Authorization";
	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		String sessionId=WebUtils.toHttp(request).getHeader(AUTTHORIZATION);
		if(StringUtils.isBank(sessionId)) {
			sessionId=WebUtils.toHttp(request).getHeader(AUTTHORIZATION.toLowerCase());
		}
		String paramSessionId=request.getParameter("_sid");
		if(StringUtils.isNotBank(sessionId)) {
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
			return sessionId;
		}else if(StringUtils.isNotBank(paramSessionId)){
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, paramSessionId);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
			return paramSessionId;
		}
		
		return super.getSessionId(request, response);
	}
	
}
