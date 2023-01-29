package com.subh.springdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.google.common.collect.ImmutableList;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().disable();
		http.authorizeRequests().antMatchers("/**/*").authenticated().anyRequest().permitAll().and().httpBasic().and()
				.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}
	
	@Bean
	@Primary
	public CorsConfigurationSource corsConfigurationSource() throws Exception{
		final CorsConfiguration configuration=new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.setAllowedMethods(ImmutableList.of("HEAD","GET","POST","OPTIONS","DELETE","PUT"));
		configuration.setAllowedHeaders(ImmutableList.of("Authorization","Cache-Control","Content-Type"));
		configuration.addAllowedOrigin("*");
		final UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}