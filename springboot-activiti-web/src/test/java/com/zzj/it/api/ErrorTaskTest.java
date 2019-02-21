package com.zzj.it.api;

import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzj.it.Application;

/**
 * 不可执行任务
 * 
 * @author zhouzj
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ErrorTaskTest {
	private static final Logger logger = LoggerFactory.getLogger(ErrorTaskTest.class);

	@Test
	public void test() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();

		ManagementService managementService = engine.getManagementService();
		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("processes/error_task.bpmn").deploy();

		logger.info("部署id" + dep.getId());
		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
		logger.info("启动流程实例" + pd.getId());
		ProcessInstance pi = runService.startProcessInstanceById(pd.getId());
		logger.error("执行流id={}", pi.getId());
		// 重新设置执行次数
		Job job = managementService.createJobQuery().singleResult();
		managementService.setJobRetries(job.getId(), 1);
		try {
			managementService.executeJob(job.getId());
		} catch (Exception e) {
			logger.error("error={}", e);
		}
	}

}
