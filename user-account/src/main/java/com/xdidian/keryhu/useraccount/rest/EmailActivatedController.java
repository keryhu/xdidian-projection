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
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xdidian.keryhu.useraccount.service.EmailActivatedService;
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
@RibbonClient("auth-server")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailActivatedController {

	private final EmailActivatedService emailActivatedService;
	
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
		result.put("emailActivatedCodeExist", emailActivatedService.emailActivatedCodeExist(email, emailActivatedCode));
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
		result.put("emailActivatedExpired", emailActivatedService.emailActivatedExpired(email));
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
		result.put("emailActivatedStatus", emailActivatedService.emailActivatedStatus(email));
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
		result.put("emailActivateSentTimesNotOver", emailActivatedService.emailActivateSentTimesNotOver(email));
		return ResponseEntity.ok(result);
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/users/query/emailActivated")
	public String clickEmailActivateUrl(@RequestParam(value="", required=true) String email,
			@RequestParam(value="", required=true) String emailActivatedCode,RedirectAttributes attr){
		//接下来就是验证 这个url中 email 和 emailactivatedCode 是否符合要求。这个只有后台处理程序，处理结果json 报出，给前台现实
		
		emailActivatedService.validateEmailActivatedAndSave(email, emailActivatedCode);
		
		//跳转email激活成功的页面，然后倒数4秒，跳转登录页面
		String message=new StringBuffer(email)
				   .append("  已经成功激活，请登录帐号！")
				   .toString();
		attr.addAttribute("emailActivated", message);
		
		log.info("user-account email成功激活，且设置了提示信息。将跳转login页面");
		//使用ribbon 定位url页面
		return "redirect:http://auth-server/uaa/login";
	}
	
	

}
