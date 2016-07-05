package com.xdidian.keryhu.email_activate.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;



/**
 * @Description : 当email被用户点击url成功激活后，发送这个message通知，user-account接受到通知后
 *              更改email所在的user数据库的emailStatus为truer
 * @date : 2016年6月18日 下午9:01:22
 * @author : keryHu keryhu@hotmail.com
 */
@Component
@EnableBinding(ActivatedSuccessOutputChannel.class)
@Slf4j
public class ActivatedSuccessProducer {

  @Autowired
  private ActivatedSuccessOutputChannel channel;

  /**
   * 当email被用户点击url成功激活后，发送这个message通知，user-account接受到通知后 更改email所在的user数据库的emailStatus为true
   */
  public void send(String email) {
    boolean result = channel.success().send(MessageBuilder.withPayload(email).build());

    Assert.isTrue(result, "发送激活成功的email message出去失败！");
    log.info("email {} 激活成功，现在发送通知出去！", email);

  }

}