package com.zzj.it.config;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class ActivitiDataSourceConfig {
	private final Logger logger = LoggerFactory.getLogger(ActivitiDataSourceConfig.class);
	@Autowired
	private DruidDataSource dataSource;

	@Bean("processEngineConfiguration")
	public ProcessEngineConfiguration engineConfiguration() {
		logger.error("开始配置activiti 数据服务");
		StandaloneInMemProcessEngineConfiguration configuration = new StandaloneInMemProcessEngineConfiguration();
		configuration.setDataSource(dataSource);
		/**
		 * 设置数据库策略 false 不做操作 true 如果没有表则去更新或者新建 create-drop 在启动的时候创建 关闭时删除 drop-create
		 * 启动先删除再创建
		 */
		configuration.setDatabaseSchemaUpdate("true");
		/**
		 * 历史配置 none 不保存任何数据 activity
		 * 
		 */
		configuration.setHistory("activity");
		configuration.setAsyncExecutorActivate(true);
		return configuration;
	}

}
