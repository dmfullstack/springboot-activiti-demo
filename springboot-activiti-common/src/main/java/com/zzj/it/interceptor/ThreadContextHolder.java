package com.zzj.it.interceptor;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 线程上下文持有工具类
 * 
 * @author jidw
 * 
 */
public class ThreadContextHolder {
	private static final String HTTP_SERVLET_REQUEST = "HTTP_SERVLET_REQUEST";
	// app端当前登录用户的用户id
	private static final String APP_CURRENT_LOGIN_USER_ID = "APP_CURRENT_LOGIN_USER_ID";
	public static final ThreadLocal<Map<String, Object>> holder = new ThreadLocal<Map<String, Object>>() {
		@Override
		protected Map<String, Object> initialValue() {
			return new HashMap<String, Object>();
		}
	};

	public static void putHttpServletRequest(HttpServletRequest request) {
		holder.get().put(APP_CURRENT_LOGIN_USER_ID,request.getParameter("loginId"));
		holder.get().put(HTTP_SERVLET_REQUEST, request);
	}

	// 获取当前的HttpServlet
	public static HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) holder.get().get(HTTP_SERVLET_REQUEST);
	}

	// 获取App端当前登录用户的用户Id
	public static String getAppCurrentLoginUserId() {
		return (String) holder.get().get(APP_CURRENT_LOGIN_USER_ID);
	}

	public static void reset() {
		holder.get().clear();
	}
}