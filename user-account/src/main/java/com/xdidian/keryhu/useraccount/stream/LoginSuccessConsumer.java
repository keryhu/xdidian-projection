/**  
* @Title: SinkMoudle.java
* @Package com.xdidian.keryhu.useraccount.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月3日 下午9:29:17
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.stream;


import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import com.xdidian.keryhu.useraccount.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: SinkMoudle
* @Description: TODO(当用户登录成功后，auth－server会发送消息，此接口用来接受消息
* 注意这个clas ，不能使用 lombok.RequiredArgsConstructor  )
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月3日 下午9:29:17
*/

@EnableBinding(LoginSuccessInputChannel.class)
@Slf4j
public class LoginSuccessConsumer {
	
	@Autowired
	private  UserRepository repository;
	
	@StreamListener(LoginSuccessInputChannel.NAME)
	public void ReceiveLoginSuccessMessage(String userId){
		log.info("接受到 auth-server 发送的登录成功用户的userId is  : " + userId);
		//通过自定义的StringToBooleanConverter 类型转换，将发送过来的 String 对象 转为了 Boolean 对象
		
		repository.findById(userId).ifPresent(e->{
			e.setLastLoginTime(LocalDateTime.now());
			repository.save(e);
			log.info("已经更新了用户登录时间！！");
		});
		
		
	}

}
