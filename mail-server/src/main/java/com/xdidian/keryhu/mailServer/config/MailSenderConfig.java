/**  
* @Title: EmailActivatedMailSender.java
* @Package com.xdidian.keryhu.mailServer.mail
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午5:56:50
* @version V1.0  
*/ 
package com.xdidian.keryhu.mailServer.config;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.xdidian.keryhu.mailServer.domain.EmailStatus;

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
public class MailSenderConfig {
	
	private final JavaMailSender javaMailSender;
	
    
	/**
	 * 
	* @Title: sendPlainText
	* @Description: TODO(当发送邮件的内容，只是纯文本的时候。)
	* @param @param to
	* @param @param subject
	* @param @param text
	* @param @return    设定文件
	* @return EmailStatus    返回类型
	* @throws
	 */
    public EmailStatus sendPlainText(String to, String subject, String text) {
        return sendM(to, subject, text, false);
    }
 
    /**
     * 
    * @Title: sendHtml
    * @Description: TODO(发送内容为html格式的时候。)
    * @param @param to
    * @param @param subject
    * @param @param htmlBody
    * @param @return    设定文件
    * @return EmailStatus    返回类型
    * @throws
     */
    public EmailStatus sendHtml(String to, String subject, String htmlBody) {
        return sendM(to, subject, htmlBody, true);
    }
    
    
    private EmailStatus sendM(String to, String subject, String text, Boolean isHtml) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            //同事设置了附件功能
            MimeMessageHelper helper = new MimeMessageHelper(mail,true /* multipart */, "UTF-8");
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
