/**  
* @Title: EmailActivatedClient.java
* @Package com.xdidian.keryhu.authserver.client
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月6日 下午7:45:45
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.client;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @ClassName: EmailActivatedClient
* @Description: TODO(spring feign 验证email 有没有激活的一些rest 接口)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月6日 下午7:45:45
*/
@FeignClient(name="user-account",fallback = EmailActivatedClientFallback.class)
public interface  EmailActivatedClient {
	
	
	/**
	 * 
	* @Title: emailActivatedStatus
	* @Description: TODO(根据登录loginName（email或phone）rest查询emailActivatedStatus的值)
	* @param @param loginName
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/emailActivatedStatus")
	public Map<String,Boolean> emailActivatedStatus(@RequestParam("loginName") String loginName);
	
	
	/**
	 * 
	* @Title: emailActivatedExpired
	* @Description: TODO(根据rest，查询eamil所在的数据库，在email未激活的情况下，查看当前时间是否超过注册时间
	* ＋最大允许的过期时间)
	* @param @param loginName
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/emailActivatedExpired")
	public Map<String,Boolean> emailActivatedExpired(@RequestParam("loginName") String loginName);
	    
	
	/**
	 * 
	* @Title: emailActivateSentTimesNotOver
	* @Description: TODO(根据当前loginName值，查询此loginName数据库中，email激活发送的次数有没有超过限制)
	* @param @param loginName
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/emailActivateSentTimesNotOver")
	public Map<String,Boolean> emailActivateSentTimesNotOver(@RequestParam("loginName") String loginName);
	

}
