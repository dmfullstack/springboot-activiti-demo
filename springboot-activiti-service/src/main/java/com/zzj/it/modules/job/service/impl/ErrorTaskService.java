package com.zzj.it.modules.job.service.impl;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ErrorTaskService implements JavaDelegate {

	private final Logger logger=LoggerFactory.getLogger(ErrorTaskService.class);
	@Override
	public void execute(DelegateExecution execution) {
		logger.error("这是处理类");
		throw new RuntimeException("allows exception");
	}

}
