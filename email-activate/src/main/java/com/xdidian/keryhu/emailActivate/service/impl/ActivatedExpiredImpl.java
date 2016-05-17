/**  
* @Title: ActivatedExpiredImpl.java
* @Package com.xdidian.keryhu.emailActivate.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月17日 下午3:50:50
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xdidian.keryhu.domain.Role;
import com.xdidian.keryhu.emailActivate.client.UserClient;
import com.xdidian.keryhu.emailActivate.repository.ActivatedTokenRepository;
import com.xdidian.keryhu.emailActivate.service.ActivatedExpired;
import com.xdidian.keryhu.emailActivate.service.RedirectService;
import com.xdidian.keryhu.emailActivate.stream.RemoveUserProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: ActivatedExpiredImpl
* @Description: TODO(当email激活时，“重新发送激活邮件”，“再次注册”时，email激活时间已经过期，执行的方法)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月17日 下午3:50:50
*/
@Component("activatedExpired")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ActivatedExpiredImpl implements ActivatedExpired {
	
    private final ActivatedTokenRepository repository;
    private final RemoveUserProducer removeUserProducer;
    private final UserClient userClient;
    private final RedirectService redicerectService;

	/**
	* <p>Title: executeExpired</p>
	* <p>Description: 该方法执行主要分为3点
	* 1 导航到注册页面，由RedicerectService.redirectRegisterPage，完成
	* 2 删除此email所在的 ActivatedToken 数据库记录，由repository 完成
	* 3 spring cloud stream发送删除此email所在的 user 数据库记录的message出去</p>
	* @param email
	* @param attr
	* @return
	* @see com.xdidian.keryhu.emailActivate.service.ActivatedExpired#executeExpired(java.lang.String, org.springframework.web.servlet.mvc.support.RedirectAttributes)
	*/ 
	@Override
	public ModelAndView executeExpired(String email, RedirectAttributes attr) {
		// TODO Auto-generated method stub
		List<Role> roles=userClient.findRolesByLoginName(email);	
		removeUserProducer.send(email);
		repository.deleteByEmail(email);
		log.info("ActivatedToken 删除 email 为 {} 的数据库记录 。",email);
		return redicerectService.redirectRegisterPage(email, attr, roles);
	}

}
