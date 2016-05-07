/**  
* @Title: RetryEmailActivatedInputChannel.java
* @Package com.xdidian.keryhu.useraccount.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午2:42:11
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
* @ClassName: RetryEmailActivatedInputChannel
* @Description: TODO(邮件激活再次发送，专门用来次消息的message channel)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午2:42:11
*/
public interface RetryEmailActivatedInputChannel {
	
	//此channel的值和 application bindings下面的值一致
	String NAME="retryEmailActivatedInputChannel";
	
	@Output(NAME)
	MessageChannel input();

}
