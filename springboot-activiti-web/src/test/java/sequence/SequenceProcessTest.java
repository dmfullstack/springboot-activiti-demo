package sequence;

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

/**
 * 条件顺序流
 * 
 * @author zhouzj
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SequenceProcessTest {
	private static final Logger logger = LoggerFactory.getLogger(SequenceProcessTest.class);

	@Test
	public void test() throws InterruptedException {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();
		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("processChild/sequence/sequence_process.bpmn").deploy();
		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("day", 6);
		ProcessInstance pi = runService.startProcessInstanceById(pd.getId(), map);
		logger.info(pi.getId());

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		logger.error("当前任务：{}", task.getName());
		taskService.complete(task.getId());
		task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		logger.error("当前任务：{}", task.getName());
		taskService.complete(task.getId());

		task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		logger.error("当前任务：{}", task.getName());

	}

}
