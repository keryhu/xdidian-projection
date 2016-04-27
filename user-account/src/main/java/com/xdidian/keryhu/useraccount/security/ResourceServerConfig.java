package com.xdidian.keryhu.useraccount.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
* @ClassName: ResourceServerConfig
* @Description: TODO(spring OAuth2 Resource 方法)
* 注意不要使用 @RequiredArgsConstructor(onConstructor = @__(@Autowired))
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 上午10:46:27
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private  RoleHierarchyImpl roleHierarchy;
	
	/**
	* @Title: webExpressionHandler
	* @Description: TODO(调用spring security role 权限大小排序bean)
	 */
	private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
		DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler=new 
				DefaultWebSecurityExpressionHandler();
		defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy);
		return defaultWebSecurityExpressionHandler;
	}
	
	


		 @Override
		    public void configure(HttpSecurity http) throws Exception {
		        http
		                .csrf().disable()
		                .httpBasic().disable()
		                .authorizeRequests()
		                .and().authorizeRequests().expressionHandler(webExpressionHandler())  //权限排序
		                .antMatchers("/favicon.ico","/users/**").permitAll()
		                .antMatchers(HttpMethod.GET, "/users/findByIdentity/**").permitAll()
		                .antMatchers(HttpMethod.GET,"/users/isEmailExist/**").permitAll()
		                .antMatchers(HttpMethod.GET,"/users/isPhoneExist/**").permitAll()
		                .antMatchers(HttpMethod.GET,"/users/isPhoneExist").permitAll()
		                .antMatchers(HttpMethod.POST,"/users/property/save").permitAll()
		                
		                .anyRequest().authenticated()
		                ;
		               
		    }
		 
		 @Override
		    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		        
		        resources.resourceId("ReadAndWriteResource");
		    }	 
}
