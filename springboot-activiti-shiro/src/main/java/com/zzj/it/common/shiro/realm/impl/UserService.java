package com.zzj.it.common.shiro.realm.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zzj.it.bean.ApplicationParams;
import com.zzj.it.bean.ApplicationResult;
import com.zzj.it.common.shiro.context.ActUser;
import com.zzj.it.common.shiro.realm.IUserService;

@Service
public class UserService implements IUserService {
	
	@Value("${user.servers}")
	private String url;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ActUser getUserInfo(ActUser user) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept-Charset", "utf-8");
		headers.set("Content-type", "application/json; charset=utf-8");
		ApplicationParams<ActUser> applicationParams=new ApplicationParams<ActUser>();
		applicationParams.setData(user);
		HttpEntity<ApplicationParams<ActUser>> formEntity = new HttpEntity<ApplicationParams<ActUser>>(applicationParams, headers);
		ApplicationResult<ActUser> applicationResult= restTemplate.postForObject(url+"/sysmgr/user/getUserInfo", formEntity,null);
		return applicationResult.getDate() ;
	}
	
	
}
