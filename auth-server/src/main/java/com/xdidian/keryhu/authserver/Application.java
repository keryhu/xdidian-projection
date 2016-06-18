package com.xdidian.keryhu.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.SessionAttributes;


@SpringBootApplication
@SessionAttributes("authorizationRequest")
@EnableDiscoveryClient
@EnableFeignClients

public class Application {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    SpringApplication.run(Application.class, args);
  }

}
