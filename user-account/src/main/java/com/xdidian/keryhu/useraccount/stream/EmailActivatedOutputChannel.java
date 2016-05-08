/**  
* @Title: EmailActivatedOutputChannel.java
* @Package com.xdidian.keryhu.useraccount.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午4:31:37
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
* @ClassName: EmailActivatedOutputChannel
* @Description: TODO(email 激活的 发出消息的 messageChannel)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午4:31:37
*/
public interface EmailActivatedOutputChannel {
	
	    //此channel的值和 application bindings下面的值一致
		String NAME="emailActivatedOutputChannel";
		
		@Output(NAME)
		MessageChannel emailActivatedOutput();

}
