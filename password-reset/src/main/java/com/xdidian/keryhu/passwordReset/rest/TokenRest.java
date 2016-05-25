/**  
* @Title: TokenRest.java
* @Package com.xdidian.keryhu.passwordReset.rest
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月16日 下午5:15:40
* @version V1.0  
*/ 
package com.xdidian.keryhu.passwordReset.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: TokenRest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月16日 下午5:15:40
*/
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenRest {
	
	@RequestMapping(method=RequestMethod.POST,value="/password/uploadEmail")
	public void uploadEmail(@RequestBody final String email){
		
	}
	
	/**
	 * 
	* @Title: validateEmail
	* @Description: TODO(忘记密码时，如果通过email重设密码，用户打开重设密码的链接
	* 需要验证的步骤
	* 1 id，即userId是否存在于数据库
	* 2 token是否和id匹配于user-account系统
	* 
	* 返回的是错误的提示，如果错误提示没有任何信息，那么证明通过了验证)
	* @param  id    userId
	* @param token  就是passwordToken
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/code")
	public ResponseEntity<?> validateEmail(@RequestParam("id") final String id,
			@RequestParam("token") final String token){
		Map<String,Integer> result=new HashMap<String,Integer>();
		
		
		return ResponseEntity.ok(result);
	}

}
