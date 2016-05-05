package com.xdidian.keryhu.mailActivate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
* @ClassName: MethodSecurityConfiguration
* @Description: TODO(spring method security 方法。)
* 注意不要使用 @RequiredArgsConstructor(onConstructor = @__(@Autowired))
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 上午10:42:53
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true,jsr250Enabled=true, proxyTargetClass = true)
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration  {
	
	 @Autowired
	 private  RoleHierarchyImpl roleHierarchy;
	
	 @Override
	  protected MethodSecurityExpressionHandler createExpressionHandler() {
		 final DefaultMethodSecurityExpressionHandler handler=new DefaultMethodSecurityExpressionHandler();
			handler.setRoleHierarchy(roleHierarchy);
			return handler;
	  }

}
