package com.xdidian.keryhu.pcgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
/**
* @ClassName: Application
* @Description: TODO(pc gateway 主程序)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 上午9:22:06
 */
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}


