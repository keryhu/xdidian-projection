package com.xdidian.keryhu.propertyregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Description : 物业公司注册主程序
 * Date : 2016年06月18日 上午11:16
 * Author : keryHu keryhu@hotmail.com
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PropertyRegisterApp {

    public static void main(String[] args) {


        SpringApplication.run(PropertyRegisterApp.class, args);
    }


}
