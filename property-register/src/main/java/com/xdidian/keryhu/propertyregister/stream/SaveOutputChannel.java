/**  
* @Title: PropertyRegisterOutputChannel.java
* @Package com.xdidian.keryhu.propertyregister.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月4日 下午5:20:32
* @version V1.0  
*/ 
package com.xdidian.keryhu.propertyregister.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


/**
 * Description : 物业公司注册后，保存注册信息的发送message 的channel
 * Date : 2016年06月18日 上午11:15
 * Author : keryHu keryhu@hotmail.com
 */
public interface SaveOutputChannel {
	
   String NAME="propertyRegisterSaveOutputChannel";
	
	@Output(NAME)
	MessageChannel saveOutput();

}
