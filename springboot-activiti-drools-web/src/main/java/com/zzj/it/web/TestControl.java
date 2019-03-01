package com.zzj.it.web;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzj.it.moudels.example.entity.User;

@RestController
public class TestControl {

	private static final Logger logger =LoggerFactory.getLogger(TestControl.class);
	 
	@Autowired
	private RepositoryService rs;
	
	@Autowired
	private RuntimeService runService;
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping("/index")
	public String index() {
		 
		logger.info("部署");
		Deployment dep =rs.createDeployment().addClasspathResource("processes/act-rule-example.bpmn")
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
		return null;
	}
	 
}
