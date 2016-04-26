package com.xdidian.keryhu.propertyregister.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.xdidian.keryhu.propertyregister.domain.PropertyRegisterDto;



/**
* @ClassName: CreateUserClientFallback
* @Description: TODO(如果远程调用post连接失败的 默认方法。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 上午9:25:12
 */
@Component
public class CreateUserClientFallback implements CreateUserClient {

	@Override
	public ResponseEntity<PropertyRegisterDto> createUser(PropertyRegisterDto dto) {
		// TODO Auto-generated method stub
		return new ResponseEntity<PropertyRegisterDto>(new PropertyRegisterDto(),HttpStatus.BAD_GATEWAY);
	}

	@Override
	public ResponseEntity<?> del(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
