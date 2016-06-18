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
 * Description : 用户注册，接受消息的channel
 * Date : 2016年06月18日 上午11:31
 * Author : keryHu keryhu@hotmail.com
 */
public interface PropertyRegisterInputChannel {
	
	    String NAME="propertyRegisterSaveInputChannel";
		
		@Input(NAME)
		MessageChannel input();

}
