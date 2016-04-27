package com.xdidian.keryhu.pcgateway.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class LoginConfig extends WebSecurityConfigurerAdapter  {
	

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http 
		     .logout()
		     .and().antMatcher("/**").authorizeRequests()
			 .antMatchers("/","/register","/webjars/**","/favicon.ico","/script/**").permitAll()
				//只有未登陆用户，才能提交注册。
			 .antMatchers(HttpMethod.POST,"/property-register/property/register").permitAll()
			 .antMatchers(HttpMethod.GET, "/user-account/users/query/**").permitAll()
			 .anyRequest().authenticated()
			 .and().csrf().disable()
				;
	}

}
