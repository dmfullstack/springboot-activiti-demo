package com.zzj.it;

import org.activiti.engine.ProcessEngineConfiguration;

public class ProcessEngineConfigurationConfig {

	public ProcessEngineConfiguration processEngineConfiguration() {
		ProcessEngineConfiguration configuration=ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		configuration.setJdbcDriver("");
		configuration.setJdbcUrl("");
		configuration.setJdbcPassword("");
		configuration.setJdbcUsername("root");
		configuration.setDatabaseSchemaUpdate("true");
		
		return configuration;
		
	}
}
