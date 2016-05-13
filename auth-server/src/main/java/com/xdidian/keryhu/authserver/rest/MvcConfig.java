package com.xdidian.keryhu.authserver.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
* @ClassName: MvcConfig
* @Description: TODO(spring thymeleaf 定义的 spring oauth2 登陆页面 和用户确认授权页面)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月25日 下午9:25:47
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/oauth/confirm_access").setViewName("authorize");
		
	}

}
