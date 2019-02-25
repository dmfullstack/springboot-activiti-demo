package task.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.zzj.it.modules.servicetask.MyDelegate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ServiceTaskTest {
	private static final Logger logger = LoggerFactory.getLogger(ServiceTaskTest.class);

	@Test
	public void test() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		logger.info("启动");
		RepositoryService rs = engine.getRepositoryService();
		RuntimeService runService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();
		IdentityService is = engine.getIdentityService();
		Deployment dep = rs.createDeployment().addClasspathResource("processTask/service/service_task.bpmn").deploy();

		logger.info("部署id" + dep.getId());

		Map<String,Object> map=new HashMap<String, Object>();
		
		map.put("myDelegate", new MyDelegate());
		
		ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
		
		ProcessInstance pi = runService.startProcessInstanceById(pd.getId(),map);
		
		 
	}

}
