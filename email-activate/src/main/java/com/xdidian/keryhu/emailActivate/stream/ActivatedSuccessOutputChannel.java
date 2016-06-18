/**
 * @Title: EmailActivatedExpiredOutputChannel.java
 * @Package com.xdidian.keryhu.authserver.stream
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月6日 下午9:19:34
 * @version V1.0
 */
package com.xdidian.keryhu.emailActivate.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Description : email激活成功后，发送出去包含email的message
 * Date : 2016年06月18日 上午9:30
 * Author : keryHu keryhu@hotmail.com
 */
public interface ActivatedSuccessOutputChannel {

    //此channel的值和 application bindings下面的值一致
    String NAME = "emailActivatedSuccessOutputChannel";

    @Output(NAME)
    MessageChannel success();

}
