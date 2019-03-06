package com.zzj.it.common.shiro.filter;


import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.GsonBuilder;
import com.zzj.it.bean.ApplicationResult;
import com.zzj.it.common.shiro.cache.ShiroRedisTemplete;
import com.zzj.it.common.shiro.config.ShiroConfigContext;
import com.zzj.it.common.shiro.contants.Const;
import com.zzj.it.util.StringUtils;

/**
 * 权限拦截器
 * 
 * @author zhouzj
 * @version 2.0
 */
public class ShiroAuthFilter extends PermissionsAuthorizationFilter {
	private static final String UTF_8 = "UTF-8";
	private static final String APPLICATION_JSON = "application/json";
	private static final String X_REQUESTED_WITH = "x-requested-with";
	private static final String ACCESS_CONTROL_REQUEST_HEADER = "Access-Control-Request-Header";
	private ShiroRedisTemplete shiroRedisTemplete;

	private ShiroConfigContext shiroConfigContext;

	public ShiroAuthFilter(ShiroRedisTemplete shiroRedisTemplete, ShiroConfigContext shiroConfigContext) {
		this.shiroConfigContext = shiroConfigContext;
		this.shiroConfigContext = shiroConfigContext;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest req, ServletResponse res) throws IOException {
		Subject subject = SecurityUtils.getSubject();
		Serializable serssionId = subject.getSession().getId();
		GsonBuilder builder = new GsonBuilder().disableHtmlEscaping();
		HttpServletRequest request = (HttpServletRequest) req;
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession().getServletContext());
		HttpServletResponse response = (HttpServletResponse) res;
		/*shiroRedisTemplete = (ShiroRedisTemplete) ac.getBean("shiroRedisTemplete");
		shiroConfigContext = (ShiroConfigContext) ac.getBean("shiroConfigContext");*/
		Session session = builder.create()
				.fromJson(builder.create().toJson(shiroRedisTemplete.getShiroData(serssionId)), Session.class);
		String requestWith = request.getHeader(ACCESS_CONTROL_REQUEST_HEADER);
		if (session == null) {
			if (StringUtils.isNotBank(requestWith) && X_REQUESTED_WITH.equals(requestWith)) {
				ApplicationResult<Object> result=new ApplicationResult<Object>();
				result.setState(401);
				result.setMessage("未登陆用户");
				response.setCharacterEncoding(UTF_8);
				response.setContentType(APPLICATION_JSON);
				response.getWriter().write(builder.create().toJson(result));
				return false;
			} else {
				response.sendRedirect(shiroConfigContext.getLogin());
				return false;
			}
		}
		Object obj = session.getAttribute(Const.SESSION_USER);
		if (obj != null) {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				//

			}
		}
		return true;
	}
}
