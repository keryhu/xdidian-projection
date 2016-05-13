/**  
* @Title: ActivatedSuccessProducer.java
* @Package com.xdidian.keryhu.emailActivate.stream
* @Description: TODO(当email被用户点击url成功激活后，发送这个message通知，user-account接受到通知后
* 更改email所在的user数据库的emailStatus为truer)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月6日 下午9:22:45
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.xdidian.keryhu.exception.MessageNotSendException;

import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: EmailActivatedExpiredProducer
* @Description: TODO(当email被用户点击url成功激活后，发送这个message通知，user-account接受到通知后
* 更改email所在的user数据库的emailStatus为truer)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月6日 下午9:22:45
*/
@Component
@EnableBinding(ActivatedSuccessOutputChannel.class)
@Slf4j
public class ActivatedSuccessProducer {
	
	@Autowired
	private ActivatedSuccessOutputChannel channel;
	
	/**
	 * 
	* @Title: send
	* @Description: TODO(当email被用户点击url成功激活后，发送这个message通知，user-account接受到通知后
	* 更改email所在的user数据库的emailStatus为true)
	* @param @param email    设定文件
	* @return void    返回类型
	* @throws
	 */
	public void send(String email){
		boolean result=channel.success()
				              .send(MessageBuilder.withPayload(email).build());
		
		
		if(!result){
			 log.error("发送激活成功的email message出去失败");
	         throw new MessageNotSendException("发送激活成功的email message出去失败！");
		}
	}

}
