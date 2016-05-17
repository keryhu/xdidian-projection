/**  
* @Title: Application.java
* @Package com.xdidian.keryhu.passwordReset
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月16日 下午3:50:39
* @version V1.0  
*/ 
package com.xdidian.keryhu.passwordReset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


/**
* @ClassName: Application
* @Description: TODO(用户忘记密码所需要的service)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月16日 下午3:50:39
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Application {
	
    public static void main(String[] args) {	
		
		SpringApplication.run(Application.class, args);
	}
	

}
