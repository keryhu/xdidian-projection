/**  
* @Title: AuthenticationSuccessListener.java
* @Package com.xdidian.keryhu.authserver.security
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月30日 下午12:37:32
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
* @ClassName: AuthenticationSuccessListener
* @Description: TODO(用户登陆成功后，监听的class)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月30日 下午12:37:32
*/
@Component
public class AuthenticationSuccessListener  implements ApplicationListener<AuthenticationSuccessEvent>{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessListener.class);

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
		 String uuid=e.getAuthentication().getName();
		 
		 logger.info("登陆成功后，远程登陆的ip地址是： "+auth.getRemoteAddress()+"uuid is : "+uuid);
		 
	}

}
