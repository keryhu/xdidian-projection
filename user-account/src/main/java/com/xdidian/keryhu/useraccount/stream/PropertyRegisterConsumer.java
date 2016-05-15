/**  
* @Title: PropertyRegisterConsumer.java
* @Package com.xdidian.keryhu.useraccount.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月4日 下午7:38:26
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.xdidian.keryhu.domain.PropertyRegisterDto;
import com.xdidian.keryhu.useraccount.domain.User;
import com.xdidian.keryhu.useraccount.service.ConverterUtil;
import com.xdidian.keryhu.useraccount.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: PropertyRegisterConsumer
* @Description: TODO（(物业公司用户注册后，接受具体注册信息的message的方法)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月4日 下午7:38:26
*/
@EnableBinding(PropertyRegisterInputChannel.class)
@Slf4j
public class PropertyRegisterConsumer {
	
	@Autowired
	private  ConverterUtil converterUtil;
	
	@Autowired
	private UserService userService;
	
	@StreamListener(PropertyRegisterInputChannel.NAME)
	public void saveProperty(PropertyRegisterDto dto){
			
			log.info("user-account 已经收到了物业公司的注册信息，具体信息为 ： "+dto);

			  User user=converterUtil.propertyRegisterDtoToUser.apply(dto);
			  //保存数据库
			  userService.save(user);
			
		
	}

}
