package com.zzj.it;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 流程操作与数据查询
 * 
 * @author admin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RecevieTaskTest {
	private static final Logger logger = LoggerFactory.getLogger(ScopeTest.class);

	@Test
	public void test() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();

		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("processes/recevie.bpmn").deploy();
		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
		logger.info("启动流程");
		ProcessInstance pi = runService.startProcessInstanceById(pd.getId());
		logger.info(pi.getId());
		Execution exe = runService.createExecutionQuery().processInstanceId(pi.getId()).onlyChildExecutions()
				.singleResult();
		logger.error("{},当前节点{}", pi.getId(), exe.getActivityId());

		// 让流程往下走
		runService.trigger(exe.getId());

		exe = runService.createExecutionQuery().processInstanceId(pi.getId()).onlyChildExecutions().singleResult();
		logger.error("{},当前节点{}", pi.getId(), exe.getActivityId());
	}
}
