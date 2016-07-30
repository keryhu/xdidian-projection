package com.xdidian.keryhu.account_activate.stream;


import com.xdidian.keryhu.account_activate.service.ConverterUtil;
import com.xdidian.keryhu.account_activate.service.TokenService;
import com.xdidian.keryhu.domain.AccountActivatedDto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;



/**
 * @Description : email激活service 接收到传递过来的，需要被激活的数据后的处理办法， 先进行类型转换，
 * 然后设置默认的发送次数为0，设置resend的冷却时间，再保存数据库
 * @date : 2016年6月18日 下午9:01:42
 * @author : keryHu keryhu@hotmail.com
 */
@EnableBinding(EmailActivatedInputChannel.class)
@Slf4j
public class EmailActivatedConsumer {

  @Autowired
  private ConverterUtil converterUtil;

  @Autowired
  private TokenService tokenService;

  @StreamListener(EmailActivatedInputChannel.NAME)
  public void receive(AccountActivatedDto dto) {

    tokenService.saveEmailActivatedToken(converterUtil.AccountActivatedDtoToEmailActivatedToken.apply(dto));
    log.info("email激活服务器，接受到刚刚注册的 dto is ： {}", dto);

  }
}
