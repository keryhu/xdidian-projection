package com.xdidian.keryhu.mailServer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
/**
* @ClassName: Application
* @Description: TODO(邮件服务器主service)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 上午9:19:51
 */
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}


