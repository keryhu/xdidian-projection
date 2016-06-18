package com.xdidian.keryhu.pcgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 * Description : pc gateway 主程序
 * Date : 2016年06月18日 上午10:55
 * Author : keryHu keryhu@hotmail.com
 */
@SpringBootApplication
@EnableZuulProxy
@EnableFeignClients
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}


