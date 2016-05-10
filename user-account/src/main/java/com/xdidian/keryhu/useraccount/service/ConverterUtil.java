package com.xdidian.keryhu.useraccount.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xdidian.keryhu.domain.EmailActivatedDto;
import com.xdidian.keryhu.domain.PropertyRegisterDto;
import com.xdidian.keryhu.domain.Role;
import com.xdidian.keryhu.useraccount.domain.AuthUserDto;
import com.xdidian.keryhu.useraccount.domain.EmailActivatedProperties;
import com.xdidian.keryhu.useraccount.domain.User;


@Component
public class ConverterUtil {
	
	@Autowired
	private  EmailActivatedProperties emailActivatedProperties;
	
	@Autowired
	private RandomUtil randomUtil;
	
	
	/**
	 * 将User 转为 AuthUser 对象。
	 */
	public Function<User,AuthUserDto> userToAuthUser = x ->
	      new AuthUserDto(x.getId(), x.getPassword(), x.getRoles(),x.getEmail(),
	    		  x.isEmailActivatedStatus());
	      
	  
	      /**
	       * 将物业公司注册数据转为User对象。此时将用户注册时间和权限加进去。
	       */
	public Function<PropertyRegisterDto,User> propertyRegisterDtoToUser=x->{
		 
		User user=new User();
		user.setEmail(x.getEmail());
		user.setPhone(x.getPhone());
		user.setPassword(x.getPassword());
		user.setCompanyName(x.getCompanyName());
		user.setDirectName(x.getDirectName());
		user.setRoles(Arrays.asList(Role.ROLE_PROPERTY));
		user.setRegisterTime(LocalDateTime.now());
		user.setEmailActivatedStatus(false);
		user.setEmailActivatedCode(randomUtil.create(emailActivatedProperties.getCodeLength()));
		user.setEmailActivatedSentTimes(0);
		return user;
	};
	
	public Function<User,EmailActivatedDto> userToEmailActivatedDto=x->{
		EmailActivatedDto dto=new EmailActivatedDto();
		dto.setEmail(x.getEmail());
		dto.setEmailActivatedCode(x.getEmailActivatedCode());
		//设置email激活的过期截止时间
		dto.setDeadlineOfEmailActivated(x.getRegisterTime().plusHours(emailActivatedProperties.getExpiredTime()));
		return dto;
	};
	   

}
