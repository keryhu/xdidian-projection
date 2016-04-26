package com.xdidian.keryhu.authserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * 
* @ClassName: ResourceServerConfig
* @Description: TODO(spring OAuth2 resource 验证方法)
* 注意不要使用 @RequiredArgsConstructor(onConstructor = @__(@Autowired))
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 上午10:44:53
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private   RoleHierarchyImpl roleHierarchy;
	
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
		                //对于auth－server里面的url控制，有2种方法，一个是在这个方法里面添加控制，注意不要加到WebSecurityConfig class 里，那个里面没有用。
		                .antMatchers("/webjars/**","/favicon.ico").permitAll()
		                .anyRequest().authenticated()
		                ;
		               
		    }
		 
		 @Override
		    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		        
		        resources.resourceId("ReadAndWriteResource");
		    }
		 
		 
}
