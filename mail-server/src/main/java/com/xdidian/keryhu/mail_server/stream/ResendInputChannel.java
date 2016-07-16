package com.xdidian.keryhu.mail_server.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;



/**
 * @Description : 当用户点击"再次发送email激活促发的channel，不能和email激活的channel一起， 因为这样会导致mail激活服务器的channel混乱。"
 * @date : 2016年6月18日 下午9:07:50
 * @author : keryHu keryhu@hotmail.com
 */
public interface ResendInputChannel {

  String NAME = "resendInputChannel";

  @Input(NAME)
  SubscribableChannel input();

}
