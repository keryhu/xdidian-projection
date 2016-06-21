package com.xdidian.keryhu.property_signup.client;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Description : spring feign client ,下面主要实现了将用户注册数据dto 远程post 给user－account service
 * @date : 2016年6月18日 下午9:13:48
 * @author : keryHu keryhu@hotmail.com
 */
@FeignClient(name = "user-account", fallback = UserAccountClientFallback.class)
public interface UserAccountClient {


  /**
   * 远程查询email是否存在于数据库
   */
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/isEmailExist")
  public Boolean isEmailExist(@RequestParam("email") String email);

  /**
   * 远程查询phone是否存在于数据库
   */
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/isPhoneExist")
  public Boolean isPhoneExist(@RequestParam("phone") String phone);

  /**
   * 远程查询公司名字是否存在于数据库
   */
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/isCompanyNameExist")
  public Boolean isCompanyNameExist(@RequestParam("companyName") String companyName);


}


