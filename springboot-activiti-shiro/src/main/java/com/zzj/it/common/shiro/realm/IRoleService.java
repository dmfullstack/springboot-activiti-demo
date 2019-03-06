package com.zzj.it.common.shiro.realm;

import java.util.List;

import com.zzj.it.common.shiro.context.ActRole;
import com.zzj.it.common.shiro.context.ActRoleUser;

public interface IRoleService {

	public List<ActRole> getRoleByUser(ActRoleUser actRoleUser);
}
