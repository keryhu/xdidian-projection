package com.xdidian.keryhu.mailServer.stream;


import com.xdidian.keryhu.domain.EmailActivatedDto;
import com.xdidian.keryhu.mailServer.domain.HostProperty;
import com.xdidian.keryhu.mailServer.mail.EmailHtmlSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.thymeleaf.context.Context;


/**
 * @Description : email激活所促发的应用
 * @date : 2016年6月18日 下午9:06:51
 * @author : keryHu keryhu@hotmail.com
 */
@EnableBinding(EmailActivatedInputChannel.class)
@EnableConfigurationProperties(HostProperty.class)
public class EmailActivatedConsumer {

  @Autowired
  private EmailHtmlSender mailSender;

  @Autowired
  private HostProperty hostProperty;

  @StreamListener(EmailActivatedInputChannel.NAME)
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
