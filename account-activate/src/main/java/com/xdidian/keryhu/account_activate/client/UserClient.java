package com.xdidian.keryhu.account_activate.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 
 * @Description : 针对于user-account service remote rest 服务
 * @date : 2016年6月18日 下午8:09:14
 * @author : keryHu keryhu@hotmail.com
 */
@FeignClient(name = "user-account", fallback = UserClientFallback.class)
public interface UserClient {


  /**
   * 用于email激活时，查询email是否存在于user数据库
   */
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/isEmailExist")
  public Boolean isEmailExist(@RequestParam("email") String email);
  
  /**
   * 用于phone激活时，查询phone是否存在于user数据库
   */
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/isPhoneExist")
  public Boolean isPhoneExist(@RequestParam("phone") String phone);

  /**
   * 当局登录名loginName，前台web，ajax查询当前loginName所在的数据库，email是否激活)
   */

  @RequestMapping(method = RequestMethod.GET, value = "/users/query/emailStatus")
  public Boolean emailStatus(@RequestParam("loginName") String loginName);
  
  /**
   * 当局登录名loginName，前台web，ajax查询当前loginName所在的数据库，phone是否激活)
   */
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/phoneStatus")
  public Boolean phoneStatus(@RequestParam("loginName") String loginName);


}


