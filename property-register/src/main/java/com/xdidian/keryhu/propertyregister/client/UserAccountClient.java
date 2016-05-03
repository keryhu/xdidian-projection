package com.xdidian.keryhu.propertyregister.client;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xdidian.keryhu.propertyregister.domain.PropertyRegisterDto;


/**
 * 
* @ClassName: CreateUserClient
* @Description: TODO(spring feign client ,下面主要实现了将用户注册数据dto 远程post 给user－account service)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 上午9:24:14
 */
@FeignClient(name="user-account",fallback = UserAccountClientFallback.class)
public interface UserAccountClient {
	
	@RequestMapping(method=RequestMethod.POST,value="/users/property/save",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PropertyRegisterDto> createUser(@RequestBody PropertyRegisterDto dto);
	
	
	/**
	 * 
	* @Title: isEmailExist
	* @Description: TODO(远程查询email是否存在于数据库)
	* @param @param email
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/isEmailExist",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean isEmailExist(@RequestParam("email") String email);
	
	/**
	 * 
	* @Title: isPhoneExist
	* @Description: TODO(远程查询phone是否存在于数据库)
	* @param @param phone
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/isPhoneExist",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean isPhoneExist(@RequestParam("phone") String phone);
	
	/**
	 * 
	* @Title: isCompanyNameExist
	* @Description: TODO(远程查询公司名字是否存在于数据库)
	* @param @param companyName
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/isCompanyNameExist",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean isCompanyNameExist(@RequestParam("companyName") String companyName);
	
		

	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
	public ResponseEntity<?> del(@PathVariable("id") String id);
}



