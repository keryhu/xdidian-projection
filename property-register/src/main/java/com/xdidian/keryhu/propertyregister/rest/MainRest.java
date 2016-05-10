package com.xdidian.keryhu.propertyregister.rest;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.esotericsoftware.minlog.Log;
import com.xdidian.keryhu.propertyregister.client.UserAccountClient;
import com.xdidian.keryhu.propertyregister.domain.PropertyForm;
import com.xdidian.keryhu.propertyregister.service.ConverterUtil;
import com.xdidian.keryhu.propertyregister.service.UserService;
import com.xdidian.keryhu.propertyregister.stream.PropertyRegisterProducer;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainRest {

	private final ConverterUtil converterUtil;

	private final UserAccountClient userClient;
	
	private final UserService userService;

	private final PropertyRegisterProducer producer;
	
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
	public void  createUser(@RequestBody PropertyForm propertyForm) {
		
		System.out.println("正在调用property－register service 的 save get方法。");
		
		//验证输入信息的合法性
		
		userService.vlidateBeforSave(propertyForm);
			
		//用户注册完，发送dto具体信息的message 给 user-account
	    producer.send(converterUtil.propertyFormToRegisterDto.apply(propertyForm));
		
	}
	
	
	/**
	 * 
	* @Title: isEmailExist
	* @Description: TODO(用于前台物业公司注册时，查询登陆的email是否存在于数据库，此为调用的后台接口)
	* @param @param email  需要被查询的email
	* @param @return    设定文件 返回的是一个json对象 Map对象，， 如果存在此email，则返回true，否则false
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(value="/query/isEmailExist",method=RequestMethod.GET)
	public ResponseEntity<?> isEmailExist(@RequestParam("email") String email){
		Log.info("查到的email 是否存在于 数据库 ： "+userClient.isEmailExist(email));
		return ResponseEntity.ok(userClient.isEmailExist(email));
	}
	
	
	/**
	 * 
	* @Title: isPhoneExist
	* @Description: TODO(用于前台物业公司注册时，查询登陆的phone是否存在于数据库，此为调用的后台接口)
	* @param @param phone  需要被调用的phone
	* @param @return    设定文件   如果存在此email，则返回true，否则false
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(value="/query/isPhoneExist",method=RequestMethod.GET)
	public ResponseEntity<?> isPhoneExist(@RequestParam("phone") String phone){
		Log.info("查到的手机号是否存在于数据库： "+userClient.isPhoneExist(phone));
		return ResponseEntity.ok(userClient.isPhoneExist(phone));
	}
	
	
	/**
	 * 
	* @Title: isComponyNameExist
	* @Description: TODO(查询数据库中是否存在此公司名字)
	* @param @param companyName  需要被查询的公司名字参数
	* @param @return    设定文件   返回数据库中是否存在此公司名字
	* @return ResponseEntity<?>    返回类型  ResponseEntity<map json>
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/query/isCompanyNameExist")
    public ResponseEntity<?> isCompanyNameExist(@RequestParam(value="", required=true) String companyName){
		
	    Log.info("查的公司名字是否在数据库中： "+userClient.isCompanyNameExist(companyName));
		return ResponseEntity.ok(userClient.isCompanyNameExist(companyName));
	}  
	
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping( "/admin")
	public String admin(){
		return "property-register this is admin role page ";
	}
	
	
	
	//删除user的 rest方法
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/property/{id}")
	public ResponseEntity<?> hello(@PathVariable("id") String id){
		
		Log.info("删除id的程序正在运行");
		return userClient.del(id);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public String test(){
		
		return "get useraccount feign ";
	}
	
	

}
