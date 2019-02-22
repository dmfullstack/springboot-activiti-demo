package borderevent;

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
 * 定时边界任务 需要开启异步执行器
 * 
 * @author admin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SignalBoderEventTaskTest {
	private static final Logger logger = LoggerFactory.getLogger(SignalBoderEventTaskTest.class);

	@Test
	public void test() throws InterruptedException {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();
		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("processes/sign_borderevent_task.bpmn").deploy();
		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();

		ProcessInstance pi = runService.startProcessInstanceById(pd.getId());
		logger.info(pi.getId());

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		logger.error("当前流程节点:{}", task.getName());
		taskService.complete(task.getId());
		logger.info("完成核查查看任务");
		task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		logger.error("当前流程节点:{}", task.getName());
		runService.signalEventReceived("contact_chanage");
		task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		logger.error("当前流程节点:{}", task.getName());

	}

}
