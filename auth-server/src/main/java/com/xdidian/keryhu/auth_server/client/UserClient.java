package com.xdidian.keryhu.auth_server.client;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xdidian.keryhu.auth_server.domain.AuthUserDto;

/**
 * 
 * @Description : 针对于user-account service remote rest 服务
 * @date : 2016年6月18日 下午7:52:10
 * @author : keryHu keryhu@hotmail.com
 */

@FeignClient(name = "user-account", fallback = UserClientFallback.class)
public interface UserClient {

   
  /**
   * 根据唯一标志，email、phone，或user中的id，3个里任何一种，来查看数据库的user
   * @param identity
   * @return
   */
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/findByIdentity")
  public AuthUserDto findByIdentity(@RequestParam("identity") String identity);

 
}


