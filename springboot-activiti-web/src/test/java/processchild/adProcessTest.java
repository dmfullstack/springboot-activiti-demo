package processchild;

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

import com.zzj.it.Application;

/**
 * 特别子流程 不指定流程走向，通过API控制
 * 
 * @author zhouzj
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class adProcessTest {
	private static final Logger logger = LoggerFactory.getLogger(adProcessTest.class);

	@Test
	public void test() throws InterruptedException {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();
		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("processChild/ad/ad.bpmn")
				.deploy();
		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();

		ProcessInstance pi = runService.startProcessInstanceById(pd.getId());
		logger.info(pi.getId());
		Execution e=runService.createExecutionQuery().processInstanceId(pi.getId()).activityId("subprocess1").singleResult();
		logger.error("执行流：{}",e.getId());
		//让流程到达任务1
		runService.executeActivityInAdhocSubProcess(e.getId(), "usertask1");
	 
		//查找任务
		Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		logger.error("当前任务 taskId:{} taskName：{}",task.getId(),task.getName());
		
		taskService.complete(task.getId());
		runService.completeAdhocSubProcess(e.getId());
		
		task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		logger.error("当前任务 taskId:{} taskName：{}",task.getId(),task.getName());
	}

}
