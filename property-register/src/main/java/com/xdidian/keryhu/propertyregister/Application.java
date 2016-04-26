package com.xdidian.keryhu.propertyregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


/**
* @ClassName: Application
* @Description: TODO(物业公司注册主程序)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 上午9:23:25
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Application {
	
public static void main(String[] args) {	
		
		
		SpringApplication.run(Application.class, args);
	}
	

}
