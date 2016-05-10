/**  
* @Title: EmailHtmlSender.java
* @Package com.xdidian.keryhu.mailServer.mail
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月8日 下午10:04:32
* @version V1.0  
*/ 
package com.xdidian.keryhu.mailServer.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
* @ClassName: EmailHtmlSender
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月8日 下午10:04:32
*/
@Component
public class EmailHtmlSender {
	
	@Autowired
	private EmailActivatedMailSender emailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public EmailStatus send(String to, String subject, String templateName, Context context) {
        String body = templateEngine.process(templateName, context);
        return emailSender.sendHtml(to, subject, body);
    }
	

}
