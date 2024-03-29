package com.xdidian.keryhu.user_account.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;


/**
 * @Description : 当email或phone成功被激活后，
 * 从account-activated发出的成功激活的email或者phone的message，此channel用来 专门接受这样的消息
 * @date : 2016年6月18日 下午9:24:04
 * @author : keryHu keryhu@hotmail.com
 */
public interface ActivatedSuccessInputChannel {

  String NAME = "activatedSuccessInputChannel";

  @Input(NAME)
  SubscribableChannel input();

}
