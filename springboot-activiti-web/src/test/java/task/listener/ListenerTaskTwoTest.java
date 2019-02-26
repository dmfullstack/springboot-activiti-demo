package task.listener;

import org.activiti.engine.IdentityService;
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
 * 监听器
 * 有三种方式 
 * <ul>
 * <li>java class </li>
 * <li>exepression: ${myBean.taskMethod(task)}    传的参数 DelegateTask</li>
 * <li>delegateExepression:${myBean} 实现TaskListener</li>
 * </ul>
 * @author zhouzj
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ListenerTaskTwoTest {
	private static final Logger logger = LoggerFactory.getLogger(ListenerTaskTwoTest.class);

	@Test
	public void test() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService=engine.getTaskService();
		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("processListener/listener_task2.bpmn").deploy();

		logger.info("部署id" + dep.getId());

		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
		ProcessInstance pi = runService.startProcessInstanceById(pd.getId());
		
		Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		taskService.complete(task.getId());

	}

}
