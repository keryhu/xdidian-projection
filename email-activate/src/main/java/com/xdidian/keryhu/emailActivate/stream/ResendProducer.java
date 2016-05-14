/**  
* @Title: ResendProducer.java
* @Package com.xdidian.keryhu.useraccount.stream
* @Description: TODO(当用户点击再次发送email激活账户的时候，促发此message的具体执行)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午4:36:03
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.xdidian.keryhu.domain.EmailActivatedDto;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: EmailActivatedProducer
* @Description: TODO(当用户点击再次发送email激活账户的时候，促发此message的具体执行)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午4:36:03
*/
@Component
@EnableBinding(ResendOutputChannel.class)
@Slf4j
public class ResendProducer {
	
	@Autowired
	private  ResendOutputChannel channel;
	
	public void send(EmailActivatedDto dto){
		
		boolean result=channel.resend()
				              .send(MessageBuilder.withPayload(dto).build());
		
		Assert.isTrue(result, "用户用户点击'再次发送邮件激活的需求，再次发送的请求message发出失败！");
		
		log.info("用户用户点击'再次发送邮件激活的需求，再次发送的请求message发出！'");
	}
	
	
	

}
