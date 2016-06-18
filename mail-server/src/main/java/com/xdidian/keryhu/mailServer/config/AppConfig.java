/**
 * @Title: AppConfig.java
 * @Package com.xdidian.keryhu.mailServer.config
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月9日 上午7:15:13
 * @version V1.0
 */
package com.xdidian.keryhu.mailServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

/**
 * Description :app配置的启动Bean
 * Date : 2016年06月18日 上午10:21
 * Author : keryHu keryhu@hotmail.com
 */
@Configuration
public class AppConfig {

    /**
     * 配置java 8 time thymeleaf 显示
     */
    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

}
