package com.xdidian.keryhu.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Description : spring cloud config server class
 * Date : 2016年06月18日 上午8:21
 * Author : keryHu keryhu@hotmail.com
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
