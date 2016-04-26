package com.xdidian.keryhu.propertyregister.client;



import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xdidian.keryhu.propertyregister.domain.PropertyRegisterDto;


/**
 * 
* @ClassName: CreateUserClient
* @Description: TODO(spring feign client ,下面主要实现了将用户注册数据dto 远程post 给user－account service)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 上午9:24:14
 */
@FeignClient(name="user-account",fallback = CreateUserClientFallback.class)
public interface CreateUserClient {
	
	@RequestMapping(method=RequestMethod.POST,value="/users/property/save",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PropertyRegisterDto> createUser(@RequestBody PropertyRegisterDto dto);
		

	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
	public ResponseEntity<?> del(@PathVariable("id") String id);
}



