package com.zzj.it.api;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzj.it.Application;
/**
 * 定时工作测试
 * @author zhouzj
 * 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SuspendTaskTest {
	private static final Logger logger = LoggerFactory.getLogger(SuspendTaskTest.class);

	@Test
	public void test() throws InterruptedException {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();

		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("processes/suspend_task.bpmn").deploy();

		logger.info("部署id" + dep.getId());
		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
		logger.info("启动流程实例" + pd.getId());
		ProcessInstance pi = runService.startProcessInstanceById(pd.getId());
		logger.error("执行流id={}", pi.getId());
		Thread.sleep(10000);
		runService.suspendProcessInstanceById(pi.getId());
		Thread.sleep(10000);
		runService.activateProcessInstanceById(pi.getId());
		
	}

}
