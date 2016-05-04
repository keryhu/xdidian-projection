/**  
* @Title: AuthenticationFailureListener.java
* @Package com.xdidian.keryhu.authserver.security
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月29日 下午9:23:42
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import com.xdidian.keryhu.authserver.service.LoginAttemptUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: AuthenticationFailureListener
* @Description: TODO(用户监听auth－service 用户输入登陆账户，密码错误时，自动运行的class)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月29日 下午9:23:42
*/
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent>  {

	private final LoginAttemptUserService loginAttemptUserService;

	/**
	* <p>Title: onApplicationEvent</p>
	* <p>Description: 监听登陆信息输入有误时，自动运行的方法。 </p>
	* @param e 输入的登陆信息。以及remote 客户端ip等一些信息
	* @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	*/ 
	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
		// TODO Auto-generated method stub
		WebAuthenticationDetails auth = (WebAuthenticationDetails) 
		          e.getAuthentication().getDetails();
		
		//获取登陆的用户名
		String loginName=e.getAuthentication().getName();
		
		String ip=auth.getRemoteAddress();
		
		loginAttemptUserService.loginFail(ip, loginName);
		
		log.info("auth failer is running and auth is : "+auth);
		
	}

}
