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
* @ClassName: AppConfig
* @Description: TODO(app配置的启动Bean)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月9日 上午7:15:13
*/
@Configuration
public class AppConfig {
	
	/**
	 * 
	* @Title: java8TimeDialect
	* @Description: TODO(配置java 8 time thymeleaf 显示)
	* @param @return    设定文件
	* @return Java8TimeDialect    返回类型
	* @throws
	 */
	@Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

}
