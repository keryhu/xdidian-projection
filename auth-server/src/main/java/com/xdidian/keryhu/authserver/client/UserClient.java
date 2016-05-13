package com.xdidian.keryhu.authserver.client;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xdidian.keryhu.authserver.domain.AuthUserDto;

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
	 * 自定义spring feign ，用于获取auth server 验证登陆用户，get 后台mongo 数据库，从user-account api 接口获取。
	 * @author hushuming
	 * @param loginName 就是用户登陆的帐号类型，可以是email，或者phone
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/findByLoginName")
	public AuthUserDto ByLoginName(@RequestParam("loginName") String loginName);
	

	/**
	 * 
	* @Title: isEmailExist
	* @Description: TODO(用于前台用户登录时，查询登陆的email是否已经注册过，这个是后台调用接口)
	* 注意spring feign的返回对象，要和被spring  feign的的rest controller 的返回结果一致
	* @param @param email  需要被查询的email
	* @param @return    设定文件   如果已经存在了此email，则返回true，否则是false
	* @return Boolean    返回类型 
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/isEmailExist")
	public Boolean isEmailExist(@RequestParam("email") String email);
	
	/**
	 * 
	* @Title: isPhoneExist
	* @Description: TODO(用于前台用户登录时，查询登陆的phone是否已经注册过，这个时调用的接口)
	* @param @param phone  需要被查询的phone
	* @param @return    设定文件  如果已经存在此phone 则返回ture，否则false
	* @return ResponseEntity<?>    返回类型 json 的封装对象
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/isPhoneExist")
    public Boolean isPhoneExist(@RequestParam("phone") String phone);
	
	
	
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
	* @Title: loginNameToEmail
	* @Description: TODO(将loginName转为数据库中的email)
	* @param @param loginName
	* @param @return    设定文件
	* @return String  返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/loginNameToEmail")
	public String loginNameToEmail(@RequestParam("loginName") String loginName);
	
}



