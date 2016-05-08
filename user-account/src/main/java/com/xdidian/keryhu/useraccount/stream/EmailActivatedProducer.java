/**  
* @Title: EmailActivatedProducer.java
* @Package com.xdidian.keryhu.useraccount.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午4:36:03
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import com.xdidian.keryhu.domain.EmailActivatedDto;
import com.xdidian.keryhu.exception.MessageNotSendException;

import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: EmailActivatedProducer
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午4:36:03
*/
@Component
@EnableBinding(EmailActivatedOutputChannel.class)
@Slf4j
public class EmailActivatedProducer {
	
	@Autowired
	private  EmailActivatedOutputChannel channel;
	
	public void send(EmailActivatedDto dto){
		boolean result=channel.emailActivatedOutput()
				              .send(MessageBuilder.withPayload(dto).build());
		
		if (!result) {
		    log.error("服务器发送email激活的message消息失败！");
            throw new MessageNotSendException("服务器发送email激活的message消息失败！");
        }
	}
	
	
	

}
