package com.zzj.it.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
/**
 * 依赖重定向
 * @author zhouzj
 *
 */
@Component
public class CorsFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse=(HttpServletResponse) response;
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod());
		httpResponse.setHeader("Access-Control-Max-Age", "3600");
		httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
		httpResponse.addHeader("Access-Control-Expose-Headers", "GID,SID,X-Requested-With,x-requested-with,Authorization,authorization,x-forward-for,X-Forward-For,X-Real-IP");
		httpRequest.getHeader("authorization");
		if(httpRequest.getMethod().equals("OPTIONS")) {
			httpResponse.setStatus(HttpServletResponse.SC_OK);
			return ;
		}
		chain.doFilter(httpRequest, httpResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
