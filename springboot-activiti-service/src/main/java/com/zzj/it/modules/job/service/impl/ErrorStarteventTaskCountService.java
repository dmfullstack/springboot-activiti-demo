package com.zzj.it.modules.job.service.impl;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service
public class ErrorStarteventTaskCountService implements JavaDelegate {
	private final Logger logger = LoggerFactory.getLogger(ErrorStarteventTaskCountService.class);

	@Override
	public void execute(DelegateExecution execution) {
		logger.error("清点人数抛出异常");
		throw new BpmnError("abc");
	}

}
