/**  
* @Title: EmailActivatedController.java
* @Package com.xdidian.keryhu.authserver.rest
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月6日 下午9:02:03
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xdidian.keryhu.authserver.client.EmailActivatedClient;
import com.xdidian.keryhu.authserver.stream.RetryEmailActivatedProducer;

import lombok.RequiredArgsConstructor;

/**
* @ClassName: EmailActivatedController
* @Description: TODO(email 激活所需要的 rest 接口)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月6日 下午9:02:03
*/
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailActivatedController  {
	
	private final EmailActivatedClient emailActivatedClient;
	
	private final RetryEmailActivatedProducer producer;
	
	/**
	 * 
	* @Title: emailActivatedExpired
	* @Description: TODO(提供rest 接口给 auth-server web前台查询当前email所在的数据库，在未激活的情况下，
	* 激活时间是否过期)
	* @param @param email
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/query/emailActivatedExpired")
	public ResponseEntity<?> emailActivatedExpired(@RequestParam(value="", required=true) String email){
		
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("emailActivatedExpired", emailActivatedClient.emailActivatedExpired(email));
		return ResponseEntity.ok(result);
	}
	
	
	/**
	 * 
	* @Title: emailActivatedStatus
	* @Description: TODO(查询当前email所在区域，email有没有激活)
	* @param @param email
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/query/emailActivatedStatus")
	public ResponseEntity<?> emailActivatedStatus(@RequestParam(value="", required=true) String email){
		
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("emailActivatedStatus", emailActivatedClient.emailActivatedStatus(email));
		return ResponseEntity.ok(result);
	}
	
	
	/**
	 * 
	* @Title: emailActivateSentTimesNotOver
	* @Description: TODO(提供rest接口，供login web 页面 ajax查询 email激活的次数有没有超过限制，
	* 如果没有超过限制，则web显示（再次发送邮件激活），如果超过限制，则显示错误提示“ 您发送邮件激活频率太高，
	* 请稍后再试，或者更换email重新注册)
	* @param @param email
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/query/emailActivateSentTimesNotOver")
	public ResponseEntity<?> emailActivateSentTimesNotOver(@RequestParam(value="", required=true) String email){
		
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("emailActivateSentTimesNotOver", emailActivatedClient.emailActivateSentTimesNotOver(email));
		return ResponseEntity.ok(result);
	}
	
	
	/**
	 * 
	* @Title: retryEmailActivated
	* @Description: TODO(前台web，如果遇到输入的帐号是email，且未激活，激活次数未超过规定次数的情况下，
	* 前台页面提交再次发送邮件激活的申请)
	* @param @param email    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.POST,value="/retryEmailActivated")
	public void retryEmailActivated(@RequestBody String email){
		
		//在发送次数未超过时，处理再次发送事情，否则不做任何处理
		if(emailActivatedClient.emailActivateSentTimesNotOver(email)){
			//再次发送email激活，使用spring cloud stream 发送含有email的消息出去，
			producer.send(email);
		}
				
	}

}
