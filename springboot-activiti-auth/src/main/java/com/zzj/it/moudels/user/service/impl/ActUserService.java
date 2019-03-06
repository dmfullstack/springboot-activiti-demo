package com.zzj.it.moudels.user.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzj.it.bean.ApplicationParams;
import com.zzj.it.moudels.user.dao.ActUserMapper;
import com.zzj.it.moudels.user.entity.ActUser;
import com.zzj.it.moudels.user.service.IActUserService;

@Service
public class ActUserService implements IActUserService {

	@Autowired
	private ActUserMapper actUserMapper;
	
	@Override
	public int insertUser(ActUser actUser) {
		actUser.setUserId(UUID.randomUUID().toString());
		return actUserMapper.insertSelective(actUser);
	}

	@Override
	public PageInfo<ActUser> getUserPage(ApplicationParams<ActUser> applicationParams) {
		PageHelper.startPage(applicationParams.getPageNum(), applicationParams.getPageSize());
		return new PageInfo<ActUser>(actUserMapper.findUserList(applicationParams.getData()));
	}

	@Override
	public ActUser findUserByUser(ActUser actUser) {
		return actUserMapper.getUserDetail(actUser);
	}

}
