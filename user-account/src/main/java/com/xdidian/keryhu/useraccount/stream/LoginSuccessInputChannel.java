/**
 * @Title: LoginSuccessInputChannel.java
 * @Package com.xdidian.keryhu.useraccount.stream
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月4日 下午1:51:59
 * @version V1.0
 */
package com.xdidian.keryhu.useraccount.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
 * Description : 当用户登录成功后，专门用来接受消息的channel
 * Date : 2016年06月18日 上午11:30
 * Author : keryHu keryhu@hotmail.com
 */
public interface LoginSuccessInputChannel {

    String NAME = "loginSuccessInputChannel";

    @Input(NAME)
    MessageChannel input();

}
