package com.xdidian.keryhu.account_activate.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;



/**
 * @Description : TODO(这个再次发送 验证码的 接口，邮件再次发送 和 手机平台 再次发送需要分开，因为接收端是不一样的。)
 * @date : 2016年6月18日 下午9:02:58
 * @author : keryHu keryhu@hotmail.com
 */
public interface ResendEmailOutputChannel {

  // 此channel的值和 application bindings下面的值一致
  String NAME = "resendEmailOutputChannel";

  @Output(NAME)
  MessageChannel resend();

}
