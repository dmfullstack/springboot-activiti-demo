package com.zzj.it.common.shiro.config;


import java.util.LinkedHashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * shiro参数配置文件加载
 * 
 * @author zhouzj
 * @version 2.0
 */
@Configuration
@ConfigurationProperties(prefix = "shiroFilter")
public class ShiroConfigContext {

	private LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

	private String success;

	private String login;

	private String unauth;

	private String usernameParam;

	private String passwordParam;

	private String rememberMeParam;

	private String hashAlgorithmName;

	private int hashIterations = 1;

	private int shiroSessionTimeOut = 30 * 1000 * 60;
	
	private String cookieName;
	
	private int maxAgeTime=-1;
	
	private String httpUrl;
	
	private LinkedHashMap<String, String> noRedirects = new LinkedHashMap<String, String>();

	public LinkedHashMap<String, String> getFilterChainDefinitionMap() {
		return filterChainDefinitionMap;
	}

	public void setFilterChainDefinitionMap(LinkedHashMap<String, String> filterChainDefinitionMap) {
		this.filterChainDefinitionMap = filterChainDefinitionMap;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getUnauth() {
		return unauth;
	}

	public void setUnauth(String unauth) {
		this.unauth = unauth;
	}

	public String getUsernameParam() {
		return usernameParam;
	}

	public void setUsernameParam(String usernameParam) {
		this.usernameParam = usernameParam;
	}

	public String getPasswordParam() {
		return passwordParam;
	}

	public void setPasswordParam(String passwordParam) {
		this.passwordParam = passwordParam;
	}

	public String getRememberMeParam() {
		return rememberMeParam;
	}

	public void setRememberMeParam(String rememberMeParam) {
		this.rememberMeParam = rememberMeParam;
	}

	public String getHashAlgorithmName() {
		return hashAlgorithmName;
	}

	public void setHashAlgorithmName(String hashAlgorithmName) {
		this.hashAlgorithmName = hashAlgorithmName;
	}

	public int getHashIterations() {
		return hashIterations;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	public int getShiroSessionTimeOut() {
		return shiroSessionTimeOut;
	}

	public void setShiroSessionTimeOut(int shiroSessionTimeOut) {
		this.shiroSessionTimeOut = shiroSessionTimeOut;
	}

	public String getCookieName() {
		return cookieName;
	}

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

	public int getMaxAgeTime() {
		return maxAgeTime;
	}

	public void setMaxAgeTime(int maxAgeTime) {
		this.maxAgeTime = maxAgeTime;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public LinkedHashMap<String, String> getNoRedirects() {
		return noRedirects;
	}

	public void setNoRedirects(LinkedHashMap<String, String> noRedirects) {
		this.noRedirects = noRedirects;
	}
	
	
	
	

}
