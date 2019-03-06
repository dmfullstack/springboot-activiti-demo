package com.zzj.it.moudels.user.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.zzj.it.bean.ApplicationParams;
import com.zzj.it.moudels.user.entity.ActUser;

@Transactional(rollbackFor=RuntimeException.class,timeout=72000,propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
public interface IActUserService {

	/**
	 * 新增用户信息
	 * @param actUser
	 * @return
	 */
	@Transactional(readOnly=false)
	public int insertUser(ActUser actUser);
	
	/**
	 * 分页获取用户信息
	 * @param applicationParams
	 * @return
	 */
	@Transactional(readOnly=true)
	public PageInfo<ActUser> getUserPage(ApplicationParams<ActUser> applicationParams);
	
	/**
	 * 获取用户详细信息
	 * @param actUser
	 * @return
	 */
	@Transactional(readOnly=true)
	public ActUser findUserByUser(ActUser actUser);
}
