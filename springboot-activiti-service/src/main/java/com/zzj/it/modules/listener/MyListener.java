package com.zzj.it.modules.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyListener implements TaskListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger=LoggerFactory.getLogger(MyListener.class);
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		logger.error("监听信息");
	}

}
