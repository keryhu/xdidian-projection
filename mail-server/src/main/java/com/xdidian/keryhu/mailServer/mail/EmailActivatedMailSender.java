/**  
* @Title: EmailActivatedMailSender.java
* @Package com.xdidian.keryhu.mailServer.mail
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午5:56:50
* @version V1.0  
*/ 
package com.xdidian.keryhu.mailServer.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
* @ClassName: EmailActivatedMailSender
* @Description: TODO(创建一个发送email 激活的 邮件 sender 方法)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午5:56:50
*/
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailActivatedMailSender {
	
	private final JavaMailSender javaMailSender;
	
    public void send(String to, String subject,String body) throws MessagingException{
		
		final MimeMessage mimeMessage=this.javaMailSender.createMimeMessage();
		
		//暗示发送的是html内容
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
		
		//产品上线时候，需要更改
		message.setFrom("948747450@qq.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body,true);
		this.javaMailSender.send(mimeMessage);
		
	}

}
