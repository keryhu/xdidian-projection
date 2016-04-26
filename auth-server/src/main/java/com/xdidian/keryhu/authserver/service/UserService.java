package com.xdidian.keryhu.authserver.service;

import com.xdidian.keryhu.domain.AuthUser;

/**
* @ClassName: UserService
* @Description: TODO(Auth-service的一些方法接口)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月25日 下午9:34:44
 */
public interface UserService {
	
	/**
	 * 
	 * @param identity ,传入的参数是 uuid，email，phone的一种，
	 * @return  根据参数查询user－account，返回AuthUser对象
	 */
	public AuthUser findByIdentity(String identity) ;
	
	/**
	 * 
	 * @param email  传入需要被查询的参数email
	 * @return  查看user-account接口数据库中是否存在此email
	 */
	public boolean EmailIsExist(String email);
	
	/**
	 * 
	 * @param phone  传入需要被查询的参数phone
	 * @return  查看user-account接口数据库中是否存在此phone
	 */
	public boolean phoneIsExist(String phone);

}
