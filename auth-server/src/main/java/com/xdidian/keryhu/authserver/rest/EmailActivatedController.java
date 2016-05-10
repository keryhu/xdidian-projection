/**  
* @Title: EmailActivatedController.java
* @Package com.xdidian.keryhu.authserver.rest
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月6日 下午9:02:03
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xdidian.keryhu.authserver.client.EmailActivatedClient;
import com.xdidian.keryhu.authserver.exception.EmailActivatedSentTimesOverException;
import com.xdidian.keryhu.authserver.stream.RemoveUserProducer;
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
	
	private final RetryEmailActivatedProducer retryEmailActivatedProducer;
	
    private final RemoveUserProducer removeUserProducer;
	
	/**
	 * 
	* @Title: emailActivatedExpired
	* @Description: TODO(提供rest 接口给 auth-server web前台查询当前email所在的数据库，在未激活的情况下，
	* 激活时间是否过期)
	* @param @param loginName
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/query/emailActivatedExpired")
	public ResponseEntity<?> emailActivatedExpired(@RequestParam(value="", required=true) String loginName){
		
		return ResponseEntity.ok(emailActivatedClient.emailActivatedExpired(loginName));
	}
	
	
	/**
	 * 
	* @Title: emailActivatedStatus
	* @Description: TODO(查询当前loginName所在数据库，email有没有激活)
	* @param @param loginName
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/query/emailActivatedStatus")
	public ResponseEntity<?> emailActivatedStatus(@RequestParam(value="", required=true) String loginName){
		
		return ResponseEntity.ok(emailActivatedClient.emailActivatedStatus(loginName));
	}
	
	
	/**
	 * 
	* @Title: emailActivateSentTimesNotOver
	* @Description: TODO(提供rest接口，供login web 页面 ajax查询 email激活的次数有没有超过限制，
	* 如果没有超过限制，则web显示（再次发送邮件激活），如果超过限制，则显示错误提示“ 您发送邮件激活频率太高，
	* 请稍后再试，或者更换email重新注册)
	* @param @param loginName
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/query/emailActivateSentTimesNotOver")
	public ResponseEntity<?> emailActivateSentTimesNotOver(@RequestParam(value="", required=true) String loginName){
		
		return ResponseEntity.ok(emailActivatedClient.emailActivateSentTimesNotOver(loginName));
	}
	
	
	/**
	 * 
	* @Title: retryEmailActivated
	* @Description: TODO(前台web，不管输入的帐号是email还是phone，只要查询下来，该数据库用户未激活，
	* 且激活次数未超过规定次数的情况下，而且用户点击 再次 发送 帐号激活的按钮时，促发此 post
	* 前台页面提交再次发送邮件激活的申请)
	* @param @param loginName    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.POST,value="/retryEmailActivated")
	public void retryEmailActivated(@RequestBody String loginName){
		Assert.hasLength(loginName);
		//在发送次数未超过时，处理再次发送事情，否则不做任何处理
		if(emailActivatedClient.emailActivateSentTimesNotOver(loginName)
				.get("emailActivateSentTimesNotOver")){
			//再次发送email激活，使用spring cloud stream 发送含有email的消息出去，
			retryEmailActivatedProducer.send(loginName);
		}else{
			throw new EmailActivatedSentTimesOverException("您的email激活次数过于频繁，请稍后再试！或者重新注册！");
		}
				
	}
	
	/**
	 * 
	* @Title: retryRegister
	* @Description: TODO(用户在login页面点击重新注册，后台接受到这个点击的post请求，所做的事情，
	* 后台负责将此user 的loginName以message的形式发送给user-account，删除此user
	* 前台负责将页面跳转到该用户的注册页面。（如果可能的话，后台实现页面跳转，目前还未证实实现的方法）)
	* @param @param loginName    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.POST,value="/retryRegister")
	public void retryRegister(@RequestBody String loginName){
		Assert.hasLength(loginName);
		//在发送次数未超过时，处理再次发送事情，否则不做任何处理
				if(emailActivatedClient.emailActivateSentTimesNotOver(loginName)
						.get("emailActivateSentTimesNotOver")){
					//再次发送email激活，使用spring cloud stream 发送含有email的消息出去，让user-account删除此user，
					removeUserProducer.send(loginName);
					//看看能否实现页面跳转到  register 页面
				}
	}
	

}
