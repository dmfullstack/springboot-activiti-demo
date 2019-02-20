package com.zzj.it;

import static org.junit.Assert.*;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignalCatchEventTaskTest {

	private static final Logger logger = LoggerFactory.getLogger(ScopeTest.class);
	@Test
	public void test() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();

		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("processes/signalCatchEvent.bpmn").deploy();

		logger.info("部署id" + dep.getId());
		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
		logger.info("启动流程实例" + pd.getId());
		ProcessInstance pi = runService.startProcessInstanceById(pd.getId());
		logger.info(pi.getId());
	}

}
