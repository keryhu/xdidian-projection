/**  
* @Title: ActivatedConfirm.java
* @Package com.xdidian.keryhu.emailActivate.service.impl
* @Description: TODO(用户点击 激活邮箱的url，当验证完url是合法的，即－－
* email存在，未激活，且未过期的情况下，后续需要执行的命令,除了页面跳转 login任务)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月17日 下午6:24:52
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xdidian.keryhu.emailActivate.repository.ActivatedTokenRepository;
import com.xdidian.keryhu.emailActivate.service.ActivatedConfirm;
import com.xdidian.keryhu.emailActivate.stream.ActivatedSuccessProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: ActivatedConfirm
* @Description: TODO(用户点击 激活邮箱的url，当验证完url是合法的，即－－
* email存在，未激活，且未过期的情况下，后续需要执行的命令,除了页面跳转 login任务
* 包含email activated 删除此email所在的数据库记录
* 发送包含email的激活成功的message出去)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月17日 下午6:24:52
*/
@Component("activatedConfirm")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ActivatedConfirmImpl implements ActivatedConfirm{
	
	private final ActivatedSuccessProducer activatedSuccessProducer;
	private final ActivatedTokenRepository repository;

	/**
	* <p>Title: exceptRedirect</p>
	* <p>Description: </p>
	* @param email
	* @see com.xdidian.keryhu.emailActivate.service.ActivatedConfirm#exceptRedirect(java.lang.String)
	*/ 
	@Override
	public void exceptRedirect(String email) {
		// TODO Auto-generated method stub
		repository.deleteByEmail(email);
		log.info("已经从数据库中成功删除了email-activate，{} 所在的数据",email);
		//发送包含email激活成功的message出去，
		activatedSuccessProducer.send(email);
		
	}

}
