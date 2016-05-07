package com.xdidian.keryhu.useraccount.service;

import java.util.Optional;

import com.xdidian.keryhu.useraccount.domain.User;



/**
 * user-account 的一些service
 * @author hushuming
 *
 */
public interface UserService {
	
	
	
	/**
	 * 
	* @Title: findByLoginName
	* @Description: TODO(查看数据库中是否存在指定的loginName，有email或者phone两种格式)
	* @param @param loginName  email phone任何一种
	* @param @return    设定文件 返回是否存在的user
	* @return Optional<User>    返回类型  返回是否存在的user Optional类型
	* @throws
	 */
	public Optional<User> findByLoginName(String loginName);

	
	
	
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
	* @Title: deleteById
	* @Description: TODO(根据id，删除user)
	* @param @param id
	* @param @return    设定文件
	* @return Optional<User>    返回类型
	* @throws
	 */
	public Optional<User> deleteById(String id);
	
	
}
