package com.xdidian.keryhu.propertyregister.client;



import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 
* @ClassName: CreateUserClient
* @Description: TODO(spring feign client ,下面主要实现了将用户注册数据dto 远程post 给user－account service)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 上午9:24:14
 */
@FeignClient(name="user-account",fallback = UserAccountClientFallback.class)
public interface UserAccountClient {
	
	
	
	/**
	 * 
	* @Title: isEmailExist
	* @Description: TODO(远程查询email是否存在于数据库)
	* @param @param email
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/isEmailExist")
	public Boolean isEmailExist(@RequestParam("email") String email);
	
	/**
	 * 
	* @Title: isPhoneExist
	* @Description: TODO(远程查询phone是否存在于数据库)
	* @param @param phone
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/isPhoneExist")
    public Boolean isPhoneExist(@RequestParam("phone") String phone);
	
	/**
	 * 
	* @Title: isCompanyNameExist
	* @Description: TODO(远程查询公司名字是否存在于数据库)
	* @param @param companyName
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/isCompanyNameExist")
	public Boolean isCompanyNameExist(@RequestParam("companyName") String companyName);
	

}



