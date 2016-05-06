/**  
* @Title: RemoveUserInputChannel.java
* @Package com.xdidian.keryhu.useraccount.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月6日 下午9:40:48
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
* @ClassName: RemoveUserInputChannel
* @Description: TODO(专门用来接受需要被删除的User的 userId的消息监听器)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月6日 下午9:40:48
*/
public interface RemoveUserInputChannel {
	
	String NAME="removeUserInputChannel";
	
	@Input(NAME)
	MessageChannel input();

}
