package com.xdidian.keryhu.mailActivate.rest;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xdidian.keryhu.mailActivate.mail.SmtpMailSender;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainController {

	private final SmtpMailSender mailSender;


    //产品上线时候，这个rest，不需要，通过jms，传递参数和服务
	@RequestMapping("/mail")
    public void mail() {
        try {
			mailSender.send("15900455491@163.com", "this is my first javaMail test", "你好，胡先生！");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
}
