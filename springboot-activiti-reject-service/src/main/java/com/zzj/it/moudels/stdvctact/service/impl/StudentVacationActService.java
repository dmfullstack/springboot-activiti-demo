package com.zzj.it.moudels.stdvctact.service.impl;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.it.moudels.stdvctact.service.IStudentVacationActService;

@Service
public class StudentVacationActService implements IStudentVacationActService {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private RepositoryService repositoryService;

	@Override
	public String start() {
		ProcessInstance pi= runtimeService.startProcessInstanceByKey("studentsVacationAct");
		
		return null;
	}
	
	
}
