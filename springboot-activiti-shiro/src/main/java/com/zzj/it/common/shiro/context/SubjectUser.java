package com.zzj.it.common.shiro.context;

import java.io.Serializable;

public class SubjectUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	
	private String userName;
	
	private ActUser user;
	
	private ActRole roles;

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

	public ActUser getUser() {
		return user;
	}

	public void setUser(ActUser user) {
		this.user = user;
	}

	public ActRole getRoles() {
		return roles;
	}

	public void setRoles(ActRole roles) {
		this.roles = roles;
	}
	
	
	
}
