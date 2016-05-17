package com.xdidian.keryhu.authserver.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




/**
* @ClassName: WebSecurityConfig
* @Description: TODO(auth-server security主方法。注意不要使用 @RequiredArgsConstructor(onConstructor = @__(@Autowired)))
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 上午10:38:47
 */
@Configuration
@Order(-20)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	 @Autowired
	 private  UserDetailsService userDetailsService; 
	 
	 @Autowired
	 private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	 @Override
	 @Bean
	    public AuthenticationManager    authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	 
	 /**
	 * <p>Title: configure</p>
	 * <p>Description: 注意对于auth-server 里的url 路由security 控制，都加到 ResourceServerConfig class 里，不要加到此class 方法里。 </p>
	 * @param http   所有的关于login 路由或者 login 带参数 如 login*  的路由 权限配置都是在 此方法中
	 * @throws Exception
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	  */
	 @Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http
				.formLogin().loginPage("/login")
				.failureUrl("/login?error")
				.failureHandler(customAuthenticationFailureHandler)
				.permitAll()
			.and()
				.requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
			.and()
				.authorizeRequests()
				.antMatchers("/login*").permitAll()
				.anyRequest().authenticated()
				;
			
		 	// @formatter:on
		}
	 
	 @Bean
		public PasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder(11);
		}
	 
	 
	 @Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth
			    .userDetailsService(userDetailsService)
			    .passwordEncoder(passwordEncoder());
		}

}
