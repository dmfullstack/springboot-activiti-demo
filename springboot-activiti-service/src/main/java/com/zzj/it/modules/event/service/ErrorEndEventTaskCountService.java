package com.zzj.it.modules.event.service;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorEndEventTaskCountService implements JavaDelegate {

	private final Logger logger = LoggerFactory.getLogger(ErrorEndEventTaskCountService.class);

	@Override
	public void execute(DelegateExecution execution) {
		logger.error("清点人数发现异常");
		throw new BpmnError("count error");

	}

}
