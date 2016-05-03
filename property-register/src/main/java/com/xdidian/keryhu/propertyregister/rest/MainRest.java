package com.xdidian.keryhu.propertyregister.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.xdidian.keryhu.propertyregister.client.UserAccountClient;
import com.xdidian.keryhu.propertyregister.domain.PropertyForm;
import com.xdidian.keryhu.propertyregister.domain.PropertyRegisterDto;
import com.xdidian.keryhu.propertyregister.service.ConverterUtil;
import com.xdidian.keryhu.propertyregister.service.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainRest {

	private final ConverterUtil converterUtil;

	private  final UserAccountClient createUserClient;
	
	private final UserService userService;

	
	/**
	 * 
	* @Title: createUser
	* @Description: TODO(验证输入信息的合法性的方法只方法逻辑层，在此，user－account里面不做判断，以为就算黑客恶意注册了，
	* 那么他也做不了什么事情，因为接下来需要邮件验证，手机验证，营业执照验证等，而且admin会定期检测账户)
	* @param @param propertyForm
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/property/register")
	public  ResponseEntity<?>  createUser(@RequestBody PropertyForm propertyForm) {
		
		System.out.println("正在调用property－register service 的 save get方法。");
		
		//验证输入信息的合法性
		
		userService.vlidateBeforSave(propertyForm);
			
			//将web 数据转为dto，并远程提交给useraccount service
			ResponseEntity<PropertyRegisterDto> result=createUserClient.createUser(
					converterUtil.propertyFormToRegisterDto.apply(propertyForm));
			
			return new  ResponseEntity<PropertyRegisterDto>(result.getBody(),HttpStatus.CREATED);
			
	
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping( "/admin")
	public String admin(){
		return "property-register this is admin role page ";
	}
	
	
	
	//删除user的 rest方法
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/property/{id}")
	public ResponseEntity<?> hello(@PathVariable("id") String id){
		
		//return userService.delById(id);
		return createUserClient.del(id);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public String test(){
		
		
		
		
		return "get useraccount feign ";
	}
	
	

}
