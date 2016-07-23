package com.xdidian.keryhu.auth_server.client;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description : TODO(auth server 远程调用 account-activate 的 spring feign 方法)
 * @date : 2016年7月23日 下午1:18:47
 * @author : keryHu keryhu@hotmail.com
 */
@FeignClient(name = "account-activate", fallback = AccountActivateFallback.class)
public interface AccountActivateClient {
  
  @RequestMapping(value = "/query/emailActivate", method = RequestMethod.GET)
  public String doWithEmailActivate(@RequestParam("email") String email);

}
