package com.xdidian.keryhu.authserver.client;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.xdidian.keryhu.authserver.domain.AuthUserDto;

/**
 * feign 连接失败的一个默认方法。
 * @author hushuming
 *
 */
@Component
public class UserAccountClientFallback implements UserAccountClient {
	
	@Override
	public Optional<AuthUserDto> ByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	/**
	* <p>Title: isEmailExist</p>
	* <p>Description: 如果调用UserAccountClient 对应的spring feign 网络失败，则此方法生效</p>
	* @param email
	* @return
	* @see com.xdidian.keryhu.authserver.client.UserAccountClient#isEmailExist(java.lang.String)
	*/ 
	@Override
	public boolean isEmailExist(String email) {
		// TODO Auto-generated method stub
		return  false;	
		}

	/**
	* <p>Title: isPhoneExist</p>
	* <p>Description: 如果调用UserAccountClient 对应的spring feign 网络失败，则此方法生效 </p>
	* @param phone
	* @return
	* @see com.xdidian.keryhu.authserver.client.UserAccountClient#isPhoneExist(java.lang.String)
	*/ 
	@Override
	public boolean isPhoneExist(String phone) {
		// TODO Auto-generated method stub
		return   false;
	}

}
