package com.zzj.it.common.shiro.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzj.it.exception.MyRunException;
import com.zzj.it.moudels.user.entity.ActUser;
import com.zzj.it.moudels.user.service.IActUserService;
import com.zzj.it.util.StringUtils;


public class MyShiroReaml extends AuthenticatingRealm{

	@Autowired
	private IActUserService userService;
	
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName=(String)token.getPrincipal();
		if( StringUtils.isBlank(userName)) {
			throw new MyRunException(403,"user name can  not bean a null!");
		}
		ActUser actUser = new ActUser();
		actUser.setUserName(userName);
		actUser=userService.findUserByUser(actUser);
		if(actUser==null) {
			throw new UnknownAccountException("user does not exist !");
		}
		return null;
	}

}
