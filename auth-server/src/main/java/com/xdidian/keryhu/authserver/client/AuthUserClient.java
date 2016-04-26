package com.xdidian.keryhu.authserver.client;



import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xdidian.keryhu.domain.AuthUser;



/**
 * 自定义spring feign ，用于获取auth server 验证登陆用户，get 后台mongo 数据库，从user-account api 接口获取。
 * @author hushuming
 * @param 是uuid ，email phone 的任何一种。
 */
@FeignClient(name="user-account",fallback = AuthUserClientFallback.class)
public interface AuthUserClient {
	
	@RequestMapping(method=RequestMethod.GET,value="/users/findByIdentity",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthUser> ByIdentity(@RequestParam("identity") String identity);
	

}



