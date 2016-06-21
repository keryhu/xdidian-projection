package com.xdidian.keryhu.email_activate.stream;

import com.xdidian.keryhu.domain.EmailActivatedDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;



/**
 * @Description : 当用户点击再次发送email激活账户的时候，促发此message的具体执行
 * @date : 2016年6月18日 下午9:03:15
 * @author : keryHu keryhu@hotmail.com
 */
@Component
@EnableBinding(ResendOutputChannel.class)
@Slf4j
public class ResendProducer {

  @Autowired
  private ResendOutputChannel channel;

  public void send(EmailActivatedDto dto) {

    boolean result = channel.resend().send(MessageBuilder.withPayload(dto).build());

    Assert.isTrue(result, "用户用户点击'再次发送邮件激活的需求，再次发送的请求message发出失败！");

    log.info("用户用户点击'再次发送邮件激活的需求，再次发送的请求message发出！'");
  }


}
