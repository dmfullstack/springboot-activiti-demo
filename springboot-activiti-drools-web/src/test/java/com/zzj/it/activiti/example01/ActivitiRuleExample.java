package com.zzj.it.activiti.example01;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzj.it.Application;
import com.zzj.it.moudels.example.entity.User;

/**
 * 规则引擎
 * 一直跑步成功，后面再来看这个问题
 * @author zhouzj
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ActivitiRuleExample {
	private static final Logger logger = LoggerFactory.getLogger(ActivitiRuleExample.class);

	@Test
	public void test() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();
		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("processes/act-rule-example.bpmn")
				.addClasspathResource("rules/activiti-rule-example01.drl").deploy();
		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();

		ProcessInstance pi = runService.startProcessInstanceById(pd.getId());
		logger.info(pi.getId());
		
		Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		
		User user=new User();
		user.setIdentity("gold");
		user.setAmount(100);
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("user1", user);
		
		taskService.complete(task.getId(), map);

	}

}
