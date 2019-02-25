package com.zzj.it.modules.event.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Zhkk implements JavaDelegate {
	private final Logger logger = LoggerFactory.getLogger(Zhkk.class);

	@Override
	public void execute(DelegateExecution execution) {
		logger.error("招商扣款");

	}
}
