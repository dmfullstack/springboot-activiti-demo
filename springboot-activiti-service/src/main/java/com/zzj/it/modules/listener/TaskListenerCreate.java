package com.zzj.it.modules.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.el.FixedValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskListenerCreate implements TaskListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TaskListenerCreate.class);
	private FixedValue userName;

	public FixedValue getUserName() {
		return userName;
	}

	public void setUserName(FixedValue userName) {
		this.userName = userName;
	}

	@Override
	public void notify(DelegateTask delegateTask) {

		logger.error("初始化任务userName={}", this.getUserName().getValue(delegateTask.getExecution()));
	}

}
