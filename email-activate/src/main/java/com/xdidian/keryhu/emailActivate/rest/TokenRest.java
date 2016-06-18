/**  
* @Title: TokenRest.java
* @Package com.xdidian.keryhu.emailActivate.rest
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月11日 下午10:11:51
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.rest;


import com.xdidian.keryhu.emailActivate.client.UserClient;
import com.xdidian.keryhu.emailActivate.domain.TokenType;
import com.xdidian.keryhu.emailActivate.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.xdidian.keryhu.emailActivate.domain.Constants.EMAIL_NOT_ACTIVATED_AND_TOKEN_EXIST_AND_NOT_EXPIRED;
import static com.xdidian.keryhu.util.StringValidate.isEmail;

/**
 * Description : email激活token对外提供的rest接口
 * Date : 2016年06月18日 上午8:41
 * Author : keryHu keryhu@hotmail.com
 */
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenRest {
	
	private final UserClient userClient;
	private final VerifyUrl verifyUrl;
	private final RedirectService redirectService;
	private final ActivatedExpired activatedExpired;
	private final ResendService resendService;
	private final ActivatedConfirm activatedConfirm ;
			
	@RequestMapping(method=RequestMethod.GET,value="/email/emailActivatedConfirm")
	public ModelAndView urlConfirm(@RequestParam("email") final String email,
			@RequestParam("token") final String token,RedirectAttributes attr){
		
		//验证url
		Object obj=verifyUrl.verify(email, token, attr, TokenType.CONFIRM);
		if(obj instanceof ModelAndView){
			return (ModelAndView) obj;
		}
		else if(obj instanceof String&&obj.equals(
				EMAIL_NOT_ACTIVATED_AND_TOKEN_EXIST_AND_NOT_EXPIRED)){
			activatedConfirm.exceptRedirect(email);
			
			return redirectService.redirectLoginPage(email, attr);
		}
		
		return new ModelAndView();
	
    }
	
	
	
	
	/**
	 *
	 * 当用户login的时候，如果遇到email未激活，那么auth-server ，通过web调用auth-server
	* 后台的一个rest方法，/query/emailNotActived，此方法会再次调用本方法，来实现后续email未激活的验证逻辑
	* 省去了auth-server的重复处理，只要auth-server在后台真正登录的时候，遇到email未激活，进行拦截就可以了。)
	 */
	@RequestMapping(method=RequestMethod.GET,value="/email/notActivedWhilelogin")
	public ModelAndView emailNotActived (@RequestParam("email")  String email
			,RedirectAttributes attr){
		log.info("正在调用email-activate");
		Assert.hasText(email, "email不能为空");
		Assert.isTrue(isEmail(email), "您输入的不是eamil或者数据库中不存在，请查正后再试 ！");
		
		//如果email已经激活，则直接跳转login页面
		 if(userClient.emailStatus(email)){	 
			 return redirectService.redirectLoginPage(email, attr);	 
		 }

		 
		//如果激活时间没有过期，那么跳转带有重新发送url的页面  hostname/8080/register/result 页面，
		if(!verifyUrl.activatedExpired(email)){
			log.info("您登录的时候，发现email未激活，且未过期，我们正在为您导航到'再次发送或重新注册页面'！");
			return redirectService.redirectResendPage(email, attr);
		}
		
		//如果激活时间已经过期，那么执行激活过期的单独方法。
		else if(verifyUrl.activatedExpired(email)){
			log.info("激活时间已经过期。。。");
			return activatedExpired.executeExpired(email, attr);
		}  
		return new ModelAndView();
	}
	
	
	
	
	/**
	 *
	 * 当用户点击“再次发送激活邮件”的时候，调用的此方法
	* 分为两段 
	* 执行发送次数是否超的判断和消息提示，另外就是更新验证码和发送次数＋1，并保存数据库 －－就是
	* tokenService.doWithResend(email)执行的方法
	* 
	* 另外一段是： 如果没有过期，页面跳转 result页面 tokenService.doWhenNotExpired )
	 */
	@RequestMapping(method=RequestMethod.GET,value="/email/resend")
	public ModelAndView resend(@RequestParam("email") final String email,
			@RequestParam("token") final String token,RedirectAttributes attr){		
		
		Object obj=verifyUrl.verify(email, token, attr, TokenType.RESEND);
		if(obj instanceof ModelAndView){
			return (ModelAndView) obj;
		}
		else if(obj instanceof String&&obj.equals(
				EMAIL_NOT_ACTIVATED_AND_TOKEN_EXIST_AND_NOT_EXPIRED)){
			//点击“重新发送激活邮件”时间为冷却，过滤操作次数过多，和数据库处理
			resendService.exec(email);
			 //跳转到 重新发送 的页面
			return redirectService.redirectResendPage(email, attr);
		}
		 return new ModelAndView();
	}
	
	
	/**
	 *
	 * 当用户点击“重新注册”时候，调用的此方法，执行的方法和激活过期一样的
	 */
	@RequestMapping(method=RequestMethod.GET,value="/email/reregister")
	public ModelAndView reregister(@RequestParam("email") final String email,
			@RequestParam("token") final String token,RedirectAttributes attr){
		
		Object obj=verifyUrl.verify(email, token, attr, TokenType.REREGISTER);
		if(obj instanceof ModelAndView){
			return (ModelAndView) obj;
		}
		else if(obj instanceof String&&obj.equals(
				EMAIL_NOT_ACTIVATED_AND_TOKEN_EXIST_AND_NOT_EXPIRED)){
			return  activatedExpired.executeExpired(email, attr);
		}
		return  new ModelAndView();
	}
	
}
	
	
