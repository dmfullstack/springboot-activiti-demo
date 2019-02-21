package com.zzj.it.api;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzj.it.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class ActivitiTest {

	private static final Logger logger=LoggerFactory.getLogger(ActivitiTest.class);
	@Test
	public void test() {
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService repositoryService=engine.getRepositoryService();
		RuntimeService runtimeService= engine.getRuntimeService();
		TaskService taskService=engine.getTaskService();
		logger.info("部署");
		repositoryService.createDeployment().addClasspathResource("processes/demo01.bpmn").deploy();
		
		ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("myProcess");
		logger.info("启动流程节点");
		Task task= taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
		logger.info("当前流程节点："+task.getName());
		taskService.complete(task.getId());
		logger.info("学生完成请假");
		task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
		taskService.complete(task.getId());
		logger.info("流程结束"+task);
		task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
		logger.info("流程结束"+task);
		engine.close();
	}

}
