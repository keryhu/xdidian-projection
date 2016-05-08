/**  
* @Title: EmailActivatedConsumer.java
* @Package com.xdidian.keryhu.mailServer.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午5:38:02
* @version V1.0  
*/ 
package com.xdidian.keryhu.mailServer.stream;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.xdidian.keryhu.domain.EmailActivatedDto;
import com.xdidian.keryhu.mailServer.mail.EmailActivatedMailSender;
import com.xdidian.keryhu.mailServer.mail.HostProperty;

import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: EmailActivatedConsumer
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午5:38:02
*/
@EnableBinding(EmailActivatedInputChannel.class)
@Slf4j
@RibbonClient("user-account")
public class EmailActivatedConsumer {
	
	@Autowired
	private EmailActivatedMailSender sender;
	
	@Autowired
	private HostProperty hostProperty;
	
	@StreamListener(EmailActivatedInputChannel.NAME)
	public void receive(EmailActivatedDto dto){
		
		//通过zuul 中转到 user-account，这样前台 点击 url，后台user-account使用 get 方法来处理
		StringBuffer url=new StringBuffer("http://user-account/users/query/emailActivated?email=")
				                      // .append("://8080/user-account/users/query/emailActivated?email=")
				                       .append(dto.getEmail())
				                       .append("&emailActivatedCode=")
				                       .append(dto.getEmailActivatedCode());
		
		
		
		StringBuffer body=new StringBuffer("<html><body>点击此链接完成邮箱注册：<a href=")
				.append(url)
				.append("</a></body></html>");
		
		try {
			sender.send(dto.getEmail(), "新地点email激活", body.toString());
			log.info("mail server 发送email 激活邮件成功！");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			log.error("mail server 发送email 激活邮件失败！");
			e.printStackTrace();
		}
	}

}
