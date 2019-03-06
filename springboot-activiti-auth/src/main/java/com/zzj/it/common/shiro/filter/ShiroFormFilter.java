package com.zzj.it.common.shiro.filter;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.google.gson.GsonBuilder;
import com.zzj.it.common.shiro.cache.ShiroRedisTemplete;
import com.zzj.it.common.shiro.config.ShiroConfigContext;
import com.zzj.it.util.StringUtils;

public class ShiroFormFilter extends FormAuthenticationFilter {

	private static final String UTF_8 = "UTF-8";
	private static final String APPLICATION_JSON = "application/json";
	private static final String X_REQUESTED_WITH = "X-Requested-With";
	private static final String ACCESS_CONTROL_REQUEST_HEADER = "XMLHttpRequest";
	private ShiroConfigContext shiroConfigContext;

	private ShiroRedisTemplete shiroRedisTemplete;

	public ShiroConfigContext getShiroConfigContext() {
		return shiroConfigContext;
	}

	public void setShiroConfigContext(ShiroConfigContext shiroConfigContext) {
		this.shiroConfigContext = shiroConfigContext;
		
	}

	public ShiroRedisTemplete getShiroRedisTemplete() {
		return shiroRedisTemplete;
	}

	public void setShiroRedisTemplete(ShiroRedisTemplete shiroRedisTemplete) {
		this.shiroRedisTemplete = shiroRedisTemplete;
	}

	public ShiroFormFilter(ShiroConfigContext shiroConfigContext) {
		this.shiroConfigContext = shiroConfigContext;
		if (shiroConfigContext != null) {
			this.setUsernameParam(shiroConfigContext.getUsernameParam());
			this.setPasswordParam(shiroConfigContext.getPasswordParam());
			this.setRememberMeParam(shiroConfigContext.getRememberMeParam());
		}
	}

	@Override
	protected boolean onAccessDenied(ServletRequest req, ServletResponse res) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		boolean isLogin = subject.isAuthenticated();
		if(isLogin) {
			return true;
		}
		GsonBuilder builder = new GsonBuilder().disableHtmlEscaping();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url=shiroConfigContext.getLogin();
		String urlParam=request.getQueryString();
		String reqUrl=shiroConfigContext.getHttpUrl()+request.getContextPath();
		
		String requestWith = request.getHeader(X_REQUESTED_WITH);
		if (StringUtils.isNotBank(requestWith) && ACCESS_CONTROL_REQUEST_HEADER.equals(requestWith)) {
			Map<String, Object> map=new HashMap<String, Object>();
			response.setCharacterEncoding(UTF_8);
			response.setContentType(APPLICATION_JSON);
			map.put("status", 403);
			map.put("msg", "未登录用户");
			Map<String, Object> data=new HashMap<String, Object>();
			data.put("referer", request.getHeader("Referer"));
			map.put("data", data);
			response.getWriter().write(builder.create().toJson(map));
			return false;
		} else {
			//存储最近一次访问的请求,注意，此方法会保存后跳转到登陆页
//			saveRequestAndRedirectToLogin(request, response);
			LinkedHashMap<String, String> noRedirects =shiroConfigContext.getNoRedirects();
			boolean isNoRedirect=false;
			for (String key : noRedirects.keySet()) {
				if(reqUrl.equals(noRedirects.get(key))) {
					isNoRedirect=true;
					break;
				}
			}
			if(isNoRedirect) {
				if(StringUtils.isNotBank(urlParam)) {
					url+="?"+urlParam;
				}
				response.sendRedirect(url);
			}else {
				if(StringUtils.isNotBank(urlParam)) {
					reqUrl+="?"+urlParam;
				}
				response.sendRedirect(url+"?reqUrl='"+reqUrl+"'");
			}
			return false;
		}
	}
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		// TODO Auto-generated method stub
		return super.createToken(request, response);
	}
}
