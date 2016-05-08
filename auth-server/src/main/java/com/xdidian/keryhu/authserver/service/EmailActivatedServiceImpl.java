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
import com.xdidian.keryhu.authserver.exception.EmailActivatedSentTimesOverException;
import com.xdidian.keryhu.authserver.stream.RemoveUserProducer;

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
	private final RemoveUserProducer producer;

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
			producer.send(id);
			log.info("email 激活时间已经过期，刚刚发送删除此user 的消息出去。");
		} else if(emailActivatedClient.emailActivateSentTimesNotOver(email)){
			//如果email的激活次数没有超过最大的限制，显示提示“您的email还未激活，请查看邮箱，或再次发送激活邮件，
			//或者更换email”  ,对这个错误进行处理，让它显示在前台web
			throw new EmailActivatedSentTimesOverException("您的email还未激活，请查看邮箱，或再次发送激活邮件，或者更换email");
		}
		
	}

}
