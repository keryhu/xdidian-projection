package com.xdidian.keryhu.useraccount.rest;


import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xdidian.keryhu.useraccount.domain.AuthUserDto;
import com.xdidian.keryhu.useraccount.domain.User;
import com.xdidian.keryhu.useraccount.exception.UserNotFoundException;
import com.xdidian.keryhu.useraccount.service.ConverterUtil;
import com.xdidian.keryhu.useraccount.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserController {
	
	private final UserService userService;
	
	private final ConverterUtil converterUtil;
	
	
	//返回的是spring data HAL rest 带有herf的链接，这个是 auth－service 需要调用的rest get接口
	//必须使用@RequestParam，如果使用PathVariable，则查询email会有bug
	/**
	 * 
	* @Title: findByIdentity
	* @Description: TODO(根据用户输入的登录帐号loginName，email或者phone格式 查询数据库中的user 对象,未注册用户和已经注册的用户都可以使用)
	* @param @param identity 可以是phone，email ，uuid中任何一种
	* @param @return    设定文件  返回ResponseEntity<userDto>
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/findByLoginName")
	public ResponseEntity<?> findByLoginName(@RequestParam(value="", required=true) String loginName){
		
		//如果用户不存在，则抛出错误,返回json {"code":404,"message":"您查询的用户不存在！！"}
		User user=userService.findByLoginName(loginName)
			        .orElseThrow(()->new UserNotFoundException("您输入的identity，数据库中不存在！！"));
		//将User 转为 AuthUser对象
		AuthUserDto au=converterUtil.userToAuthUser.apply(user);
		//Resource<AuthUser>  resources=new Resource<AuthUser>(
			//	new AuthUser("11111","22222",Arrays.asList(Role.ROLE_ADMIN,Role.ROLE_PROPERTY)));
	
		//resources.add(linkTo(methodOn(UserController.class).findByIdentity(identity)).withSelfRel());
		//return ResponseEntity.ok(resources);
		
		return ResponseEntity.ok(au);
	}
	
	
	
	
	
	/**
	 * 
	* @Title: isEmailExist
	* @Description: TODO(对外提供查询email是否存在数据库的api接口，不需要增加spring security验证)
	* @param @param email  需要被查询的email对象
	* @param @return    返回json 对象，map类型，是否存在此email
	* @return ResponseEntity<?>    返回类型 ResponseEntity<boolean>
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/isEmailExist")
	public ResponseEntity<?> isEmailExist(@RequestParam(value="", required=true) String email){
		log.info("需要被查询的email是："+email+" , email  是否存在于数据库 ： "+userService.emailIsExist(email));
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("isEmailExist", userService.emailIsExist(email));
		return ResponseEntity.ok(result);
	}
	


	/**
	 * 
	* @Title: isPhoneExist
	* @Description: TODO(对外提供查询phone是否存在与数据库，不需要增加spring security验证)
	* @param @param phone  需要被查询的phone
	* @param @return    设定文件 返回数据库中是否存在此phone
	* @return ResponseEntity<?>    返回类型 ResponseEntity<map json>
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/query/isPhoneExist")
    public ResponseEntity<?> isPhoneExist(@RequestParam(value="", required=true) String phone){
		log.info("需要被查询的phone是："+phone+" , phone  是否存在于数据库 ： "+userService.phoneIsExist(phone));
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("isPhoneExist", userService.phoneIsExist(phone));
		
		return ResponseEntity.ok(result);
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
	@RequestMapping(method=RequestMethod.GET,value="/users/query/isCompanyNameExist")
    public ResponseEntity<?> isCompanyNameExist(@RequestParam(value="", required=true) String companyName){
		log.info("查询公司名字是否存在，公司名字是 ： "+companyName+"查到的 结果 为 ： "+userService.companyNameIsExist(companyName));
		Map<String,Boolean> result=new HashMap<String,Boolean>();
		result.put("isCompanyNameExist", userService.companyNameIsExist(companyName));
		return ResponseEntity.ok(result);
	}  
	
	
	

}
