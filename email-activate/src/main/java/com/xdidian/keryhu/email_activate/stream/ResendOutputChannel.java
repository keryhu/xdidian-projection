package com.xdidian.keryhu.email_activate.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;



/**
 * @Description : TODO(用一句话描述该文件做什么)
 * @date : 2016年6月18日 下午9:02:58
 * @author : keryHu keryhu@hotmail.com
 */
public interface ResendOutputChannel {

  // 此channel的值和 application bindings下面的值一致
  String NAME = "resendOutputChannel";

  @Output(NAME)
  MessageChannel resend();

}
