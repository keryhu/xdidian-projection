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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xdidian.keryhu.authserver.client.EmailActivatedClient;

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
	
	
	
	@RequestMapping(method=RequestMethod.GET,value="/query/findEmailActivatedStatus")
	public ResponseEntity<?> findEmailActivatedStatus(@RequestParam(value="", required=true) String email){
		
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("emailActivatedStatus", emailActivatedClient.findEmailActivatedStatus(email));
		return ResponseEntity.ok(result);
	}

}
