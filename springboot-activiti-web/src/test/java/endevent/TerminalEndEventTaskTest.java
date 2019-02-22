package endevent;

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

import com.zzj.it.Application;

/**
 * 错误结束事件，和错误开始事件连用
 * 
 * @author admin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TerminalEndEventTaskTest {
	private static final Logger logger = LoggerFactory.getLogger(TerminalEndEventTaskTest.class);

	@Test
	public void test() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();
		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("processes/terminal_endevent_task.bpmn").deploy();
		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();

		ProcessInstance pi = runService.startProcessInstanceById(pd.getId());
		logger.info(pi.getId());

		List<Execution> executions = runService.createExecutionQuery().processInstanceId(pi.getId()).list();

		logger.error("终止前执行流数量：{}", executions.size());
		;

		List<Task> tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();

		for (Task task : tasks) {
			if ("usertask1".equals(task.getName())) {
				taskService.complete(task.getId());
			}
		}
		logger.error("终止后执行流数量：{}", runService.createExecutionQuery().processDefinitionId(pi.getId()).count());
		logger.error("终止后查看任务是否还存在：{},{}",rs.createProcessDefinitionQuery().processDefinitionId(pi.getId()).singleResult(),"可以看到值已经查不到");
	}

}
