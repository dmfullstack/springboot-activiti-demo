package com.zzj.it.moudels.user.dao;

import java.util.List;

import com.zzj.it.moudels.user.entity.ActUser;

public interface ActUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(ActUser record);

    int insertSelective(ActUser record);

    ActUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(ActUser record);

    int updateByPrimaryKey(ActUser record);
    
    /**
     * 获取用户列表
     * @param record
     * @return
     */
    List<ActUser> findUserList(ActUser record);
    /**
     * 获取用户详细信息
     * @param record
     * @return
     */
    ActUser getUserDetail(ActUser record);
}