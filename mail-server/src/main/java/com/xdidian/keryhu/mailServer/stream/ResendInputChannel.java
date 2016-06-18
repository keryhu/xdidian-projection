/**
 * @Title: ResendInputChannel.java
 * @Package com.xdidian.keryhu.mailServer.stream
 * @Description: TODO(当用户点击"再次发送email激活促发的channel，不能和email激活的channel一起，
 * 因为这样会导致mail激活服务器的channel混乱。")
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月7日 下午5:21:48
 * @version V1.0
 */
package com.xdidian.keryhu.mailServer.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;


/**
 * Description : 当用户点击"再次发送email激活促发的channel，不能和email激活的channel一起，
 * 因为这样会导致mail激活服务器的channel混乱。"
 * Date : 2016年06月18日 上午10:35
 * Author : keryHu keryhu@hotmail.com
 */
public interface ResendInputChannel {

    String NAME = "resendInputChannel";

    @Input(NAME)
    MessageChannel input();

}
