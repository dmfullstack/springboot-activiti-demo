package com.zzj.it;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 参数作用域测试
 * 
 * @author zzj <br>
 * @Email 799596822@qq.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ScopeTest {
	private static final Logger logger = LoggerFactory.getLogger(ScopeTest.class);

	@Test
	public void test() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();

		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("processes/scope.bpmn").deploy();

		logger.info("部署id" + dep.getId());
		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
		logger.info("启动流程实例" + pd.getId());
		ProcessInstance pi = runService.startProcessInstanceById(pd.getId());
		logger.info(pi.getId());
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();

		for (Task task : tasks) {
			//在设置值时需要先获取到执行流
			Execution e = runService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
			logger.info("taskName:"+task.getName());
			if ("taskA".equals(task.getName())) {
				//设置参数为本地参数
				runService.setVariableLocal(e.getId(), "taskA", "varA");
			} else {
				//设置参数为全局参数
				runService.setVariable(e.getId(), "taskB", "varB");
			}
		}
		//结束流程
		for (Task task : tasks) {
			taskService.complete(task.getId());
		}
		
		Task taskC =taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		//获取到参数
		logger.info("taskA var ="+runService.getVariable(pi.getId(), "taskA"));
		
		logger.info("taskB var="+runService.getVariable(pi.getId(), "taskB"));

		//最终结果，本地参数无法获取，全局参数可获取
	}

}
