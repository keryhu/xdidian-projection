/**  
* @Title: ResendOutputChannel.java
* @Package com.xdidian.keryhu.useraccount.stream
* @Description: TODO(当再次发送邮件激活的时候channel)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午4:31:37
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
* @ClassName: EmailActivatedOutputChannel
* @Description: TODO(email 激活的 发出消息的 messageChannel)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午4:31:37
*/
public interface ResendOutputChannel {
	
	    //此channel的值和 application bindings下面的值一致
		String NAME="resendOutputChannel";
		
		@Output(NAME)
		MessageChannel resend();

}
