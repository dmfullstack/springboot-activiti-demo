package com.zzj.it.common.shiro.factory;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.DefaultWebSessionContext;

import com.zzj.it.util.StringUtils;
/**
 * 自定义session
 * @author zhouzj
 * @version 1.3.3
 */
public class ShiroSessionFactory implements SessionFactory{

	@Override
	public Session createSession(SessionContext sessionContext) {
		ShiroSession session=new ShiroSession();
		HttpServletRequest request=(HttpServletRequest) sessionContext.get(DefaultWebSessionContext.class.getName()+".SERVLET_REQUEST");
		session.setHost(getIpAddress(request));
		return session;
	}
	
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Real_IP");
		String localIp="127.0.0.1";
		if(StringUtils.isBank(ip) || ip.equalsIgnoreCase(localIp) ||"unknow".equals(ip)) {
			ip=request.getHeader("Proxy-Client-IP");
		}
		if(StringUtils.isBank(ip)  || ip.equalsIgnoreCase(localIp) ||"unknow".equals(ip)) {
			ip=request.getHeader("WL-Proxy-Client-ip");
		}
		if(StringUtils.isBank(ip)  || ip.equalsIgnoreCase(localIp) ||"unknow".equals(ip)) {
			ip=request.getRemoteAddr();
		}
		return ip;
		
	}

}
