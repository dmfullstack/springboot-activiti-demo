package com.zzj.it.modules.child.service;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmbededSubDelegate implements JavaDelegate {

	private static final Logger logger = LoggerFactory.getLogger(EmbededSubDelegate.class);

	@Override
	public void execute(DelegateExecution execution) {
		logger.error("遇见异常,thread",Thread.currentThread());
		throw new BpmnError("抛出异常了");

	}

}
