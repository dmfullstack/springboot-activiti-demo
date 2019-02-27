package com.zzj.it.moudels.example.service;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zzj.it.moudels.example.entity.User;

public class ActRuleExample01Detegate implements JavaDelegate {

	private static final Logger logger=LoggerFactory.getLogger(ActRuleExample01Detegate.class);
	@Override
	public void execute(DelegateExecution execution) {

		@SuppressWarnings("unchecked")
		List<User> users=(List<User>) execution.getVariable("result");
		for (User user : users) {
			logger.error("客戶{}消费金额为{}",user.getIdentity(),user.getResult());
		}
		
	}

}
