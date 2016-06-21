/**
 * @Title: Application.java
 * @Package com.xdidian.keryhu.emailActivate
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年5月11日 上午10:54:19
 * @version V1.0
 */
package com.xdidian.keryhu.email_activate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 
 * @Description : 这个service主要功能： 提供用户注册完后，email激活账户
 * @date : 2016年6月18日 下午8:08:55
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
