package com.zzj.it.web.sysmgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzj.it.bean.ApplicationParams;
import com.zzj.it.bean.ApplicationResult;
import com.zzj.it.moudels.user.entity.ActUser;
import com.zzj.it.moudels.user.service.IActUserService;

@RestController
@RequestMapping("/sysmgr/user")
public class UserControl {
	
	@Autowired
	private IActUserService userService;

	@RequestMapping("/getUserInfo")
	public ApplicationResult<ActUser> getUserInfo(@RequestBody ApplicationParams<ActUser> applicationParams){
		ApplicationResult<ActUser> applicationResult=new ApplicationResult<ActUser>();
		applicationResult.setDate(userService.findUserByUser(applicationParams.getData()));
		return applicationResult;
	}
}
