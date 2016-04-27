package com.xdidian.keryhu.useraccount.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xdidian.keryhu.useraccount.domain.AuthUserDto;
import com.xdidian.keryhu.useraccount.domain.PropertyRegisterDto;
import com.xdidian.keryhu.useraccount.domain.User;
import com.xdidian.keryhu.useraccount.service.ConverterUtil;
import com.xdidian.keryhu.useraccount.service.UserService;
import com.xdidian.keryhu.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
	
	private final UserService userService;
	
	private final ConverterUtil converterUtil;
	
	
	
	
	//返回的是spring data HAL rest 带有herf的链接，这个是 auth－service 需要调用的rest get接口
	//必须使用@RequestParam，如果使用PathVariable，则查询email会有bug
	/**
	 * 
	* @Title: findByIdentity
	* @Description: TODO(根据identity 查询数据库中的user 对象,未注册用户和已经注册的用户都可以使用)
	* @param @param identity 可以是phone，email ，uuid中任何一种
	* @param @return    设定文件  返回ResponseEntity<userDto>
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/findByIdentity")
	public ResponseEntity<?> findByIdentity(@RequestParam("identity") String identity){
		System.out.println("identity is : "+identity);
		Assert.notNull(identity,"查询的id不能为空");
		
		//如果用户不存在，则抛出错误。
		User user=userService.findByIdentity(identity)
			        .orElseThrow(()->new IllegalArgumentException("您查询的用户不存在！！"))
				;
		System.out.println("在userAccount中，通过identity查询到的user is ："+user);
		//将User 转为 AuthUser对象
		
		AuthUserDto au=converterUtil.userToAuthUser.apply(user);
		
		//Resource<AuthUser>  resources=new Resource<AuthUser>(
			//	new AuthUser("11111","22222",Arrays.asList(Role.ROLE_ADMIN,Role.ROLE_PROPERTY)));
	
		//resources.add(linkTo(methodOn(UserController.class).findByIdentity(identity)).withSelfRel());
		//return ResponseEntity.ok(resources);
		
		return ResponseEntity.ok(au);
	}
	
	
	/**
	* @Title: createUser
	* @Description: TODO(将远程提交过来的物业公司注册dto对象转为user 对象，并且保存数据库。
	* 增加验证，必须是未注册用户才可以使用save)
	* @param @param dto
	* @param @return    设定文件
	* @return ResponseEntity<Resource<User>>    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.POST,value="/users/property/save")
		public ResponseEntity<PropertyRegisterDto> createUser(@RequestBody PropertyRegisterDto dto){
		   
		  System.out.println("提交的dto is : "+dto);
		  
		  if(SecurityUtils.isAuthenticated()){
			  //抛出错误，
		  }
		
		  //将提交的PropertyRegisterDto 转为User对象。
		
		  User user=converterUtil.propertyRegisterDtoToUser.apply(dto);
		  //保存数据库
		  userService.save(user);
			
		  return new  ResponseEntity<PropertyRegisterDto>(dto,HttpStatus.CREATED);
		}
	
	
	/**
	 * 
	* @Title: isEmailExist
	* @Description: TODO(对外提供查询email是否存在数据库的api接口，不需要增加spring security验证)
	* @param @param email  需要被查询的email对象
	* @param @return    返回是否存在此email
	* @return ResponseEntity<?>    返回类型 ResponseEntity<boolean>
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/isEmailExist")
	public ResponseEntity<?> isEmailExist(@RequestParam("email") String email){
		
		return ResponseEntity.ok(userService.emailIsExist(email));
	}
	
	

	/**
	 * 
	* @Title: isPhoneExist
	* @Description: TODO(对外提供查询phone是否存在与数据库，不需要增加spring security验证)
	* @param @param phone  需要被查询的phone
	* @param @return    设定文件 返回数据库中是否存在此phone
	* @return ResponseEntity<?>    返回类型 ResponseEntity<boolean>
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/isPhoneExist")
    public ResponseEntity<?> isPhoneExist(@RequestParam("phone") String phone){
		
		return ResponseEntity.ok(userService.phoneIsExist(phone));
	}  
	
	
	/**
	 * 
	* @Title: isComponyNameExist
	* @Description: TODO(查询数据库中是否存在此公司名字)
	* @param @param companyName  需要被查询的公司名字参数
	* @param @return    设定文件   返回数据库中是否存在此公司名字
	* @return ResponseEntity<?>    返回类型  ResponseEntity<boolean>
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/users/isComponyNameExist")
    public ResponseEntity<?> isComponyNameExist(@RequestParam("companyName") String companyName){
		
		return ResponseEntity.ok(userService.phoneIsExist(companyName));
	}  
	
	
	

}
