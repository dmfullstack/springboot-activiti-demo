package com.zzj.it.common.shiro.config;

import java.util.LinkedHashMap;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

	@Value("${shiro.filterMap}")
	private LinkedHashMap<String, String> filterChinDefinitionMap=new LinkedHashMap<String, String>();
	
	
	
	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChinDefinitionMap);
		
		return shiroFilterFactoryBean;
	}
	
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		hashedCredentialsMatcher.setHashIterations(1);
		return hashedCredentialsMatcher;
	}
	@Bean
	public MyShiroReaml myShiroRealm() {
		MyShiroReaml reaml=new MyShiroReaml();
		reaml.setCredentialsMatcher(hashedCredentialsMatcher());
		reaml.setCachingEnabled(Boolean.TRUE);
		reaml.setAuthenticationCachingEnabled(Boolean.TRUE);
		reaml.setAuthenticationCacheName("authenticationCache");
		return reaml;
	}
	@Bean 
	public ShiroSessionManager shiroSessionManager() {
		
		return new ShiroSessionManager();
	}
	@Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setSessionManager(shiroSessionManager());
        return securityManager;
    }
	
	

	
	@Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        
        return authorizationAttributeSourceAdvisor;
    }

	
			
	
}
