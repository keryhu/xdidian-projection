/**  
* @Title: EmailActivatedConsumer.java
* @Package com.xdidian.keryhu.mailServer.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午5:38:02
* @version V1.0  
*/ 
package com.xdidian.keryhu.mailServer.stream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.thymeleaf.context.Context;
import com.xdidian.keryhu.domain.EmailActivatedDto;
import com.xdidian.keryhu.mailServer.domain.HostProperty;
import com.xdidian.keryhu.mailServer.mail.EmailHtmlSender;


/**
* @ClassName: EmailActivatedConsumer
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午5:38:02
*/
@EnableBinding(EmailActivatedInputChannel.class)
public class EmailActivatedConsumer {
	
	@Autowired
	private EmailHtmlSender mailSender;
	
	@Autowired
	private HostProperty hostProperty;
	
	@StreamListener(EmailActivatedInputChannel.NAME)
	public void receive(EmailActivatedDto dto){
		
		//通过zuul 中转到 user-account，这样前台 点击 url，后台user-account使用 get 方法来处理
		
		String url=new StringBuffer(hostProperty.getHostName())
				.append(":8080/email-activate/email/emailActivatedConfirm?email=")
				.append(dto.getEmail())
				.append("&token=")
				.append(dto.getEmailToken())
				.toString();
		Context context = new Context();
		context.setVariable("dto", dto);
		context.setVariable("url", url);
		

		mailSender.send(dto.getEmail(), "新地点帐号激活", "emailActivated",context);
			
		
	}
}
