/**  
* @Title: EmailActivatedInputChannel.java
* @Package com.xdidian.keryhu.mailServer.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午5:21:48
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
* @ClassName: EmailActivatedInputChannel
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午5:21:48
*/
public interface EmailActivatedInputChannel {
	
    String NAME="emailActivatedInputChannel";
	
	@Input(NAME)
	MessageChannel input();

}
