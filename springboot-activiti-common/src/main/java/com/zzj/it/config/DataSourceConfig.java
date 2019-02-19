package com.zzj.it.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourceConfig {

	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Value("${spring.datasource.driver}")
	private String driver;

	@Primary
	@Bean(name = "dataSource", destroyMethod = "close")
	public DruidDataSource druidDataSource() throws Exception {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(this.url);
		dataSource.setDriverClassName(this.driver);
		dataSource.setUsername(this.username);
		dataSource.setPassword(this.password);
		dataSource.init();
		return dataSource;
	}

}
