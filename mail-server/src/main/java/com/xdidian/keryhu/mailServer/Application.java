package com.xdidian.keryhu.mailServer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * Description : 邮件服务器主service
 * Date : 2016年06月18日 上午10:36
 * Author : keryHu keryhu@hotmail.com
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}


