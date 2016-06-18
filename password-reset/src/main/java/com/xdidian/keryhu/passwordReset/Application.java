package com.xdidian.keryhu.passwordReset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;



/**
 * @Description : 用户忘记密码所需要的service
 * @date : 2016年6月18日 下午9:08:16
 * @author : keryHu keryhu@hotmail.com
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Application {

  public static void main(String[] args) {

    SpringApplication.run(Application.class, args);
  }


}
