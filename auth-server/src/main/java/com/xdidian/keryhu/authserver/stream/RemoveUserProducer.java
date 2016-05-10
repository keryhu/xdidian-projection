/**  
* @Title: EmailActivatedExpiredProducer.java
* @Package com.xdidian.keryhu.authserver.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月6日 下午9:22:45
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.xdidian.keryhu.exception.MessageNotSendException;

import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: EmailActivatedExpiredProducer
* @Description: TODO(发送含有userId或者loginName（phone或email）的消息出去，目的为了user-account删除此user)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月6日 下午9:22:45
*/
@Component
@EnableBinding(RemoveUserOutputChannel.class)
@Slf4j
public class RemoveUserProducer {
	
	@Autowired
	private RemoveUserOutputChannel channel;
	
	/**
	 * 
	* @Title: send
	* @Description: TODO(这里发出的id，有可能是uuid，也可能是email或者phone，
	* 具体如何要靠接收方user-account来判断)
	* @param @param id    设定文件
	* @return void    返回类型
	* @throws
	 */
	public void send(String id){
		boolean result=channel.removeUser()
				              .send(MessageBuilder.withPayload(id).build());
		
		
		if(!result){
			 log.error("发送 remove user messge 失败");
	         throw new MessageNotSendException("发送 remove user messge 失败！！");
		}
	}

}
