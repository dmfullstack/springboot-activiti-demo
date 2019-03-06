package com.zzj.it.common.shiro.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zzj.it.common.shiro.ShiroSessionListener;
import com.zzj.it.common.shiro.cache.ShiroCacheManager;
import com.zzj.it.common.shiro.cache.ShiroRedisTemplete;
import com.zzj.it.common.shiro.cache.ShiroSessionRedisDao;
import com.zzj.it.common.shiro.filter.ShiroAdviceFilter;
import com.zzj.it.common.shiro.filter.ShiroAuthFilter;
import com.zzj.it.common.shiro.filter.UserFormFilter;
import com.zzj.it.common.shiro.realm.AuthRealm;
 

@Configuration
public class ShiroConfig {
	private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

	@Resource
	private ShiroConfigContext shiroConfigContext;

	@Resource
	private ShiroSessionRedisDao shiroSessionRedisDao;

	@Autowired
	private ShiroRedisTemplete shiroRedisTemplete;

	@Autowired
	private HashedCredentialsMatcher hashedCredentialsMatcher;
	
	@Autowired
	private ShiroSessionListener sessionListener;
	
	@Autowired
	private ShiroCacheManager cacheManager;

	@Autowired
	private SessionManager sessionManager;

	@Autowired
	private CookieRememberMeManager cookieRememberMeManager;

	@Autowired
	private DefaultWebSecurityManager defaultWebSecurityManager;

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		logger.error("开始注册shiro拦截器");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroConfigContext.getFilterChainDefinitionMap());
		shiroFilterFactoryBean.setSuccessUrl(shiroConfigContext.getSuccess());
		shiroFilterFactoryBean.setUnauthorizedUrl(shiroConfigContext.getUnauth());
		shiroFilterFactoryBean.setLoginUrl(shiroConfigContext.getLogin());
		// 拦截器配置
		Map<String, Filter> filters = new HashMap<String, Filter>();
		filters.put("shiroLoginFilter", new ShiroAdviceFilter(shiroRedisTemplete, shiroConfigContext));
		filters.put("perms", new ShiroAuthFilter(shiroRedisTemplete, shiroConfigContext));
		filters.put("authc", createUserFormFilter());
		shiroFilterFactoryBean.setFilters(filters);
		return shiroFilterFactoryBean;
	}

	// @Bean
	public UserFormFilter createUserFormFilter() {
		UserFormFilter shiroFormFilter = new UserFormFilter(shiroConfigContext);
		shiroFormFilter.setShiroRedisTemplete(shiroRedisTemplete);
		return shiroFormFilter;
	}

	/**
	 * 凭证校验
	 * 
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName(shiroConfigContext.getHashAlgorithmName());
		hashedCredentialsMatcher.setHashIterations(shiroConfigContext.getHashIterations());
		return hashedCredentialsMatcher;
	}

	@Bean
	public AuthRealm customRealm() {
		AuthRealm authRealm = new AuthRealm();
		authRealm.setCredentialsMatcher(hashedCredentialsMatcher);
		authRealm.setCachingEnabled(true);
		authRealm.setAuthenticationCachingEnabled(true);
		authRealm.setAuthenticationCacheName("authenticationCache");
		return authRealm;
	}

	@Bean
	public ShiroCacheManager cacheManager() {
		ShiroCacheManager shiroCacheManager = new ShiroCacheManager();
		shiroCacheManager.setExpirTime(shiroConfigContext.getShiroSessionTimeOut());
		return shiroCacheManager;
	}

	@Bean(name = "sessionListeners")
	public ShiroSessionListener shiroSessionListener() {

		return new ShiroSessionListener(shiroRedisTemplete);
	}

	@Bean
	public SessionManager sessionManager() {
		ShiroSessionManager sessionManager = new ShiroSessionManager();
		sessionManager.setSessionDAO(shiroSessionRedisDao);
		sessionManager.setGlobalSessionTimeout(shiroConfigContext.getShiroSessionTimeOut()*1000);
		sessionManager.setCacheManager(cacheManager);
		List<SessionListener> sessionListeners = new ArrayList<SessionListener>();
		sessionListeners.add(sessionListener);
		sessionManager.setSessionListeners(sessionListeners);
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setSessionIdCookie(this.SessionIdCookie());
		return sessionManager;
	}

	@Bean
	public CookieRememberMeManager cookieRememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(this.simpleCookie());
		return cookieRememberMeManager;

	}

	@Bean(name = "rememberMeCookie")
	public SimpleCookie simpleCookie() {
		SimpleCookie simpleCookie = new SimpleCookie(shiroConfigContext.getRememberMeParam());
		simpleCookie.setName(Base64.decodeToString(shiroConfigContext.getCookieName()));
		simpleCookie.setMaxAge(shiroConfigContext.getMaxAgeTime());
		return simpleCookie;
	}

	@Bean(name = "sessionIdCookie")
	public SimpleCookie SessionIdCookie() {
		SimpleCookie simpleCookie = new SimpleCookie(shiroConfigContext.getCookieName());
		simpleCookie.setMaxAge(-1);
		return simpleCookie;
	}

	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setSessionManager(sessionManager);
		defaultWebSecurityManager.setCacheManager(cacheManager);
		defaultWebSecurityManager.setRealm(customRealm());
		defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager);
		return defaultWebSecurityManager;
	}

	/**
	 * 开启aop注解支持
	 * 
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor attributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		attributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
		return attributeSourceAdvisor;
	}

}
