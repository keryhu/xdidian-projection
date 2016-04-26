package com.xdidian.keryhu.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;




/**
* @ClassName: Application
* @Description: TODO(spring cloud eureka server)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月25日 下午3:06:42
 */
@SpringBootApplication
@EnableEurekaServer
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}