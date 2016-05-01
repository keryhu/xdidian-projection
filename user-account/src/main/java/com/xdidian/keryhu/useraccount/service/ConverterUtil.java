package com.xdidian.keryhu.useraccount.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.Function;
import org.springframework.stereotype.Component;
import com.xdidian.keryhu.domain.Role;
import com.xdidian.keryhu.useraccount.domain.AuthUserDto;
import com.xdidian.keryhu.useraccount.domain.PropertyRegisterDto;
import com.xdidian.keryhu.useraccount.domain.User;


@Component
public class ConverterUtil {
	
	
	/**
	 * 将User 转为 AuthUser 对象。
	 */
	public Function<User,AuthUserDto> userToAuthUser = x ->
	      new AuthUserDto(x.getId(), x.getPassword(), x.getRoles());
	      
	  
	      /**
	       * 将物业公司注册数据转为User对象。此时将用户注册时间和权限加进去。
	       */
	public Function<PropertyRegisterDto,User> propertyRegisterDtoToUser=x->{
		 
		User user=new User();
		user.setEmail(x.getEmail());
		user.setPhone(x.getPhone());
		user.setPassword(x.getPassword());
		user.setCompanyName(x.getCompanyName());
		user.setRoles(Arrays.asList(Role.ROLE_PROPERTY));
		user.setRegisterTime(LocalDateTime.now());
		return user;
	};
	

}
