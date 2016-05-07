/**  
* @Title: EmailActivatedServiceImpl.java
* @Package com.xdidian.keryhu.authserver.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 上午8:54:34
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.xdidian.keryhu.authserver.client.EmailActivatedClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: EmailActivatedServiceImpl
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 上午8:54:34
*/
@Component("emailActivatedService")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailActivatedServiceImpl implements EmailActivatedService {
	
	private final EmailActivatedClient emailActivatedClient;

	/**
	* <p>Title: handleEmaiActivated</p>
	* <p>Description: 处理email激活的事情</p>
	* @param id
	* @see com.xdidian.keryhu.authserver.service.EmailActivatedService#checkAndDoByEmaiActivated(java.lang.String)
	*/ 
	@Override
	public void handleEmaiActivated(String id,String email) {
		// TODO Auto-generated method stub
		
		if(emailActivatedClient.emailActivatedExpired(email)){
			//处理user 删除，发送message
		} else if(emailActivatedClient.emailActivateSentTimesNotOver(email)){
			//如果email的激活次数没有超过最大的限制，显示“再次发送邮件，或者更换email
			
		}
		
	}

}
