/**
 * @Title: ActivatedSuccessInputChannel.java
 * @Package com.xdidian.keryhu.useraccount.stream
 * @Description: TODO(当email成功被激活后，从email-activated发出的成功激活的email的message，此channel用来
 *专门接受这样的消息)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月4日 下午1:51:59
 * @version V1.0
 */
package com.xdidian.keryhu.useraccount.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
 * Description : 当email成功被激活后，从email-activated发出的成功激活的email的message，此channel用来
 * 专门接受这样的消息
 * Date : 2016年06月18日 上午11:30
 * Author : keryHu keryhu@hotmail.com
 */
public interface ActivatedSuccessInputChannel {

    String NAME = "emailActivatedSuccessInputChannel";

    @Input(NAME)
    MessageChannel input();

}
