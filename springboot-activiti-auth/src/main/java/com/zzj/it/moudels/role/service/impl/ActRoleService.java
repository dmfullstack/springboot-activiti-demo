package com.zzj.it.moudels.role.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzj.it.bean.ApplicationParams;
import com.zzj.it.common.shiro.context.SubjectUser;
import com.zzj.it.moudels.role.dao.ActRoleMapper;
import com.zzj.it.moudels.role.entity.ActRole;
import com.zzj.it.moudels.role.service.IActRoleService;
import com.zzj.it.moudels.roleuser.entity.ActRoleUser;

@Service
public class ActRoleService implements IActRoleService {

	@Autowired
	private ActRoleMapper actRoleMapper;
	@Override
	public int addRole(ActRole actRole) {
		SubjectUser subjectUser=(SubjectUser) SecurityUtils.getSubject().getPrincipal();
		actRole.setCreateBy(subjectUser.getUserId());
		actRole.setLastUpdateBy(subjectUser.getUserId());
		actRole.setRoleId(UUID.randomUUID().toString());
		return actRoleMapper.insert(actRole);
	}

	@Override
	public List<ActRole> findUserRole(ActRoleUser actRoleUser) {
		return actRoleMapper.getRoleByUser(actRoleUser);
	}

	@Override
	public PageInfo<ActRole> getRolePageList(ApplicationParams<ActRole> applicationParams) {
		PageHelper.startPage(applicationParams.getPageNum(), applicationParams.getPageSize());
		return new PageInfo<ActRole>(actRoleMapper.getRolePageList(applicationParams.getData()));
	}

}
