package com.xdidian.keryhu.user_account.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
 * @Description : 当用户登录成功后，专门用来接受消息的channel
 * @date : 2016年6月18日 下午9:25:21
 * @author : keryHu keryhu@hotmail.com
 */
public interface LoginSuccessInputChannel {

  String NAME = "loginSuccessInputChannel";

  @Input(NAME)
  MessageChannel input();

}
