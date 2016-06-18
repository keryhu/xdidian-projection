package com.xdidian.keryhu.useraccount.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Description : 用户注册保存数据库加密的bean
 * Date : 2016年06月18日 上午11:23
 * Author : keryHu keryhu@hotmail.com
 */
@Configuration
public class PasswordBcryBean {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }


}
