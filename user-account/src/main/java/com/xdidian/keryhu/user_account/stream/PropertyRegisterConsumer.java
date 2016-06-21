package com.xdidian.keryhu.user_account.stream;

import com.xdidian.keryhu.domain.PropertyRegisterDto;
import com.xdidian.keryhu.user_account.domain.User;
import com.xdidian.keryhu.user_account.service.ConverterUtil;
import com.xdidian.keryhu.user_account.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @Description : 物业公司用户注册后，接受具体注册信息的message的方法
 * @date : 2016年6月18日 下午9:25:40
 * @author : keryHu keryhu@hotmail.com
 */
@EnableBinding(PropertyRegisterInputChannel.class)
@Slf4j
public class PropertyRegisterConsumer {

  @Autowired
  private ConverterUtil converterUtil;

  @Autowired
  private UserService userService;

  @StreamListener(PropertyRegisterInputChannel.NAME)
  public void saveProperty(PropertyRegisterDto dto) {

    log.info("user-account 已经收到了物业公司的注册信息，具体信息为 ： " + dto);

    User user = converterUtil.propertyRegisterDtoToUser.apply(dto);
    // 保存数据库
    userService.save(user);


  }

}
