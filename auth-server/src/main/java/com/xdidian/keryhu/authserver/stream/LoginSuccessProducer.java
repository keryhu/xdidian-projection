/**  
* @Title: SendSource.java
* @Package com.xdidian.keryhu.authserver.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月3日 下午9:20:51
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.xdidian.keryhu.authserver.exception.MessageNotSendException;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: SendSource
* @Description: TODO(当登录成功后，通过spring cloud stream 发送userId出去。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月3日 下午9:20:51
*/
@Component
@EnableBinding(LoginSuccessOutputChannel.class)
@Slf4j
public class LoginSuccessProducer {
	
	
	@Autowired
	private LoginSuccessOutputChannel channel;
	
	
	/**
	 * 
	* @Title: send
	* @Description: TODO(当用户登录成功后，直接发送当前用户的userId，如果对方接受到是uuid，则表示登录成功了，否则表示登录失败)
	* @param     设定文件
	* @return void    返回类型
	* @throws
	* 
	*
	 */
	public void  send(String userId){
		//登录成功后，发送成功的信号
		
		boolean result = channel.loginSuccessOutput()
				                .send(MessageBuilder.withPayload(userId).build());
		 if (!result) {
			    log.error("服务器发送登录成功的userId消息失败！");
	            throw new MessageNotSendException("服务器发送登录成功的userId消息失败！");
	        }
	}

}
