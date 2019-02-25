package task.user;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class CandidateTaskTest {
	private static final Logger logger = LoggerFactory.getLogger(CandidateTaskTest.class);

	@Test
	public void test() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();
		IdentityService is = engine.getIdentityService();
		// 创建用户组
		Group group = is.newGroup("boss");

		group.setName("boss");
		is.saveGroup(group);
		Group group1 = is.newGroup("management");

		group.setName("management");

		is.saveGroup(group1);

		User user = is.newUser("argus");
		user.setFirstName("周佐俊");
		is.saveUser(user);
		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("processTask/user/candidate_task.bpmn").deploy();

		logger.info("部署id" + dep.getId());

		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
		ProcessInstance pi = runService.startProcessInstanceById(pd.getId());
		// 查询各个用户（用户组）下可处理任务
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("boss").list();
		logger.error("boss任务数量:{}", tasks.size());
		for (Task task : tasks) {
			logger.info("boss用户组可处理的任务：{}", task.getName());
		}
		tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		logger.error("management任务数量:{}", tasks.size());
		for (Task task : tasks) {
			logger.info("management用户组可处理的任务：{}", task.getName());
		}

		tasks = taskService.createTaskQuery().taskCandidateUser("argus").list();
		logger.error("argus任务数量:{}", tasks.size());
		for (Task task : tasks) {
			logger.info("argus用户可处理的任务：{}", task.getName());
		}
	}

}
