package com.xdidian.keryhu.account_activate.stream;

import com.xdidian.keryhu.domain.AccountActivatedDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;



/**
 * @Description : 当用户点击再次发送email激活账户的时候，促发此message的具体执行
 * 这个再次发送 验证码的 接口，邮件再次发送 和 手机平台 再次发送需要分开，因为接收端是不一样的
 * @date : 2016年6月18日 下午9:03:15
 * @author : keryHu keryhu@hotmail.com
 */
@Component
@EnableBinding(ResendEmailOutputChannel.class)
@Slf4j
public class ResendEmailProducer {

  @Autowired
  private ResendEmailOutputChannel channel;

  public void send(AccountActivatedDto dto) {

    boolean result = channel.resend().send(MessageBuilder.withPayload(dto).build());

    Assert.isTrue(result, "用户用户点击'再次发送邮件激活的需求，再次发送的请求message发出失败！");

    log.info("用户用户点击'再次发送邮件激活的需求，再次发送的请求message发出！发送email token is： ' {} ",dto.getToken());
  }


}
