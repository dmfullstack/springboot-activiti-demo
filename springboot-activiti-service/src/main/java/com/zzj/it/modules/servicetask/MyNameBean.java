package com.zzj.it.modules.servicetask;

import java.io.Serializable;

import org.activiti.engine.runtime.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyNameBean implements Serializable {

	final Logger logger=LoggerFactory.getLogger(MyNameBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name="augns";

	public String getName() {
		return name;
	}
	
	public void print(Execution e) {
		logger.error("执行print方法，执行流id：{}",e.getId());
	}
	

}
