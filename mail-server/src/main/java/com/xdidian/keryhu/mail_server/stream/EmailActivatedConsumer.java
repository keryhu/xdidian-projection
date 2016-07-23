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
 * @Description : email激活所促发的应用
 * @date : 2016年6月18日 下午9:06:51
 * @author : keryHu keryhu@hotmail.com
 */
@EnableBinding(EmailActivatedInputChannel.class)
@Slf4j
public class EmailActivatedConsumer {

  @Autowired
  private EmailHtmlSender mailSender;


  @StreamListener(EmailActivatedInputChannel.NAME)
  public void receive(AccountActivatedDto dto) {

    // 通过zuul 中转到 user-account，这样前台 点击 url，后台user-account使用 get 方法来处理

    if(isEmail(dto.getId())){
      final Context ctx = new Context();

      ctx.setVariable("dto", dto);    //必须保留，前台需要读取过期时间
      ctx.setVariable("token", dto.getToken());
      ctx.setVariable("email", dto.getId());

      mailSender.send(dto.getId(), "新地点帐号激活", "emailActivated", ctx);
    }
    
    else {
      log.info("接受到的需要被激活的对象 不是email激活。");
    }
    


  }
}
