package com.subh.springdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.subh.springdemo.dto.RequestMeta;

@EnableWebMvc
@Configuration
public class WebConfigurator implements WebMvcConfigurer {

	@Bean
	@RequestScope
	public RequestMeta getRequestMeta() {
		return new RequestMeta();
	}

	@Bean
	public JwtInterceptor jwtInterceptor() {
		return new JwtInterceptor(getRequestMeta());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor());
	}
}
