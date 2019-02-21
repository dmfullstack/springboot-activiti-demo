package event;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzj.it.Application;

/**
 * 定时器开始事件
 * @author zhouzj
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TimerStarteventTaskTest {
	private static final Logger logger = LoggerFactory.getLogger(TimerStarteventTaskTest.class);

	@Test
	public void test() throws InterruptedException {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();

		logger.info("部署");
		Deployment dep = rs.createDeployment().addClasspathResource("processes/timer_startevent_task.bpmn").deploy();

		logger.info("部署id" + dep.getId());
		 
		//等待时间
		Thread.sleep(20*1000);
		//查询全部流程实例
		List<ProcessInstance> processInstances=runService.createProcessInstanceQuery().list();
		logger.error("pi size={}",processInstances.size());
		
		Thread.sleep(20*1000);
		
		processInstances=runService.createProcessInstanceQuery().list();
		
		logger.error("pi size={}",processInstances.size());
	}

}
