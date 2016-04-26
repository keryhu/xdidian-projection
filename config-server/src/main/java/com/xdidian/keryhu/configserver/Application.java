package com.xdidian.keryhu.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;



/**
* @ClassName: Application
* @Description: TODO(spring cloud config server class)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月25日 下午3:33:19
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
