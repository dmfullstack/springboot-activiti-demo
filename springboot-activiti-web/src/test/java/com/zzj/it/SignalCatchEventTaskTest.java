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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
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

		// 获取执行流
		Execution exe = runService.createExecutionQuery().processInstanceId(pi.getId()).onlyChildExecutions()
				.singleResult();

		logger.error("{}当前执行节点{}", pi.getId(), exe.getActivityId());
		//在发送触发信号的时候引用的配置中貌似使用的是name属性中的值，以后再测试
		runService.signalEventReceived("singalTest");
		// 获取执行流
		exe = runService.createExecutionQuery().processInstanceId(pi.getId()).onlyChildExecutions().singleResult();

		logger.error("{}当前执行节点{}", pi.getId(), exe.getActivityId());
	}

}
