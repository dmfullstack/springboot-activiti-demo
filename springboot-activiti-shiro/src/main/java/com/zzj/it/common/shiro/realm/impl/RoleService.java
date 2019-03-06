package com.zzj.it.common.shiro.realm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zzj.it.bean.ApplicationParams;
import com.zzj.it.bean.ApplicationResult;
import com.zzj.it.common.shiro.context.ActRole;
import com.zzj.it.common.shiro.context.ActRoleUser;
import com.zzj.it.common.shiro.realm.IRoleService;

@Service
public class RoleService implements IRoleService {
	
	@Value("${user.servers}")
	private String url;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<ActRole> getRoleByUser(ActRoleUser actRoleUser) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept-Charset", "utf-8");
		headers.set("Content-type", "application/json; charset=utf-8");
		ApplicationParams<ActRoleUser> applicationParams=new ApplicationParams<ActRoleUser>();
		applicationParams.setData(actRoleUser);
		HttpEntity<ApplicationParams<ActRoleUser>> formEntity = new HttpEntity<ApplicationParams<ActRoleUser>>(applicationParams, headers);
		ApplicationResult<List<ActRole>> applicationResult= restTemplate.postForObject(url+"/sysmgr/user/getUserInfo", formEntity,null);
		return applicationResult.getDate() ;
	}
	
	
}
