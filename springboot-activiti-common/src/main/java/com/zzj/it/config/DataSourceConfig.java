package com.zzj.it.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourceConfig {

	private final Logger logger=LoggerFactory.getLogger(DataSourceConfig.class);
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Value("${spring.datasource.driver}")
	private String driver;

	@Bean(name = "dataSource", destroyMethod = "close")
	public DruidDataSource druidDataSource() throws Exception {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(this.url);
		dataSource.setDriverClassName(this.driver);
		dataSource.setUsername(this.username);
		dataSource.setPassword(this.password);
		logger.error("启动数据服务");
		dataSource.init();
		return dataSource;
	}

}
