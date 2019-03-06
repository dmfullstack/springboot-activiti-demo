package com.zzj.it.moudels.role.dao;

import java.util.List;

import com.zzj.it.moudels.role.entity.ActRole;
import com.zzj.it.moudels.roleuser.entity.ActRoleUser;

public interface ActRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(ActRole record);

    int insertSelective(ActRole record);

    ActRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(ActRole record);

    int updateByPrimaryKey(ActRole record);
    /**
     * 获取用户角色信息
     * @param actRoleUser
     * @return
     */
    List<ActRole> getRoleByUser(ActRoleUser actRoleUser);
    
    List<ActRole> getRolePageList(ActRole record);
}