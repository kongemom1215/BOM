package com.spring.bom.configuration.coffee;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring.bom.service.coffee.CoffeeInterceptor;


@Configuration
public class CoffeeWebMvcConfiguration implements WebMvcConfigurer {
	// Interceptor 추가 설정 
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CoffeeInterceptor()).addPathPatterns("/coffee/interceptor/**")
													//	.addPathPatterns("/coffee/censorMemberManagerPage")
		;
	}
}
