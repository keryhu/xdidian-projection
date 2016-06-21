package com.xdidian.keryhu.email_activate.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


/**
 * @Description : email激活成功后，发送出去包含email的message
 * @date : 2016年6月18日 下午9:01:03
 * @author : keryHu keryhu@hotmail.com
 */
public interface ActivatedSuccessOutputChannel {

    //此channel的值和 application bindings下面的值一致
    String NAME = "emailActivatedSuccessOutputChannel";

    @Output(NAME)
    MessageChannel success();

}
