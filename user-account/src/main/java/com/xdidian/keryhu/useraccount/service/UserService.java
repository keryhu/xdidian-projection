package com.xdidian.keryhu.useraccount.service;

import java.util.Optional;

import com.xdidian.keryhu.useraccount.domain.PropertyRegisterDto;
import com.xdidian.keryhu.useraccount.domain.User;



/**
 * user-account 的一些service
 * @author hushuming
 *
 */
public interface UserService {
	
	
	
	/**
	 * 
	* @Title: findByIdentity
	* @Description: TODO(查看数据库中是否存在指定的identity)
	* @param @param identity  identity uuid，email phone的一种
	* @param @return    设定文件 返回是否存在的user
	* @return Optional<User>    返回类型  返回是否存在的user Optional类型
	* @throws
	 */
	public Optional<User> findByIdentity(String identity);

	
	
	
	/**
	 * 
	* @Title: save
	* @Description: TODO(将用户保存于数据库)
	* @param @param user  需要被保存到数据库的user 对象
	* @param @return    设定文件  返回刚刚创建的user对象
	* @return User    返回类型   返回刚刚创建的user对象
	* @throws
	 */
	public User save(User user);
	
	
	
	/**
	 * 
	* @Title: emailIsExist
	* @Description: TODO(查看email是否存在于数据库)
	* @param @param email  传入需要被查询的参数email
	* @param @return    设定文件  查看user-account接口数据库中是否存在此email
	* @return boolean    返回类型  如果存在则返回true，否则false
	* @throws
	 */
	public boolean emailIsExist(String email);
	
	
	
	
	/**
	 * 
	* @Title: phoneIsExist
	* @Description: TODO(查看手机号是否存在于数据库)
	* @param @param phone  传入需要被查询的参数phone
	* @param @return    设定文件  查看user-account接口数据库中是否存在此phone
	* @return boolean    返回类型  如果存在则返回true，否则false
	* @throws
	 */
	public boolean phoneIsExist(String phone);
	
	
	
	/**
	 * 
	* @Title: companyNameIsExist
	* @Description: TODO(查看公司名字是否存在于数据库)
	* @param @param companyName  需要被查询的公司名字
	* @param @return    设定文件  返回是否存在
	* @return boolean    返回类型   如果存在则返回true，否则false
	* @throws
	 */
	public boolean companyNameIsExist(String companyName);
	
	/**
	 * 
	* @Title: validatePropertyDtoBeforeSave
	* @Description: TODO(物业公司注册数据Dto保存之前，验证数据的合法性，就是验证email格式，手机格式，公司名字格式，是否正确)
	* @param @param dto    设定文件
	* @return void    返回类型
	* @throws
	 */
	public void validatePropertyDtoBeforeSave(PropertyRegisterDto dto);
	
	

}
