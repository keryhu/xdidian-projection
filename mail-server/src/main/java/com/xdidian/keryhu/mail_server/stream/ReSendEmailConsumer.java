package com.xdidian.keryhu.mail_server.stream;


import com.xdidian.keryhu.domain.AccountActivatedDto;
import com.xdidian.keryhu.mail_server.mail.EmailHtmlSender;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.thymeleaf.context.Context;
import static com.xdidian.keryhu.util.StringValidate.isEmail;


/**
 * @Description : 当用户点击"再次发送email激活促发的channel，
 * 不能和email激活的channel一起， 因为这样会导致mail激活服务器的channel混乱。"
 * 
 * 这个服务不能和手机验证码的 再次发送，合并，因为 接受的消息的终端不一样，一个是邮件，一个手机发送平台
 * @date : 2016年6月18日 下午9:07:26
 * @author : keryHu keryhu@hotmail.com
 */
@EnableBinding(ResendEmailInputChannel.class)
@Slf4j
public class ReSendEmailConsumer {

  @Autowired
  private EmailHtmlSender mailSender;

  
  @StreamListener(ResendEmailInputChannel.NAME)
  public void receive(AccountActivatedDto dto) {

    // 通过zuul 中转到 user-account，这样前台 点击 url，后台user-account使用 get 方法来处理

    if(isEmail(dto.getId())){
      Context ctx = new Context();
      
      ctx.setVariable("dto", dto);
      ctx.setVariable("token", dto.getToken());


      //发送邮件
      
      mailSender.send(dto.getId(), "新地点帐号激活", "emailActivated", ctx);
      log.info("因为用户点击' 再次发送邮件 '，再次给 {} 发送验证码邮件 ");
    }
    else {
      log.info("接受到的需要被激活的对象 不是email激活。");
    }
    


  }
}
