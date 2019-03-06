package com.zzj.it.common.shiro.contants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroSessionRedisConstant {

	public static final String SSOTOKEN_COOKIE_KEY = "SESSIONTOKENID";

	public static final String SSOTOKEN_PREFIX = "_SSOTOKEN";

	/**
	 * 默认超时时间
	 */
	public static final Integer SESSION_DEFAULT_TIMEOUT_TIME = 30*60;

	public static final String SSOTOKEN_SUFFIX = "SHIROSESSION_";
	
	public static final String SESSION_USER="active_user";

	@Value("${shiro.session.timeout}")
	private Integer sessionTimeout;

	public Integer getSessionTimeout() {
		if(sessionTimeout==null) {
			return SESSION_DEFAULT_TIMEOUT_TIME;
		}
		return sessionTimeout;
	}

	public void setSessionTimeout(Integer sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

}
