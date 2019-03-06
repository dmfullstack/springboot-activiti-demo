package com.zzj.it.common.cofig;

import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.zzj.it.exception.MyRunException;

@Configuration
public class MybatisConfigure {

	@Value("mybatis.mapperLocations")
	private String mapperLocations;

	@Value("mybatis.typeAliasesPackage")
	private String aliasesPackage;
	
	@Autowired
	private DruidDataSource dataSource;
	
	@Bean("sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() {
		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setTypeAliasesPackage(aliasesPackage);
		//添加分页插件
		PageHelper helper=new PageHelper();
		Properties properties=new Properties();
		properties.setProperty("reasonable","true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");
		helper.setProperties(properties);
		bean.setPlugins(new Interceptor[] {helper});
		ResourcePatternResolver patternResolver=new PathMatchingResourcePatternResolver();
		try {
			bean.setMapperLocations(patternResolver.getResources(mapperLocations));
			return bean.getObject();
		} catch (Exception e) {
			throw new MyRunException("sqlSessionFactory init is failed", e);
		}
	}
	
	@Bean("sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory) {
		return new SqlSessionTemplate(factory);
	}
	@Bean("transactionManager")
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}
