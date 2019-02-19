package com.zzj.it;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class ActivitiTest {

	@Test
	public void test() {
		 
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		
		engine.close();
	}

}
