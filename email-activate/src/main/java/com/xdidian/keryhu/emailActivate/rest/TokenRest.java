/**  
* @Title: TokenRest.java
* @Package com.xdidian.keryhu.emailActivate.rest
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月11日 下午10:11:51
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.util.StringUtils;
import com.xdidian.keryhu.emailActivate.client.UserClient;
import com.xdidian.keryhu.emailActivate.domain.ActivatedProperties;
import com.xdidian.keryhu.emailActivate.exception.CannotRestException;
import com.xdidian.keryhu.emailActivate.exception.EmailNotFoundException;
import com.xdidian.keryhu.emailActivate.repository.ActivatedTokenRepository;
import com.xdidian.keryhu.emailActivate.service.TokenService;
import com.xdidian.keryhu.util.StringValidate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: ActivatedTokenRest
* @Description: TODO(email激活token对外提供的rest接口)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月11日 下午10:11:51
*/
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenRest {
	
	private final ActivatedTokenRepository repository;
	
	private final UserClient userClient;

	private final TokenService tokenService; 
	
	private final ActivatedProperties activatedProperties;
	
	@RequestMapping(method=RequestMethod.GET,value="/email/emailActivatedConfirm")
	public ModelAndView urlConfirm(@RequestParam("email") final String email,
			@RequestParam("token") final String token,RedirectAttributes attr){
		
		if(StringUtils.isNullOrEmpty(email)||StringUtils.isNullOrEmpty(token)){
			throw new EmailNotFoundException("email或token 不能为空！ ");
		}
		else if(!StringValidate.isEmail(email)){
			throw new EmailNotFoundException("您输入的不是eamil，请检查后再试 ！ ");
		}
		
		//如果email不存在，跳转注册页面，然后显示email未注册，提示先注册
		boolean notInToken=!repository.findByEmail(email).isPresent();
		boolean notInUser=!userClient.isEmailExist(email);
		ModelAndView mav=new ModelAndView();
		
		if(notInToken||notInUser){
			 String redirectUrl=activatedProperties.getReregisterUrl();
			 String message=new StringBuffer("尊敬的 ：").append(email)
					 .append("  用户，您提交的email还未注册，请先注册！").toString();
			 
			 attr.addAttribute("notRegister",message);
			 
			 mav.setViewName(redirectUrl);
			 return mav;		 
		}		
					
		
		
		//如果email已经激活，则直接跳转login页面
		 if(userClient.emailStatus(email)){
			 String message=new StringBuffer("尊敬的 ：").append(email)
					 .append("  用户，您已成功激活，请直接登录！").toString();
			 String redirectUrl=activatedProperties.getLoginUrl();
			 attr.addAttribute("emailActivated",message);
			 mav.setViewName(redirectUrl);
			 return mav;		 
		 }
		
		//将email没有激活的情况处理，封装在这个service里
		tokenService.ConfirmUrl(email,token,mav,attr);

		return mav;
    }
	
	
	
	
	/**
	 * 
	* @Title: emailNotActived
	* @Description: TODO(当用户login的时候，如果遇到email未激活，那么auth-server ，通过web调用auth-server
	* 后台的一个rest方法，/query/emailNotActived，此方法会再次调用本方法，来实现后续email未激活的验证逻辑
	* 省去了auth-server的重复处理，只要auth-server在后台真正登录的时候，遇到email未激活，进行拦截就可以了。)
	* @param @param email
	* @param @param attr
	* @param @return    设定文件
	* @return ModelAndView    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/email/notActivedWhilelogin")
	public ModelAndView emailNotActived (@RequestParam("email")  String email
			,RedirectAttributes attr){
		ModelAndView mav=new ModelAndView();
		log.info("正在调用email-activate");
		if(StringUtils.isNullOrEmpty(email)){
			throw new EmailNotFoundException("email 不能为空！ ");
		}
		
		if(!StringValidate.isEmail(email)){
			throw new EmailNotFoundException("您输入的数据不是email，请查正后再试！ ");
		}
		
		//如果激活时间没有过期，那么跳转  hostname/8080/register/result 页面，并且附带email参数
		if(!tokenService.activateExpired(email)){
			log.info("您登录的时候，发现email未激活，且未过期，我们正在为您导航到'再次发送或重新注册页面'！");
			tokenService.doWhenNotExpired(email, mav, attr);
		}
		
		//如果激活时间已经过期，那么执行激活过期的单独方法。
		else if(tokenService.activateExpired(email)){
			log.info("激活时间已经过期。。。");
			tokenService.doWhenExpired(email, mav, attr);
		}  else {
			//报错，您无法执行此操作
			throw new CannotRestException("您无法执行此操作！");
		}
		return mav;
	}
	
	
	
	
	/**
	 * 
	* @Title: resend
	* @Description: TODO(当用户点击“再次发送激活邮件”的时候，调用的此方法)
	* @param @param email
	* @param @param attr
	* @param @return    设定文件
	* @return ModelAndView    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/email/resend")
	public ModelAndView resend(@RequestParam("email") final String email
			,RedirectAttributes attr){
		//所有代码封装在service 里
		 tokenService.doWithResend(email);
		 ModelAndView mav=new ModelAndView();
		 String redirectUrl=activatedProperties.getRegisterResultUrl();
		//重新发送的url
		String resend=new StringBuffer(activatedProperties.getResendUrl())
					.append(email)
					.toString();		
					
			//重新注册的url
		String reregister=new StringBuffer(activatedProperties.getReregisterUrl())
					.append(email)
					.toString();	
			
				
		attr.addAttribute("resend", resend);
		attr.addAttribute("reregister", reregister);
		 
		attr.addAttribute("resendInfo","再次发送邮件成功！");
		
		 mav.setViewName(redirectUrl);
		 return mav;
	}
	
	
	/**
	 * 
	* @Title: reregister
	* @Description: TODO(当用户点击“重新注册”时候，调用的此方法)
	* @param @param email
	* @param @param attr
	* @param @return    设定文件
	* @return ModelAndView    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/email/reregister")
	public ModelAndView reregister(@RequestParam("email") final String email
			,RedirectAttributes attr){
		
		if(StringUtils.isNullOrEmpty(email)){
			throw new EmailNotFoundException("email 不能为空！ ");
		}
		
		ModelAndView mav=new ModelAndView();
		//执行和激活过期相同的代码
		tokenService.doWhenExpired(email, mav, attr);
		
		 
		mav.setViewName(activatedProperties.getDefaultRegisterUrl());
		return mav;
	}
	
	
}
	
	
