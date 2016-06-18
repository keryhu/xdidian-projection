/**  
* @Title: AuthenticationSuccessListener.java
* @Package com.xdidian.keryhu.authserver.security
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月30日 下午12:37:32
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.security;

import com.xdidian.keryhu.authserver.service.LoginAttemptUserService;
import com.xdidian.keryhu.authserver.stream.LoginSuccessProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * Description : 用户登陆成功后，监听的class
 * Date : 2016年06月17日 下午9:12
 * Author : keryHu keryhu@hotmail.com
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationSuccessListener  implements ApplicationListener<AuthenticationSuccessEvent>{
	
	private final LoginAttemptUserService loginAttemptUserService;
	
	private final LoginSuccessProducer sendSource;
	
	/**
	 *  用户成功登陆后，监听的事件方法。</p>
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
		 
		 //登录成功后，通过spring cloud stream rabbit 将登录成功的userId发送出去，user－account接受
		 sendSource.send(userId);
		 
	}

}
