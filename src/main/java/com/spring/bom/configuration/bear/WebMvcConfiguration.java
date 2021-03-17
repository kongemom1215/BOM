package com.spring.bom.configuration.bear;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring.bom.service.bear.SampleInterceptor;



@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	// Interceptor 추가 설정 
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SampleInterceptor()).addPathPatterns("/interCeptor");
	}
}
