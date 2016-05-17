/**  
* @Title: ActivatedTokenServiceImpl.java
* @Package com.xdidian.keryhu.emailActivate.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月11日 下午9:50:17
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.xdidian.keryhu.emailActivate.domain.ActivatedToken;
import com.xdidian.keryhu.emailActivate.repository.ActivatedTokenRepository;
import com.xdidian.keryhu.emailActivate.service.TokenService;
import lombok.RequiredArgsConstructor;

/**
* @ClassName: ActivatedTokenServiceImpl
* @Description: TODO(继承自 ActivatedTokenService 的接口服务的具体实现。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月11日 下午9:50:17
*/
@Component("tokenService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenServiceImpl  implements TokenService{
	
	private final ActivatedTokenRepository repository;
	

	/**
	* <p>Title: save</p>
	* <p>Description:  带有事物回滚的 ActivatedToken 保存到数据库</p>
	* @param activatedToken
	* @return
	* @see com.xdidian.keryhu.emailActivate.service.TokenService#save(com.xdidian.keryhu.emailActivate.domain.ActivatedToken)
	*/ 
	@Override
	@Transactional
	public ActivatedToken save(ActivatedToken activatedToken) {
		// TODO Auto-generated method stub
		return repository.save(activatedToken);
	}

}
