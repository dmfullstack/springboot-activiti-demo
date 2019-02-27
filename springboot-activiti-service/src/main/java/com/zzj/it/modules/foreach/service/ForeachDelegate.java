package com.zzj.it.modules.foreach.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForeachDelegate implements JavaDelegate {

	private final static Logger logger=LoggerFactory.getLogger(ForeachDelegate.class);
	@Override
	public void execute(DelegateExecution execution) {

		logger.error("处理服务,{}",execution.getVariable("data"));
	}

}
