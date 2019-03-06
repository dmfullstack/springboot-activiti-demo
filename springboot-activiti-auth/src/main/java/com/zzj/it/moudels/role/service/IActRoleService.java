package com.zzj.it.moudels.role.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.zzj.it.bean.ApplicationParams;
import com.zzj.it.moudels.role.entity.ActRole;
import com.zzj.it.moudels.roleuser.entity.ActRoleUser;

@Transactional(rollbackFor=RuntimeException.class,timeout=72000,propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
public interface IActRoleService {

	@Transactional(readOnly=false)
	public int addRole(ActRole actRole);
	
	@Transactional(readOnly=true)
	public List<ActRole> findUserRole(ActRoleUser actRoleUser);
	
	@Transactional(readOnly=true)
	public PageInfo<ActRole> getRolePageList(ApplicationParams<ActRole> applicationParams);
}
