package com.zzj.it.config;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class ActivitiDataSourceConfig {

	@Autowired
	private DruidDataSource dataSource;
	
	@Bean("processEngineConfiguration")
	public StandaloneInMemProcessEngineConfiguration engineConfiguration() {
		StandaloneInMemProcessEngineConfiguration configuration=new StandaloneInMemProcessEngineConfiguration();
		configuration.setDataSource(dataSource);
		return configuration;
	}
}
