package com.xdidian.keryhu.propertyregister.service;


import java.util.function.Function;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.xdidian.keryhu.propertyregister.domain.PropertyForm;
import com.xdidian.keryhu.propertyregister.domain.PropertyRegisterDto;


/**
* @ClassName: ConverterUtil
* @Description: TODO(实现一些类型转换)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月21日 下午6:50:12
 */
@Component
public class ConverterUtil {
	
	/**
	 * 将web前端提交的物业公司注册数据，转换为 dto，因为需要远程http，所以在传输之前，就先加密密码。
	 */
	public Function<PropertyForm,PropertyRegisterDto> propertyFormToRegisterDto=x->{
		
		PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(11); 
		
		return new PropertyRegisterDto(x.getEmail(),x.getPhone(),passwordEncoder.encode(x.getPassword()),x.getCompanyName());
	};

}
