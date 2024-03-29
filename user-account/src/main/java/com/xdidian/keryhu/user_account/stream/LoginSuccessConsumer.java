package com.xdidian.keryhu.user_account.stream;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.xdidian.keryhu.user_account.service.UserService;

import java.time.LocalDateTime;

/**
 * @Description : 当用户登录成功后，auth－server会发送消息，此接口用来接受消息 注意这个class ，不能使用 lombok.RequiredArgsConstructor
 * 
 * @date : 2016年6月18日 下午9:24:58
 * @author : keryHu keryhu@hotmail.com
 */
@EnableBinding(LoginSuccessInputChannel.class)
@Slf4j
public class LoginSuccessConsumer {

  @Autowired
  private UserService userService;

  //这里传递进来的id，可以是phone，或email还有可能是id，为了更大的兼容性，这里使用，任意一种。所以使用userService.findById
  @StreamListener(LoginSuccessInputChannel.NAME)
  public void ReceiveLoginSuccessMessage(String userId) {
    log.info("接受到 auth-server 发送的登录成功用户的userId is  : " + userId);
    // 通过自定义的StringToBooleanConverter 类型转换，将发送过来的 String 对象 转为了 Boolean 对象

    userService.findById(userId).ifPresent(e -> {
      e.setLastLoginTime(LocalDateTime.now());
      userService.save(e);
      log.info("已经更新了用户登录时间！！");
    });
    


  }

}
