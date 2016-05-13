/**  
* @Title: EmailActivatedExpiredOutputChannel.java
* @Package com.xdidian.keryhu.authserver.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月6日 下午9:19:34
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
* @ClassName: EmailActivatedExpiredOutputChannel
* @Description: TODO(删除user数据的channel，传递的是email，userAccount会根据类型判断是email，phone，uuid，哪种形式)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月6日 下午9:19:34
*/
public interface RemoveUserOutputChannel {
	
	//此channel的值和 application bindings下面的值一致
		String NAME="removeUserOutputChannel";
		
		@Output(NAME)
		MessageChannel removeUser();

}
