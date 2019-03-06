package com.zzj.it.common.shiro.context;

import java.io.Serializable;
import java.util.List;

import com.zzj.it.moudels.role.entity.ActRole;
import com.zzj.it.moudels.user.entity.ActUser;

public class SubjectUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;

	private String userName;

	private String phone;

	private ActUser actUser;

	private List<ActRole> roles;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ActUser getActUser() {
		return actUser;
	}

	public void setActUser(ActUser actUser) {
		this.actUser = actUser;
	}

	public List<ActRole> getRoles() {
		return roles;
	}

	public void setRoles(List<ActRole> roles) {
		this.roles = roles;
	}

}
