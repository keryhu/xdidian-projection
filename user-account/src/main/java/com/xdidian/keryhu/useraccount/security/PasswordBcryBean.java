package com.xdidian.keryhu.useraccount.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
* @ClassName: PasswordBcryBean
* @Description: TODO(用户注册保存数据库加密的bean)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月24日 下午7:33:55
 */
@Configuration
public class PasswordBcryBean {
	
	 @Bean
		public PasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder(11);
		}
	 
	

}
