package com.xdidian.keryhu.authserver.client;



import org.springframework.stereotype.Component;

import com.xdidian.keryhu.authserver.domain.AuthUserDto;

/**
 * feign 连接失败的一个默认方法。
 * @author hushuming
 *
 */
@Component
public class UserClientFallback implements UserClient {
	
	@Override
	public AuthUserDto ByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return new AuthUserDto() ;
	}

	/**
	* <p>Title: isEmailExist</p>
	* <p>Description: 如果调用UserAccountClient 对应的spring feign 网络失败，则此方法生效</p>
	* @param email
	* @return
	* @see com.xdidian.keryhu.authserver.client.UserClient#isEmailExist(java.lang.String)
	*/ 
	@Override
	public Boolean isEmailExist(String email) {
		// TODO Auto-generated method stub
		return false;
		}

	/**
	* <p>Title: isPhoneExist</p>
	* <p>Description: 如果调用UserAccountClient 对应的spring feign 网络失败，则此方法生效 </p>
	* @param phone
	* @return
	* @see com.xdidian.keryhu.authserver.client.UserClient#isPhoneExist(java.lang.String)
	*/ 
	@Override
	public Boolean isPhoneExist(String phone) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	* <p>Title: emailStatus</p>
	* <p>Description: 当局登录名loginName，前台web，ajax查询当前loginName所在的数据库，email是否激活
	* 这个是默认的失败调用</p>
	* @param loginName
	* @return
	* @see com.xdidian.keryhu.emailActivate.client.UserAccountClient#emailStatus(java.lang.String)
	*/ 
	@Override
	public Boolean emailStatus(String loginName) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	* <p>Title: loginNameToEmail</p>
	* <p>Description: </p>
	* @param loginName
	* @return
	* @see com.xdidian.keryhu.authserver.client.UserClient#loginNameToEmail(java.lang.String)
	*/ 
	@Override
	public String loginNameToEmail(String loginName) {
		// TODO Auto-generated method stub
		return null;
	}


}
