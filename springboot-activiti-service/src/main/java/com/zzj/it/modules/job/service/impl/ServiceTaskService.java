package com.zzj.it.modules.job.service.impl;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceTaskService implements  JavaDelegate{

	private final Logger logger=LoggerFactory.getLogger(ServiceTaskService.class);
	@Override
	public void execute(DelegateExecution execution) {
		logger.error("这是处理类");
	}

}
