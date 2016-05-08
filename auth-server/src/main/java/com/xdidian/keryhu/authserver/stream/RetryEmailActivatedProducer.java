/**  
* @Title: RetryEmailActivatedProducer.java
* @Package com.xdidian.keryhu.authserver.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午2:12:41
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import com.xdidian.keryhu.exception.MessageNotSendException;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: RetryEmailActivatedProducer
* @Description: TODO(当login 页面，因为客户email未激活的情况下，且激活次数未超过限制时，
* 客户需要重新发送email激活邮件服务的具体实施方法)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午2:12:41
*/
@Component
@EnableBinding(RetryEmailActivatedOutputChannel.class)
@Slf4j
public class RetryEmailActivatedProducer {
	
	@Autowired
	private RetryEmailActivatedOutputChannel channel;
	
	public void send(String email){
		boolean result = channel.retry()
				                .send(MessageBuilder.withPayload(email).build());
		
		 if (!result) {
			    log.error("服务器再次发送email激活的message消息失败！");
	            throw new MessageNotSendException("服务器再次发送email激活的message消息失败！");
	        }
	}

}
