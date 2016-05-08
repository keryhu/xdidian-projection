/**  
* @Title: EmailActivatedServiceImpl.java
* @Package com.xdidian.keryhu.useraccount.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午2:50:47
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xdidian.keryhu.useraccount.domain.EmailActivatedProperties;
import com.xdidian.keryhu.useraccount.exception.EmailActivatedCodeNotFoundException;
import com.xdidian.keryhu.useraccount.exception.EmailActivatedExpiredException;
import com.xdidian.keryhu.useraccount.exception.EmailActivatedSentTimesOverException;
import com.xdidian.keryhu.useraccount.exception.EmailNotFoundException;
import com.xdidian.keryhu.useraccount.repository.UserRepository;
import com.xdidian.keryhu.useraccount.stream.EmailActivatedProducer;

import lombok.RequiredArgsConstructor;

/**
* @ClassName: EmailActivatedServiceImpl
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午2:50:47
*/
@Component("emailActivatedService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailActivatedServiceImpl implements EmailActivatedService {
	
	private final UserRepository repository;

	private final EmailActivatedProperties emailActivatedProperties;
	
	private final RandomUtil randomUtil;
	
	private final ConverterUtil converterUtil;
	
	private final EmailActivatedProducer producer;

	
    /**	
	 * Description: 查看当前用户的email，邮件激活发送的次数，有没有超过最大的限定次数，默认是返回true（就是没有超过）
	 * 如果email不存在于数据库，则返回默认值true
	 * @param email
	 * @return
	 * @see com.xdidian.keryhu.useraccount.service.UserService#emailActivateSendTimesNotOver(java.lang.String)
	 */
	@Override
	public boolean emailActivateSentTimesNotOver(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email)
				.map(e -> e.getEmailActivatedSentTimes() < emailActivatedProperties.getMaxSendTimes()).orElse(true);

	}


	/**
	 * Title: emailActivatedStatus
	 * Description: 查询当前email所在的user 数据库账户，email 有没有被激活
	 * @param email
	 * @return 如果email 不存在，返回默认fasle
	 * @see com.xdidian.keryhu.useraccount.service.UserService#findEmailActivated(java.lang.String)
	 */
	@Override
	public boolean emailActivatedStatus(String email) {
		// TODO Auto-generated method stub

		return repository.findByEmail(email).map(e -> e.isEmailActivatedStatus()).orElse(false);
	}

	/**
	 * Title: emailActivatedExpired
	 * Description: 如果email 存在的情况下，查看注册时间 是否 已经大于 最大的允许时间
	 * 默认的执行此方法的前提是： email存在，且email的 emailActivated＝false ，在这个前提下，才会执行此方法
	 * @param email
	 * @return 
	 * @see com.xdidian.keryhu.useraccount.service.UserService#emailActivatedCodeExpired(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean emailActivatedExpired(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email)
				         .filter(e->!e.isEmailActivatedStatus())
		                 .map(e->LocalDateTime.now().isAfter(e.getRegisterTime()
		                		 .plusHours(emailActivatedProperties.getExpiredTime())))
		                 .orElseThrow(()->new EmailNotFoundException("您需要查询的email不存在于数据库"));	
	}

     /**	
	 * Title: emailActivatedCodeExist
	 * Description:如果email不存在，返回false，如果 查询的对象，不存在emailActivatedCode，则返回false
	 * 否则判断 参数emailActivatedCode 是否和 查询的结果 emailActivatedCode 相等。
	 * @param email
	 * @param emailActivatedCode
	 * @return
	 * @see com.xdidian.keryhu.useraccount.service.UserService#emailActivatedCodeExist(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean emailActivatedCodeExist(String email, String emailActivatedCode) {
		// TODO Auto-generated method stub
				return repository.findByEmail(email).map(e -> {
					if (e.getEmailActivatedCode() != null) {
						return e.getEmailActivatedCode().equals(emailActivatedCode);
					}
					return false;

				}).orElse(false);
	}


	/**
	* <p>Title: retryEmailActivated</p>
	* <p>Description:当user-account接受到再次发送email激活的消息时，所需要做的工作：
	* 创建一个新的email 激活验证码
	* 首先，查询email所在的user，
	* 第三步，将验证码更新到此user中的emailActivatedCode 参数中去。
	* 第四步，atomicInter 将user 的 email激活发送次数 增加1 
	* 第五步 更新数据库
	* 发送消息，包含email ， email激活验证码的 message 出去，希望送给 mail-server </p>
	* @param email
	* @see com.xdidian.keryhu.useraccount.service.EmailActivatedService#retryEmailActivated(java.lang.String)
	*/ 
	@Override
	public void retryEmailActivated(String email) {
		// TODO Auto-generated method stub
		
		repository.findByEmail(email).ifPresent(e->{
			e.setEmailActivatedCode(randomUtil.create(6));
			AtomicInteger atomic=new AtomicInteger(e.getEmailActivatedSentTimes());
			e.setEmailActivatedSentTimes(atomic.incrementAndGet());
			repository.save(e);
			//发送email激活的消息出去。
			producer.send(converterUtil.userToEmailActivatedDto.apply(e));
			
		});
	}


	/**
	* <p>Title: validateEmailActivatedAndSave</p>
	* <p>Description: 验证email激活，发送到邮箱的url，用户点击url，后台处理get 的url，查看是否此url
	* 能够通过验证，如果通过验证，那么就更新数据库，否则报错</p>
	* @param email
	* @param emailActivatedCode
	* @see com.xdidian.keryhu.useraccount.service.EmailActivatedService#validateEmailActivatedClicked(java.lang.String, java.lang.String)
	*/ 
	@Override
	public void validateEmailActivatedAndSave(String email, String emailActivatedCode) {
		// TODO Auto-generated method stub
		
	  repository.findByEmail(email)
		          .orElseThrow(()->new EmailNotFoundException("您的email还未注册！"));
		
		
	  if(!emailActivatedCodeExist(email,emailActivatedCode)){
   		  throw new EmailActivatedCodeNotFoundException("您的email激活码不存在，不能验证。");
   	  } 
   	  else if(emailActivatedExpired(email)){
   		  throw new EmailActivatedExpiredException("您的email激活时间已过期，请重新注册");
   	  }
   	  else if(!emailActivateSentTimesNotOver(email)){
   		  throw new EmailActivatedSentTimesOverException("您的email激活次数过于频繁，请稍后再试！");
   	  }
	  
	  repository.findByEmail(email)
	            .ifPresent(e->{ 
	            	e.setEmailActivatedStatus(true);
	            	repository.save(e);     
	            });			
	}

}
