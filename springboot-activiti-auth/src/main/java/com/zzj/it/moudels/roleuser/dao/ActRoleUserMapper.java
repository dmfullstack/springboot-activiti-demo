package com.zzj.it.moudels.roleuser.dao;

import java.util.List;

import com.zzj.it.moudels.roleuser.entity.ActRoleUser;

public interface ActRoleUserMapper {
    int deleteByPrimaryKey(String roleUserId);

    int insert(ActRoleUser record);

    int insertSelective(List<ActRoleUser> record);

    ActRoleUser selectByPrimaryKey(String roleUserId);

    int updateByPrimaryKeySelective(List<ActRoleUser> record);

    int updateByPrimaryKey(ActRoleUser record);
}