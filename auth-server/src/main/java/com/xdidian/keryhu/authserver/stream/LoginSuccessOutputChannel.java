/**  
* @Title: LoginSuccessOutputChannel.java
* @Package com.xdidian.keryhu.authserver.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月4日 下午1:37:41
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
* @ClassName: LoginSuccessOutputChannel
* @Description: TODO(设置的loginSuccess 发送消息出去的专用 channel)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月4日 下午1:37:41
*/
public interface LoginSuccessOutputChannel {
	
	String NAME="loginSuccessOutputChannel";
	
	@Output(NAME)
	MessageChannel loginSuccessOutput();

}
