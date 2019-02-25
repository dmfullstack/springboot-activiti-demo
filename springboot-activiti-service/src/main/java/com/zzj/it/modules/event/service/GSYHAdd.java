package com.zzj.it.modules.event.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GSYHAdd implements JavaDelegate {

	private final Logger logger = LoggerFactory.getLogger(GSYHAdd.class);

	@Override
	public void execute(DelegateExecution execution) {
		logger.error("工商银行加钱");

	}

}
