/**  
* @Title: VerifyUrlImpl.java
* @Package com.xdidian.keryhu.emailActivate.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月17日 下午1:53:07
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.service.impl;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.xdidian.keryhu.emailActivate.client.UserClient;
import com.xdidian.keryhu.emailActivate.domain.TokenType;
import com.xdidian.keryhu.emailActivate.exception.EmailNotFoundException;
import com.xdidian.keryhu.emailActivate.exception.TokenNotFoundException;
import com.xdidian.keryhu.emailActivate.repository.ActivatedTokenRepository;
import com.xdidian.keryhu.emailActivate.service.ActivatedExpired;
import com.xdidian.keryhu.emailActivate.service.RedirectService;
import com.xdidian.keryhu.emailActivate.service.VerifyToken;
import com.xdidian.keryhu.emailActivate.service.VerifyUrl;
import static com.xdidian.keryhu.emailActivate.domain.Constants.EMAIL_NOT_ACTIVATED_AND_TOKEN_EXIST_AND_NOT_EXPIRED;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: VerifyUrlImpl
* @Description: TODO(email激活过程中，验证激活url，重新发送邮件的url、重新注册的url中都需要
* 验证url的真伪，此服务就是具体的实现，验证的逻辑如下：
* email是否存在于数据库
* email是否已经激活
* email对应的token是否存在于数据库，此接口由 VerifyToken service已经实现
* 激活时间有没有过期，如果过期，执行 激活过期的service
* 如果没有过期，那么返回boolean)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月17日 下午1:53:07
*/
@Component("verifyUrl")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
   class VerifyUrlImpl implements VerifyUrl {
	
	private final VerifyToken verifyToken;
	private final ActivatedTokenRepository repository;
	private final UserClient userClient;
	private final RedirectService redirectService;
	private final ActivatedExpired activatedExpired;
	
	/**
	* <p>Title: verify</p>
	* <p>Description: 具体的验证url的方法
	* 1 如果email不存在于数据库，报告exception，
	* 2 如果email已经激活，则直接执行 redirectService.redirectLoginPage 方法
	* 3 如果验证码不存在直接报错
	* 4 如果激活过期，则执行activatedExpired.executeExpired方法
	* 5 最好返回一个常量标志。。，表示此URL已经通过了验证，该报错的地方已经包错过了，最后能得到string说明：
	* email 未激活，token存在于数据库，且激活时间未过期。
	* </p>
	* @param eamil  @param token
	* @param attr   @param tokenType  需要被验证的url的类型
	* @param roles 用户权限
	* @return
	* @see com.xdidian.keryhu.emailActivate.service.VerifyUrl#verify(java.lang.String, java.lang.String)
	*/
	@Override
	
	 public Object verify(final String email, final String token,final RedirectAttributes attr,
			 TokenType tokenType) {
		// TODO Auto-generated method stub
		if(userClient.emailStatus(email)){
			return redirectService.redirectLoginPage(email, attr);
		}
		return repository.findByEmail(email).map(e->{
			if(!verifyToken.tokenExist(email, token, tokenType)){
				throw new TokenNotFoundException("Url中token不存在！请提供正确的链接");
			}
			else if(activatedExpired(email)){
				log.info("email为 {} 激活时间已经过期",e.getEmail());
				return activatedExpired.executeExpired(email, attr);
				
			}
			
			return EMAIL_NOT_ACTIVATED_AND_TOKEN_EXIST_AND_NOT_EXPIRED;
		}).orElseThrow(()->new EmailNotFoundException("email不存在，请查证后再试！"));
		
		
	}

	/**
	* <p>Title: activatedExpired</p>
	* <p>Description: 验证激活时间有没有过期</p>
	* @param email
	* @return
	* @see com.xdidian.keryhu.emailActivate.service.VerifyUrl#activatedExpired(java.lang.String)
	*/ 
	@Override
	public boolean activatedExpired(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email)
		         .filter(e->e.getExpiryDate()!=null)
		         .map(e->LocalDateTime.now().isAfter(e.getExpiryDate()))
		         .orElseThrow(()->new EmailNotFoundException("您要激活的email不存在于数据库"));	
		
	}

}
