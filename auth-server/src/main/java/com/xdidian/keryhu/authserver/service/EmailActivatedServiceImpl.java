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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import com.xdidian.keryhu.authserver.client.EmailActivatedClient;
import com.xdidian.keryhu.authserver.exception.EmailActivatedSentTimesOverException;
import com.xdidian.keryhu.authserver.exception.EmailActivatedTooOftenException;
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
	* <p>Description: 处理email激活的事情,所有的数据处理，页面显示均在前台，后台只负责报错</p>
	* @param id
	* @see com.xdidian.keryhu.authserver.service.EmailActivatedService#checkAndDoByEmaiActivated(java.lang.String)
	*/ 
	@Override
	public void handleEmaiActivated(String id,String email) {
		// TODO Auto-generated method stub
		
		if(emailActivatedClient.emailActivatedExpired(email).get("emailActivatedExpired")){
			//处理user 删除，发送message
			producer.send(id);
			log.info("email 激活时间已经过期，刚刚发送删除此user 的消息出去。");
			throw new BadCredentialsException("您提供的email尚未注册成功！");
		} else if(emailActivatedClient.emailActivateSentTimesNotOver(email).get("emailActivateSentTimesNotOver")){
			//如果email的激活次数没有超过最大的限制，显示提示“您的email还未激活，请查看邮箱，或再次发送激活邮件，
			//或者重新注册”  ,后台只负责错误遇到就暂停执行后续，具体如何执行后续，根据前台点击 再次发送注册，还是重新注册来判断
			
			throw new EmailActivatedSentTimesOverException("您的email还未激活，请查看邮箱，或再次发送激活邮件，或者重新注册");
		} else{
			
			throw new EmailActivatedTooOftenException("您点击“再次激活帐号”太过频繁，请稍后再试，或者重新注册！");
		}
		
	}

}
