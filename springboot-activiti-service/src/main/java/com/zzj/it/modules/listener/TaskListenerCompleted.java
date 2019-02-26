package com.zzj.it.modules.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskListenerCompleted implements TaskListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger=LoggerFactory.getLogger(TaskListenerCompleted.class);
	@Override
	public void notify(DelegateTask delegateTask) {
		logger.error("完成任务触发的");
	}

}
