package com.xdidian.keryhu.mailServer.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


/**
* @ClassName: SmtpMailSender
* @Description: TODO(邮件发送主程序)
* @author keryhu  keryhu@hotmail.com
* @date 2016年3月4日 下午1:17:20
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmtpMailSender {
	
	private final JavaMailSender javaMailSender;
	
	public void send(String to, String subject,String body) throws MessagingException{
		
		final MimeMessage mimeMessage=this.javaMailSender.createMimeMessage();
		
		final MimeMessageHelper message=new MimeMessageHelper(mimeMessage);
		
		//产品上线时候，需要更改
		message.setFrom("948747450@qq.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		this.javaMailSender.send(mimeMessage);
		
	}

}
