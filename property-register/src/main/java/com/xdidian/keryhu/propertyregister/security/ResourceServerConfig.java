package com.xdidian.keryhu.propertyregister.security;

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
import static com.xdidian.keryhu.util.Constants.READ_AND_WRITE_RESOURCE_ID;
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
		               
		                .authorizeRequests()
		                .and().authorizeRequests().expressionHandler(webExpressionHandler())  //权限排序
		                .antMatchers("/favicon.ico","/query/**").permitAll()
		                .antMatchers(HttpMethod.POST, "/property/register").permitAll()
		                
		                .anyRequest().authenticated()
		                ;
		               
		    }
		 
		 @Override
		    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		        
		        resources.resourceId(READ_AND_WRITE_RESOURCE_ID);
		    }
		 	 
}
