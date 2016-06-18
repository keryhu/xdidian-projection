package com.xdidian.keryhu.emailActivate.stream;


import com.xdidian.keryhu.domain.EmailActivatedDto;
import com.xdidian.keryhu.emailActivate.service.ConverterUtil;
import com.xdidian.keryhu.emailActivate.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;



/**
 * @Description : email激活service 接收到传递过来的，需要被激活的数据后的处理办法， 先进行类型转换，然后设置默认的发送次数为0，再保存数据库
 * @date : 2016年6月18日 下午9:01:42
 * @author : keryHu keryhu@hotmail.com
 */
@EnableBinding(EmailActivatedInputChannel.class)
@Slf4j
public class EmailActivatedConsumer {

  @Autowired
  private ConverterUtil converterUtil;

  @Autowired
  private TokenService activatedTokenService;

  @StreamListener(EmailActivatedInputChannel.NAME)
  public void receive(EmailActivatedDto dto) {

    activatedTokenService.save(converterUtil.EmailActivatedDtoToActivatedToken.apply(dto));
    log.info("email激活服务器，接受到刚刚注册的 dto is ： {}", dto);

  }
}
