package com.zzj.it.common.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zzj.it.common.shiro.context.ActUser;
import com.zzj.it.common.shiro.context.SubjectUser;
import com.zzj.it.common.shiro.util.MySimpleByteSource;
import com.zzj.it.exception.MyRunException;
import com.zzj.it.util.StringUtils;

@Component
public class AuthRealm extends AuthenticatingRealm {

 
	@Autowired
	private IUserService userService;

	@Override
	public void setName(String name) {
		super.setName("authRealm");
	}
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName=(String) token.getPrincipal();
		if(StringUtils.isBank(userName)) {
			throw new MyRunException("用户名不能为空!");
		}
		
		ActUser user=new ActUser();
		user.setUserName(userName);
		ActUser userInfo=userService.getUserInfo(user);
		if(userInfo==null) {
			throw new MyRunException(403,"用户不存在");
		}
		String password=userInfo.getPassword();
		SubjectUser subjectUser=new SubjectUser();
		subjectUser.setUser(userInfo);
		
		return new SimpleAuthenticationInfo(subjectUser, password, new MySimpleByteSource("eteokues"), this.getName());
	}

}
