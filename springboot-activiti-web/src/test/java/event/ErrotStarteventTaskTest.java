package event;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzj.it.Application;

/**
 * 错误开始事件
 * 只能放到
 * @author zhouzj
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ErrotStarteventTaskTest {
	private static final Logger logger = LoggerFactory.getLogger(ErrotStarteventTaskTest.class);

	@Test
	public void test() throws InterruptedException {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();

		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("processes/error_startevent_task.bpmn").deploy();
		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
		
		
		ProcessInstance pi = runService.startProcessInstanceById(pd.getId());
		logger.info(pi.getId());
 
	}

}
