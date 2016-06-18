package com.xdidian.keryhu.useraccount.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
 * @Description : 用户注册，接受消息的channel
 * @date : 2016年6月18日 下午9:25:58
 * @author : keryHu keryhu@hotmail.com
 */
public interface PropertyRegisterInputChannel {

  String NAME = "propertyRegisterSaveInputChannel";

  @Input(NAME)
  MessageChannel input();

}
