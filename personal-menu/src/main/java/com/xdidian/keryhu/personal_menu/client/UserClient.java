package com.xdidian.keryhu.personal_menu.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "user-account", fallback = UserClientFallback.class)
public interface UserClient {
  
  @RequestMapping(value = "/users/query/isInCompany", method = RequestMethod.GET)
  public boolean isInComopany(@RequestParam("id") String id);

}
