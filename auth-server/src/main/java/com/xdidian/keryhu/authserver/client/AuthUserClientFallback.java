package com.xdidian.keryhu.authserver.client;




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
public class AuthUserClientFallback implements AuthUserClient {

	@Override
	public ResponseEntity<AuthUser> ByIdentity(String identity) {
		// TODO Auto-generated method stub
		return new ResponseEntity<AuthUser>(new AuthUser(),HttpStatus.NOT_FOUND);
	}

}
