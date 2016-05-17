/**  
* @Title: RedirectServiceImpl.java
* @Package com.xdidian.keryhu.emailActivate.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月17日 下午2:41:47
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xdidian.keryhu.domain.Role;
import com.xdidian.keryhu.emailActivate.domain.ActivatedProperties;
import com.xdidian.keryhu.emailActivate.repository.ActivatedTokenRepository;
import com.xdidian.keryhu.emailActivate.service.RedirectService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: RedirectServiceImpl
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月17日 下午2:41:47
*/
@Component("redirectService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
 class RedirectServiceImpl implements RedirectService {
	 
	 private final MessageSource messageSource;
	 private final ActivatedProperties activatedProperties;
	 private final ActivatedTokenRepository repository;

	/**
	* <p>Title: redirectLoginPage</p>
	* <p>Description:导航到login页面的具体实现 </p>
	* @param email
	* @param attr
	* @return
	* @see com.xdidian.keryhu.emailActivate.service.RedirectService#redirectLoginPage(java.lang.String, org.springframework.web.servlet.mvc.support.RedirectAttributes)
	*/
	@Override
	public ModelAndView redirectLoginPage(String email, RedirectAttributes attr) {
		// TODO Auto-generated method stub
		ModelAndView mav=new ModelAndView();
		Object[] args={email};
		String hasRegister=messageSource.getMessage("email.hasRegister",
				 args ,"您的email已注册，请先直接登录！",LocaleContextHolder.getLocale());
		
		String redirectUrl=activatedProperties.getLoginUrl();
		attr.addAttribute("emailActivated",hasRegister);
		mav.setViewName(redirectUrl); 	
		log.info("导航到login页面");
		return mav;
	}

	/**
	* <p>Title: redirectRegisterPage</p>
	* <p>Description: 根据role不同，导航到不同的注册页面，默认导航到 /register</p>
	* @param email
	* @param attr
	* @param roles
	* @return
	* @see com.xdidian.keryhu.emailActivate.service.RedirectService#redirectRegisterPage(java.lang.String, org.springframework.web.servlet.mvc.support.RedirectAttributes, java.util.List)
	*/ 
	@Override
	public ModelAndView redirectRegisterPage(String email, RedirectAttributes attr, List<Role> roles) {
		// TODO Auto-generated method stub
		ModelAndView mav=new ModelAndView();
		Object[] args={email};
		String notRegister=messageSource.getMessage("email.notRegister",
				 args ,"您的email还未注册，请先注册！",LocaleContextHolder.getLocale());
		 
		String redirectUrl=activatedProperties.getDefaultRegisterUrl();
		 
		attr.addAttribute("notRegister",notRegister);
		 
		mav.setViewName(redirectUrl);
		return mav;
	}

	/**
	* <p>Title: redirectResendPage</p>
	* <p>Description: 当激活时间没有过期，需要执行的方法，就是将email参数，一起redirect到 
	* hostname/8080/register/result 页面，方便用户点击“再次发送”和“重新注册”)</p>
	* @param email
	* @param attr
	* @return
	* @see com.xdidian.keryhu.emailActivate.service.RedirectService#redirectResendPage(java.lang.String, org.springframework.web.servlet.mvc.support.RedirectAttributes)
	*/ 
	@Override
	public ModelAndView redirectResendPage(String email, RedirectAttributes attr) {
		// TODO Auto-generated method stub
		ModelAndView mav=new ModelAndView();
		String resendToken=repository.findByEmail(email)
				.map(e->e.getResendToken())
				.orElse("");
		//重新发送的url
		String resend=activatedProperties.getResendUrl(email,resendToken);
				
		String registerToken=repository.findByEmail(email)
				.map(e->e.getReregisterToken())
				.orElse("");
		//重新注册的url
		String reregister=activatedProperties.getReregisterUrl(email,registerToken);
		
		String redirectUrl=activatedProperties.getRegisterResultUrl();
			
		attr.addAttribute("resend", resend);
		attr.addAttribute("reregister", reregister);
		mav.setViewName(redirectUrl);
		return mav;
	}

}
