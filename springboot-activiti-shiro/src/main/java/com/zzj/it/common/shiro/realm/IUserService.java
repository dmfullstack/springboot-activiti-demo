package com.zzj.it.common.shiro.realm;

import com.zzj.it.common.shiro.context.ActUser;

public interface IUserService {

	public ActUser getUserInfo(ActUser user);
}
