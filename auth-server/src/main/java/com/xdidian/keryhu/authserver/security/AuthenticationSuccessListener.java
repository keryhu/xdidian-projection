/**  
* @Title: AuthenticationSuccessListener.java
* @Package com.xdidian.keryhu.authserver.security
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月30日 下午12:37:32
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;


import com.xdidian.keryhu.authserver.service.LoginAttemptUserService;
import com.xdidian.keryhu.authserver.stream.LoginSuccessProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: AuthenticationSuccessListener
* @Description: TODO(用户登陆成功后，监听的class)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月30日 下午12:37:32
*/
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AuthenticationSuccessListener  implements ApplicationListener<AuthenticationSuccessEvent>{
	
	private final LoginAttemptUserService loginAttemptUserService;
	
	private final LoginSuccessProducer sendSource;
	
	/**
	* <p>Title: onApplicationEvent</p>
	* <p>Description: 用户成功登陆后，监听的事件方法。</p>
	* @param e  登陆成功后，客户拥有的remote 基本信息，包含ip，登陆账户uuid等。
	* @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	*/ 
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent e) {
		// TODO Auto-generated method stub
		 WebAuthenticationDetails auth = (WebAuthenticationDetails) 
		          e.getAuthentication().getDetails();
		 //如果登录成功，那么这个就是userId
		 String userId=e.getAuthentication().getName();
		 String ip=auth.getRemoteAddress();
		 loginAttemptUserService.loginSuccess(ip, userId);
		 log.info("登陆成功后，远程登陆的ip地址是：{} , userId is :  ",auth.getRemoteAddress(),userId);
		 
		 //登录成功后，通过spring cloud stream rabbit 将登录成功的userId发送出去，user－account接受
		 sendSource.send(userId);
		 
	}

}
