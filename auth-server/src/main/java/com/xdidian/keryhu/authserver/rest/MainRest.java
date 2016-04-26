package com.xdidian.keryhu.authserver.rest;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
* @ClassName: MainRest
* @Description: TODO(获取当前在线用户。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月25日 下午9:24:48
 */
@RestController
public class MainRest {
	
	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}
	
	@PreAuthorize("permitAll")
	@RequestMapping("/hello")
	public String hello(){
		return "this is auth-server hello page ...";
	}

}
