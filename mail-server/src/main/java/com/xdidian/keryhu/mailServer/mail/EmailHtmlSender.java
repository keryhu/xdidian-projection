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

import com.xdidian.keryhu.mailServer.config.MailSenderConfig;
import com.xdidian.keryhu.mailServer.domain.EmailStatus;

/**
* @ClassName: EmailHtmlSender
* @Description: TODO(thymeleaf html格式的 spring mail配置)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月8日 下午10:04:32
*/
@Component
public class EmailHtmlSender {
	
	@Autowired
	private MailSenderConfig emailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	/**
	 * 
	* @Title: send
	* @Description: TODO(发送邮件的主程序)
	* @param @param to    发送对象，目标邮件地址
	* @param @param subject   发送的主题
	* @param @param templateName    thymeleaf html 文件的地址 和文件名字
	* @param @param context      上下文。
	* @param @return    设定文件
	* @return EmailStatus    返回类型
	* @throws
	 */
	public EmailStatus send(String to, String subject, String templateName, Context context) {
        String body = templateEngine.process(templateName, context);
        return emailSender.sendHtml(to, subject, body);
    }
	

}
