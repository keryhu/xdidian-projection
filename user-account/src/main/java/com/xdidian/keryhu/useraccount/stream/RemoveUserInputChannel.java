package com.xdidian.keryhu.useraccount.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;


/**
 * @Description : 专门用来接受需要被删除的User的 userId的消息监听器
 * @date : 2016年6月18日 下午9:26:33
 * @author : keryHu keryhu@hotmail.com
 */
public interface RemoveUserInputChannel {

  String NAME = "removeUserInputChannel";

  @Input(NAME)
  MessageChannel input();

}
