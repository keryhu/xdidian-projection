/**
 * @Title: EmailActivatedInputChannel.java
 * @Package com.xdidian.keryhu.mailServer.stream
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月7日 下午5:21:48
 * @version V1.0
 */
package com.xdidian.keryhu.mailServer.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
 * Description : email激活接受的消息channel
 * Date : 2016年06月18日 上午10:34
 * Author : keryHu keryhu@hotmail.com
 */
public interface EmailActivatedInputChannel {

    String NAME = "emailActivatedInputChannel";

    @Input(NAME)
    MessageChannel input();

}
