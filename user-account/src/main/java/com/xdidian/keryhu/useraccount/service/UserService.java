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
	 * @param identity uuid，email phone的一种
	 * @return 返回是否存在的user
	 */
	public Optional<User> findByIdentity(String identity);

	
	/**
	 * 
	 * @param user  需要被保存到数据库的user 对象
	 * @return  返回刚刚创建的user对象
	 */
	public User save(User user);
	
	/**
	 * 
	 * @param email  传入需要被查询的参数email
	 * @return  查看user-account接口数据库中是否存在此email
	 */
	public boolean emailIsExist(String email);
	
	/**
	 * 
	 * @param phone  传入需要被查询的参数phone
	 * @return  查看user-account接口数据库中是否存在此phone
	 */
	public boolean phoneIsExist(String phone);
	
	
	

}
