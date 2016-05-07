/**  
* @Title: RetryEmailActivated.java
* @Package com.xdidian.keryhu.authserver.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午2:08:57
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
* @ClassName: RetryEmailActivated
* @Description: TODO(当login 页面，因为客户email未激活的情况下，且激活次数未超过限制时，
* 客户需要重新发送email激活邮件服务的channel)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午2:08:57
*/
public interface RetryEmailActivatedOutputChannel {
	
	//此channel的值和 application bindings下面的值一致
			String NAME="retryEmailActivatedOutputChannel";
			
			@Output(NAME)
			MessageChannel retry();

}
