package com.xdidian.keryhu.authserver.client;


import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.xdidian.keryhu.domain.AuthUser;

/**
 * feign 连接失败的一个默认方法。
 * @author hushuming
 *
 */
@Component
public class UserAccountClientFallback implements UserAccountClient {
	
	@Override
	public ResponseEntity<AuthUser> ByIdentity(String identity) {
		// TODO Auto-generated method stub
		return new ResponseEntity<AuthUser>(new AuthUser(),HttpStatus.NOT_FOUND);
	}

	/**
	* <p>Title: isEmailExist</p>
	* <p>Description: 如果调用UserAccountClient 对应的spring feign 网络失败，则此方法生效</p>
	* @param email
	* @return
	* @see com.xdidian.keryhu.authserver.client.UserAccountClient#isEmailExist(java.lang.String)
	*/ 
	@Override
	public ResponseEntity<Map<String,Boolean>> isEmailExist(String email) {
		// TODO Auto-generated method stub
		return  ResponseEntity.ok(null);	
		}

	/**
	* <p>Title: isPhoneExist</p>
	* <p>Description: 如果调用UserAccountClient 对应的spring feign 网络失败，则此方法生效 </p>
	* @param phone
	* @return
	* @see com.xdidian.keryhu.authserver.client.UserAccountClient#isPhoneExist(java.lang.String)
	*/ 
	@Override
	public ResponseEntity<Map<String,Boolean>> isPhoneExist(String phone) {
		// TODO Auto-generated method stub
		return   ResponseEntity.ok(null);
	}

}
