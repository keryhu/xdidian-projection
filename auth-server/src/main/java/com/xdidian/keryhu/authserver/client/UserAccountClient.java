package com.xdidian.keryhu.authserver.client;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
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
@FeignClient(name="user-account",fallback = UserAccountClientFallback.class)
public interface UserAccountClient {
	
	/**
	 * 自定义spring feign ，用于获取auth server 验证登陆用户，get 后台mongo 数据库，从user-account api 接口获取。
	 * @author hushuming
	 * @param loginName 就是用户登陆的帐号类型，可以是email，或者phone
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/findByLoginName",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AuthUserDto ByLoginName(@RequestParam("loginName") String loginName);
	

	/**
	 * 
	* @Title: isEmailExist
	* @Description: TODO(用于前台用户登录时，查询登陆的email是否已经注册过，这个是后台调用接口)
	* 注意spring feign的返回对象，要和被spring  feign的的rest controller 的返回结果一致
	* @param @param email  需要被查询的email
	* @param @return    设定文件   如果已经存在了此email，则返回true，否则是false
	* @return ResponseEntity<?>    返回类型 json的封装对象
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/isEmailExist",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Boolean> isEmailExist(@RequestParam("email") String email);
	
	/**
	 * 
	* @Title: isPhoneExist
	* @Description: TODO(用于前台用户登录时，查询登陆的phone是否已经注册过，这个时调用的接口)
	* @param @param phone  需要被查询的phone
	* @param @return    设定文件  如果已经存在此phone 则返回ture，否则false
	* @return ResponseEntity<?>    返回类型 json 的封装对象
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/isPhoneExist",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Boolean> isPhoneExist(@RequestParam("phone") String phone);
}



