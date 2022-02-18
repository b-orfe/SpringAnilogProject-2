package com.zdrv.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zdrv.filter.AuthFilter;



@Configuration
public class FilterConfig {
	@Bean public FilterRegistrationBean<AuthFilter> authFilter() {
		var bean = new FilterRegistrationBean<>(new AuthFilter());
		bean.addUrlPatterns("/list/*"); 
		// 対応するURL 
		bean.setOrder(1); 
		// フィルターの適用順 
		return bean;
	}
}
