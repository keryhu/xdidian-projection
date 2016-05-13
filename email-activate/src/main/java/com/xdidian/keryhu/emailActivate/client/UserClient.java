package com.xdidian.keryhu.emailActivate.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
* @ClassName: UserAccountClient
* @Description: TODO(针对于user-account service remote rest 服务)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月27日 下午3:28:28
 */
@FeignClient(name="user-account",fallback = UserClientFallback.class)
public interface UserClient {
	
	

	/**
	 * 
	* @Title: isEmailExist
	* @Description: TODO(用于email激活时，查询email是否存在于user数据库
	* @param @param email  需要被查询的email
	* @param @return    设定文件   如果已经存在了此email，则返回true，否则是false
	* @return Boolean    返回类型 json的封装对象
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/isEmailExist")
	public Boolean isEmailExist(@RequestParam("email") String email);
	
	/**
	 * 
	* @Title: emailStatus
	* @Description: TODO(当局登录名loginName，前台web，ajax查询当前loginName所在的数据库，email是否激活)
	* @param @param loginName
	* @param @return    设定文件
	* @return Boolean    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/emailStatus")
	public Boolean emailStatus(@RequestParam("loginName") String loginName);
	
	
	/**
	 * 
	* @Title: findRolesByEmail
	* @Description: TODO(根据当前的email，查询user拥有的权限，返回的是 String 类型的数组。)
	* @param @param email
	* @param @return    设定文件
	* @return List<String>  返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/roles")
	public List<String> findRolesByEmail(@RequestParam("email") String email);
}



