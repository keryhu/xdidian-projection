package com.xdidian.keryhu.emailActivate.client;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Component;

/**
 * feign 连接失败的一个默认方法。
 * @author hushuming
 *
 */
@Component
public class UserClientFallback implements UserClient {
	
	

	/**
	* <p>Title: isEmailExist</p>
	* <p>Description: 如果调用UserAccountClient 对应的spring feign 网络失败，则此方法生效</p>
	* @param email
	* @return
	* @see com.xdidian.keryhu.UserClient.client.UserAccountClient#isEmailExist(java.lang.String)
	*/ 
	@Override
	public Boolean isEmailExist(String email) {
		// TODO Auto-generated method stub
		return false;
		}

	/**
	* <p>Title: emailStatus</p>
	* <p>Description: 当局登录名loginName，前台web，ajax查询当前loginName所在的数据库，email是否激活
	* 这个是默认的失败调用</p>
	* @param loginName
	* @return
	* @see com.xdidian.keryhu.emailActivate.client.UserClient#emailStatus(java.lang.String)
	*/ 
	@Override
	public Boolean emailStatus(String loginName) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	* <p>Title: findRolesByEmail</p>
	* <p>Description: </p>
	* @param email
	* @return
	* @see com.xdidian.keryhu.emailActivate.client.UserClient#findRolesByEmail(java.lang.String)
	*/ 
	@Override
	public List<String> findRolesByEmail(String email) {
		// TODO Auto-generated method stub
		return new ArrayList<String>();
				
	}

	

}
