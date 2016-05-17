/**  
* @Title: EmailActivatedConsumer.java
* @Package com.xdidian.keryhu.mailServer.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午5:38:02
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.stream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import com.xdidian.keryhu.domain.EmailActivatedDto;
import com.xdidian.keryhu.emailActivate.service.TokenService;

import lombok.extern.slf4j.Slf4j;

import com.xdidian.keryhu.emailActivate.service.ConverterUtil;



/**
* @ClassName: EmailActivatedConsumer
* @Description: TODO(email激活service 接收到传递过来的，需要被激活的数据后的处理办法，
* 先进行类型转换，然后设置默认的发送次数为0，再保存数据库)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午5:38:02
*/
@EnableBinding(EmailActivatedInputChannel.class)
@Slf4j
public class EmailActivatedConsumer {
	
	@Autowired
	private ConverterUtil converterUtil;
	
	@Autowired
	private TokenService activatedTokenService;
	
	@StreamListener(EmailActivatedInputChannel.NAME)
	public void receive(EmailActivatedDto dto){
		
		activatedTokenService
		   .save(converterUtil.EmailActivatedDtoToActivatedToken.apply(dto));
		log.info("email激活服务器，接受到刚刚注册的 dto is ： {}",dto);
		
	}
}
