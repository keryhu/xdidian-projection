package com.xdidian.keryhu.useraccount.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRest {
	
	//@PreAuthorize("isAuthenticated()")  表示用户必须是注册用户
	
	//@PreAuthorize("isAuthenticated()")
	@PreAuthorize("hasAuthority('ROLE_PROPERTY')")
	@RequestMapping(method=RequestMethod.GET,value="/property")
	public String hello(){
		return "this is hello page , it should be ROLE_PROPERTY ";
	}
	
	
	
	@RequestMapping(method=RequestMethod.GET,value="/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	public String test(){
		return "this is test page , it should be ROLE_ADMIN ";
	}
	
	@PreAuthorize("hasAuthority('ROLE_TENANT')")
	//@PreAuthorize("isAuthenticated()")
	@RequestMapping(method=RequestMethod.GET,value="/tenant")
	public String tenant(){
		return "this is test page , it should be ROLE_TENANT ";
	}
	
	
	
	
	
	
	
	
	

}
