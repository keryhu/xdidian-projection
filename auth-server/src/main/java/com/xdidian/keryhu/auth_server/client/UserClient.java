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
   * 自定义spring feign ，用于获取auth server 验证登陆用户，get 后台mongo 数据库，从user-account api 接口获取。
   */
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/findByLoginName")
  public AuthUserDto ByLoginName(@RequestParam("loginName") String loginName);


  /**
   * 用于前台用户登录时，查询登陆的email是否已经注册过，这个是后台调用接口 注意spring feign的返回对象，要和被spring feign的的rest controller
   * 的返回结果一致
   */
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/isEmailExist")
  public Boolean isEmailExist(@RequestParam("email") String email);

  /**
   * 用于前台用户登录时，查询登陆的phone是否已经注册过，这个时调用的接口
   */
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/isPhoneExist")
  public Boolean isPhoneExist(@RequestParam("phone") String phone);


  /**
   * 当局登录名loginName，前台web，ajax查询当前loginName所在的数据库，email是否激活
   */
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/emailStatus")
  public Boolean emailStatus(@RequestParam("loginName") String loginName);

  /**
   * 将loginName转为数据库中的email
   */
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/loginNameToEmail")
  public String loginNameToEmail(@RequestParam("loginName") String loginName);

}


