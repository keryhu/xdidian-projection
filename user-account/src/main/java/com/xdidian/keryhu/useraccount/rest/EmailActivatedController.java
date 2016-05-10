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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.xdidian.keryhu.useraccount.domain.HostProperty;
import com.xdidian.keryhu.useraccount.exception.EmailActivatedSentTimesOverException;
import com.xdidian.keryhu.useraccount.service.EmailActivatedService;
import com.xdidian.keryhu.useraccount.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: EmailActivatedController
* @Description: TODO(在email激活服务过程中，需要用到的 rest controller )
* 因为有 @RibbonClient，所以不能使用lombok.RequiredArgsConstructor;
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月5日 下午8:07:21
*/
@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailActivatedController {

	
	private final EmailActivatedService emailActivatedService;
	
	private final HostProperty hostProperty;
	
	private final UserService userService;
	

	/**
	 * 
	* @Title: emailActivatedCodeExist
	* @Description: TODO(提供rest接口，查询email所在数据，emailActivatedCodeExist是否和参数相等)
	* @param @param loginName
	* @param @param emailActivatedCode
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型  返回map 的json 
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/emailActivatedCodeExist")
	public ResponseEntity<?> emailActivatedCodeExist(@RequestParam(value="", required=true) String loginName,
			@RequestParam(value="", required=true)String emailActivatedCode){
		
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("emailActivatedCodeExist", emailActivatedService.emailActivatedCodeExist(loginName, emailActivatedCode));
		return ResponseEntity.ok(result);
	}
	
	
	/**
	 * 
	* @Title: emailActivatedExpired
	* @Description: TODO(提供rest接口，loginName所在的数据库，在未激活的前提下，当前时间是否超过注册时间＋最大的允许过期时间)
	* @param @param loginName
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/emailActivatedExpired")
	public ResponseEntity<?> emailActivatedExpired(@RequestParam(value="", required=true) String loginName){
		
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("emailActivatedExpired", emailActivatedService.emailActivatedExpired(loginName));
		return ResponseEntity.ok(result);
	}
	
	
	/**
	 * 
	* @Title: emailActivatedStatus
	* @Description: TODO(rest 接口查询当前loginName所在的用户，邮箱是否已经激活，如果激活，返回ture，默认是false)
	* @param @param loginName
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/emailActivatedStatus")
	public ResponseEntity<?> emailActivatedStatus(@RequestParam(value="", required=true) String loginName){
		
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("emailActivatedStatus", emailActivatedService.emailActivatedStatus(loginName));
		return ResponseEntity.ok(result);
	}
	
	
	/**
	 * 
	* @Title: emailActivateSendTimesNotOver
	* @Description: TODO(rest 查询当前邮件激活的次数，有没有超过最大的次数限制，如果么有超过返回true，否则false)
	* @param @param loginName
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/emailActivateSentTimesNotOver")
	public ResponseEntity<?> emailActivateSentTimesNotOver(@RequestParam(value="", required=true) String loginName){
		
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("emailActivateSentTimesNotOver", emailActivatedService.emailActivateSentTimesNotOver(loginName));
		return ResponseEntity.ok(result);
	}
	
	
	/**
	 * 
	* @Title: clickEmailActivateUrl
	* @Description: TODO(将用户帐号激活的mail发送到客户的邮箱，客户点击邮箱的激活地址，所产生的后台验证程序。)
	* @param @param email
	* @param @param emailActivatedCode
	* @param @param attr
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
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
		String redirectUrl=new StringBuffer("redirect:")
				.append(hostProperty.getHostName())
				.append(":9999/uaa/login").toString();
		
		log.info("需要被跳转的url （加了redirect前缀）{} ",redirectUrl);
		
		return redirectUrl;
	}
	
	
	/**
	 * 
	* @Title: retryEmailActivated
	* @Description: TODO(用户注册完后，显示“请查收邮件，或垃圾邮箱或再次发送注册邮件，或重新注册的页面，
	* 注意－－此页面需要获取到之前用户注册的email或phone，也就是页面跳转时，需要传递email或phone参数。）
	* 前台通过zuul，转到此user－account，再转到此post接口)
	* @param @param loginName    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.POST,value="/retryEmailActivated")
	public void retryEmailActivated(@RequestBody String loginName){
		Assert.hasLength(loginName);
		//在发送次数未超过时，处理再次发送事情，否则
		if(emailActivatedService.emailActivateSentTimesNotOver(loginName)){
			//pc-gateway正在调用retry email帐号激活
			log.info("pc-gateway正在调用retry email帐号激活");
			emailActivatedService.retryEmailActivated(loginName);
		} else{
			throw new EmailActivatedSentTimesOverException("您的email激活次数过于频繁，请稍后再试！或者重新注册！");
		}
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/retryRegister")
	public void retryRegister(@RequestBody String loginName){
		Assert.hasLength(loginName);
		//在发送次数未超过时，处理再次发送事情，否则不做任何处理
		if(emailActivatedService.emailActivateSentTimesNotOver(loginName)){
			//pc-gateway正在调用retry email帐号激活
			log.info("pc-gateway正在调用retry 注册帐号激活");
			//删除此user，并且试图将页面跳转到 register页面，，（前台跳转）
			
			userService.findById(loginName)
			           .ifPresent(e->userService.deleteUser(e));
			
	
		}
	}
	
}
