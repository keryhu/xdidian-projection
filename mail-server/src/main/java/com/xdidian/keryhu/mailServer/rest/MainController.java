package com.xdidian.keryhu.mailServer.rest;

import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import com.xdidian.keryhu.mailServer.mail.EmailHtmlSender;
import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainController {

	private final EmailHtmlSender mailSender;


    //产品上线时候，这个rest，不需要，通过jms，传递参数和服务
	@RequestMapping("/mail")
    public void mail() {
		String url="http://www.163.com";
		String email="keryhu@hotmail.com";
		LocalDateTime d=LocalDateTime.now();
		Context context = new Context();
		context.setVariable("url", url);
		context.setVariable("email", email);
		context.setVariable("deadline", d);
		
		mailSender.send("15900455491@163.com", "新地点帐号激活", "emailActivated",context);
		
      
    }
	
	
}
