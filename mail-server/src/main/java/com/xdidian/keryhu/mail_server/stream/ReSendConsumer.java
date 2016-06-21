package com.xdidian.keryhu.mail_server.stream;


import com.xdidian.keryhu.domain.EmailActivatedDto;
import com.xdidian.keryhu.mail_server.domain.HostProperty;
import com.xdidian.keryhu.mail_server.mail.EmailHtmlSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.thymeleaf.context.Context;

/**
 * @Description : 当用户点击"再次发送email激活促发的channel，不能和email激活的channel一起， 因为这样会导致mail激活服务器的channel混乱。"
 * @date : 2016年6月18日 下午9:07:26
 * @author : keryHu keryhu@hotmail.com
 */
@EnableBinding(ResendInputChannel.class)
@EnableConfigurationProperties(HostProperty.class)
public class ReSendConsumer {

  @Autowired
  private EmailHtmlSender mailSender;

  @Autowired
  private HostProperty hostProperty;

  @StreamListener(ResendInputChannel.NAME)
  public void receive(EmailActivatedDto dto) {

    // 通过zuul 中转到 user-account，这样前台 点击 url，后台user-account使用 get 方法来处理

    String url = new StringBuffer(hostProperty.getHostName())
        .append(":8080/email-activate/email/emailActivatedConfirm?email=").append(dto.getEmail())
        .append("&token=").append(dto.getEmailToken()).toString();
    Context context = new Context();
    context.setVariable("dto", dto);
    context.setVariable("url", url);


    mailSender.send(dto.getEmail(), "新地点帐号激活", "emailActivated", context);


  }
}
