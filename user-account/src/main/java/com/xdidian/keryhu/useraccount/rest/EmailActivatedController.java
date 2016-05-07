/**  
* @Title: EmailActivatedController.java
* @Package com.xdidian.keryhu.useraccount.rest
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月5日 下午8:07:21
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.xdidian.keryhu.useraccount.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: EmailActivatedController
* @Description: TODO(在email激活服务过程中，需要用到的 rest controller )
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月5日 下午8:07:21
*/
@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailActivatedController {
	
	private final UserService userService;
	
	/**
	 * 
	* @Title: emailActivatedCodeExist
	* @Description: TODO(提供rest接口，查询email所在数据，emailActivatedCodeExist是否和参数相等)
	* @param @param email
	* @param @param emailActivatedCode
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型  返回map 的json 
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/emailActivatedCodeExist")
	public ResponseEntity<?> emailActivatedCodeExist(@RequestParam(value="", required=true) String email,
			@RequestParam(value="", required=true)String emailActivatedCode){
		
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("emailActivatedCodeExist", userService.emailActivatedCodeExist(email, emailActivatedCode));
		return ResponseEntity.ok(result);
	}
	
	
	/**
	 * 
	* @Title: emailActivatedExpired
	* @Description: TODO(提供rest接口，email所在的数据库，在未激活的前提下，当前时间是否超过注册时间＋最大的允许过期时间)
	* @param @param email
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/emailActivatedExpired")
	public ResponseEntity<?> emailActivatedExpired(@RequestParam(value="", required=true) String email){
		
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("emailActivatedExpired", userService.emailActivatedExpired(email));
		return ResponseEntity.ok(result);
	}
	
	
	/**
	 * 
	* @Title: findEmailActivatedStatus
	* @Description: TODO(rest 接口查询当前email所在的用户，邮箱是否已经激活，如果激活，返回ture，默认是false)
	* @param @param email
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/emailActivatedStatus")
	public ResponseEntity<?> emailActivatedStatus(@RequestParam(value="", required=true) String email){
		
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("emailActivatedStatus", userService.emailActivatedStatus(email));
		return ResponseEntity.ok(result);
	}
	
	
	/**
	 * 
	* @Title: emailActivateSendTimesNotOver
	* @Description: TODO(rest 查询当前邮件激活的次数，有没有超过最大的次数限制，如果么有超过返回true，否则false)
	* @param @param email
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/emailActivateSentTimesNotOver")
	public ResponseEntity<?> emailActivateSentTimesNotOver(@RequestParam(value="", required=true) String email){
		
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("emailActivateSendTimesNotOver", userService.emailActivateSendTimesNotOver(email));
		return ResponseEntity.ok(result);
	}
	
	
	
	

}
