package gatway;

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

/**
 * 事件网关
 * 事件网关会根据最先执行的流程一条线执行到底
 * @author zhouzj
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class EventGatwayTaskTest {
	private static final Logger logger = LoggerFactory.getLogger(EventGatwayTaskTest.class);

	@Test
	public void test() throws InterruptedException {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();

		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("gatway/event_gatway_task.bpmn").deploy();
		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
		logger.info("部署id" + dep.getId());
		ProcessInstance pi = runService.startProcessInstanceById(pd.getId());
		//第一种测试
		/*
		runService.signalEventReceived("taskBsignal");
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		logger.error("当前执行任务:{}",task.getName());
		Thread.sleep(7*1000);
		
		task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		logger.error("当前执行任务:{}",task.getName());*/
		//第二种测试
		Thread.sleep(7*1000);
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		logger.error("当前执行任务:{}",task.getName());
		
		runService.signalEventReceived("taskBsignal");
		task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		logger.error("当前执行任务:{}",task.getName());
	}

}
