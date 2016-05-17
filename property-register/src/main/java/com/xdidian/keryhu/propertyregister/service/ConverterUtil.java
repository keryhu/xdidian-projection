package com.xdidian.keryhu.propertyregister.service;


import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.xdidian.keryhu.domain.EmailActivatedDto;
import com.xdidian.keryhu.domain.PropertyRegisterDto;
import com.xdidian.keryhu.propertyregister.domain.ActivatedProperties;
import com.xdidian.keryhu.propertyregister.domain.PropertyForm;


/**
* @ClassName: ConverterUtil
* @Description: TODO(实现一些类型转换)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月21日 下午6:50:12
 */
@Component
public class ConverterUtil {
	
	@Autowired
	private  ActivatedProperties activatedProperties;
	
	/**
	 * 将web前端提交的物业公司注册数据，转换为 dto，因为需要远程http，所以在传输之前，就先加密密码。
	 */
	public Function<PropertyForm,PropertyRegisterDto> propertyFormToRegisterDto=x->{
		
		PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(11); 
		
		return new PropertyRegisterDto(x.getEmail(),x.getPhone(),passwordEncoder.encode(x.getPassword()),
				x.getCompanyName(),x.getDirectName());
	};
	
	
	/**
	 * 将注册完的物业公司对象PropertyForm，转为EmailActivatedDto,为了email激活
	 */

	public Function<PropertyForm,EmailActivatedDto> propertyFormToEmailActivatedDto = x->{
		LocalDateTime expireDate=LocalDateTime.now().plusHours(activatedProperties.getExpiredTime());
		EmailActivatedDto dto=new EmailActivatedDto();
		dto.setEmail(x.getEmail());
		dto.setEmailToken(UUID.randomUUID().toString());
		dto.setReregisterToken(UUID.randomUUID().toString());
		dto.setResendToken(UUID.randomUUID().toString());
		dto.setExpireDate(expireDate);
		return dto;
	};
	
}
