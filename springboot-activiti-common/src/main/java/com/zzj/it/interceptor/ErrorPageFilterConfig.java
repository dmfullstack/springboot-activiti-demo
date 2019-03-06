package com.zzj.it.interceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorPageFilterConfig {

	/*@Bean
	public ErrorPageFilter errorPageFilter() {
		
		return new ErrorPageFilter();
	}
	
	@Bean
	public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter errorPageFilter) {
		FilterRegistrationBean registrationBean=new FilterRegistrationBean();
		registrationBean.setFilter(errorPageFilter);
		registrationBean.setEnabled(false);
		return registrationBean;
		
	}*/
}
