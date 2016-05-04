/**  
* @Title: PropertyRegisterInputChannel.java
* @Package com.xdidian.keryhu.useraccount.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月4日 下午7:34:38
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
* @ClassName: PropertyRegisterInputChannel
* @Description: TODO(用户注册，接受消息的channel)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月4日 下午7:34:38
*/
public interface PropertyRegisterInputChannel {
	
	    String NAME="propertyRegisterSaveInputChannel";
		
		@Input(NAME)
		MessageChannel input();

}
