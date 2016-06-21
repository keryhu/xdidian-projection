package com.xdidian.keryhu.property_signup.stream;

import com.xdidian.keryhu.domain.PropertyRegisterDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @Description : 物业公司用户注册完后，发送此消息出去
 * @date : 2016年6月18日 下午9:18:52
 * @author : keryHu keryhu@hotmail.com
 */
@Component
@EnableBinding(SaveOutputChannel.class)
@Slf4j
public class SaveProducer {

  @Autowired
  private SaveOutputChannel channel;

  /**
   * 当物业公司注册完，直接发送物业公司的message 出去。
   */
  public void send(PropertyRegisterDto dto) {

    boolean result = channel.saveOutput().send(MessageBuilder.withPayload(dto).build());

    Assert.isTrue(result, "物业公司注册完，发送具体消息失败！");
    log.info("物业公司注册成功，现在发送message，给user-account ! ");

  }

}
