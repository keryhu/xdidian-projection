package com.xdidian.keryhu.emailActivate.client;

import com.xdidian.keryhu.domain.Role;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Description : 针对于user-account service remote rest 服务
 * Date : 2016年06月18日 上午8:27
 * Author : keryHu keryhu@hotmail.com
 */
@FeignClient(name = "user-account", fallback = UserClientFallback.class)
public interface UserClient {


    /**
     * 用于email激活时，查询email是否存在于user数据库
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/query/isEmailExist")
    public Boolean isEmailExist(@RequestParam("email") String email);

    /**
     * 当局登录名loginName，前台web，ajax查询当前loginName所在的数据库，email是否激活)
     */

    @RequestMapping(method = RequestMethod.GET, value = "/users/query/emailStatus")
    public Boolean emailStatus(@RequestParam("loginName") String loginName);


    /**
     * 根据当前的email，查询user拥有的权限，返回的是 String 类型的数组。)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/query/roles")
    public List<Role> findRolesByLoginName(@RequestParam("loginName") String loginName);
}



