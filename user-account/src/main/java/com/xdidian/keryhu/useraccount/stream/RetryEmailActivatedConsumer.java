/**  
* @Title: RetryEmailActivatedConsumer.java
* @Package com.xdidian.keryhu.useraccount.stream
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午2:44:35
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.stream;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import com.xdidian.keryhu.useraccount.service.EmailActivatedService;

import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: RetryEmailActivatedConsumer
* @Description: TODO(用来接受，邮件激活的 消息监听器)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午2:44:35
*/
@EnableBinding(RetryEmailActivatedInputChannel.class)
@Slf4j
public class RetryEmailActivatedConsumer {
	
	@Autowired
	private EmailActivatedService emailActivatedService;
	
	
	@StreamListener(RetryEmailActivatedInputChannel.NAME)
	public void retry(String email){
		Assert.assertNotNull(email,"email 不能为空");
		emailActivatedService.retryEmailActivated(email);
	}
	

}
