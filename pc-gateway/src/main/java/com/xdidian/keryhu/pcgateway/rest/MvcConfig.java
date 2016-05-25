package com.xdidian.keryhu.pcgateway.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/").setViewName("index.html");
        registry.addViewController("/register/result").setViewName("afterRegister");
        registry.addViewController("/recover/code").setViewName("password-reset");
        
      
    }

}