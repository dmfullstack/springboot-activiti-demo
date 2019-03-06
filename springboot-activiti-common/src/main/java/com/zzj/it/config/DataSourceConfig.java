package com.zzj.it.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.zzj.it.exception.MyRunException;

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
	
	@Value("${spring.datasource.maxActive}")
	private Integer maxActive;
	
	@Value("${spring.datasource.minIdle}")
	private Integer minIdle;
	
	@Value("${spring.datasource.initSize}")
	private Integer initSize;
	@Value("${spring.datasource.testSql}")
	private String testSql;

	@Bean(name = "dataSource", destroyMethod = "close")
	public DruidDataSource druidDataSource() throws Exception {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(this.url);
		dataSource.setDriverClassName(this.driver);
		dataSource.setUsername(this.username);
		dataSource.setPassword(this.password);
		dataSource.setMaxWait(60000);
		dataSource.setTimeBetweenEvictionRunsMillis(300000);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		dataSource.setTestWhileIdle(true);
		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxOpenPreparedStatements(100);
		dataSource.setMaxActive(maxActive);
		dataSource.setInitialSize(initSize);
		dataSource.setMinIdle(minIdle);
		logger.error("启动数据服务");
		List<Filter> filters= new ArrayList<Filter>();
		dataSource.setProxyFilters(filters);
		try {
		dataSource.init();
		}catch(SQLException e) {
			logger.error("初始化数据连接池失败:{}", e);
			throw new MyRunException("init druid pool is failed",e);
		}
		return dataSource;
	}
	
	@Bean
	public WallFilter wallFiler() {
		WallFilter filter=new WallFilter();
		filter.setConfig(this.wallConfig());
		return filter;
	}
	@Bean
	public WallConfig wallConfig() {
		WallConfig config= new WallConfig();
		config.setMultiStatementAllow(true);
		config.setNoneBaseStatementAllow(true);
		
		return null;
	}

}
