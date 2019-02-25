package com.zzj.it.modules.servicetask;

import java.io.Serializable;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyDelegate implements JavaDelegate,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(MyDelegate.class);

	@Override
	public void execute(DelegateExecution execution) {
		logger.info("处理");

	}

}
