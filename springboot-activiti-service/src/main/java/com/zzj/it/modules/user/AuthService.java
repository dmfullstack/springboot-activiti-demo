package com.zzj.it.modules.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AuthService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<String> getCandidateUsers(){
		List<String> users=new ArrayList<String>();
		users.add("juelA");
		users.add("juelB");
		return users;
		
	}

}
