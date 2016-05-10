/**  
* @Title: EmailActivatedMailSender.java
* @Package com.xdidian.keryhu.mailServer.mail
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午5:56:50
* @version V1.0  
*/ 
package com.xdidian.keryhu.mailServer.mail;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: EmailActivatedMailSender
* @Description: TODO(创建一个发送email 激活的 邮件 sender 方法)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午5:56:50
*/
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EmailActivatedMailSender {
	
	private final JavaMailSender javaMailSender;
	
    
    public EmailStatus sendPlainText(String to, String subject, String text) {
        return sendM(to, subject, text, false);
    }
 
    public EmailStatus sendHtml(String to, String subject, String htmlBody) {
        return sendM(to, subject, htmlBody, true);
    }
    
    
    private EmailStatus sendM(String to, String subject, String text, Boolean isHtml) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setFrom("948747450@qq.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            javaMailSender.send(mail);
            log.info("Send email '{}' to: {}", subject, to);
            return new EmailStatus(to, subject, text).success();
        } catch (Exception e) {
            log.error(String.format("Problem with sending email to: {}, error message: {}", to, e.getMessage()));
            return new EmailStatus(to, subject, text).error(e.getMessage());
        }
    }

}
