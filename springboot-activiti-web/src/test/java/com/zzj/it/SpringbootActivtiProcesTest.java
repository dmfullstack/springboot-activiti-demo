package com.zzj.it;

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

/**
 * 测试执行流
 * 
 * @author admin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringbootActivtiProcesTest {
	private static final Logger logger = LoggerFactory.getLogger(SpringbootActivtiProcesTest.class);

	@Test
	public void test() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();
		
		
		logger.info("部署");
		Deployment dp = rs.createDeployment().addClasspathResource("processes/demo02.bpmn")
				.deploy();
		
		logger.info("部署id"+dp.getId());
		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dp.getId()).singleResult();
		logger.info("启动流程实例"+pd.getId());
		ProcessInstance pi= runService.startProcessInstanceById(pd.getId());
		
		logger.info(pi.getId());
	}

}
