/**  
* @Title: RemoveUserConsumer.java
* @Package com.xdidian.keryhu.useraccount.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月6日 下午9:42:16
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.amazonaws.util.StringUtils;
import com.xdidian.keryhu.useraccount.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: RemoveUserConsumer
* @Description: TODO(接受到需要删除User消息时，具体的实施方法)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月6日 下午9:42:16
*/
@EnableBinding(RemoveUserInputChannel.class)
@Slf4j
public class RemoveUserConsumer {
	
	@Autowired
	private UserService userService;
	
	@StreamListener(RemoveUserInputChannel.NAME)
	public void removeUser(String id){
		if(!StringUtils.isNullOrEmpty(id)){
			userService.deleteById(id).ifPresent(e->log.info("user-account 成功删除了此user ： "+e));
		}
	}

}
