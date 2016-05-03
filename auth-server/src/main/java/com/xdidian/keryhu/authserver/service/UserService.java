package com.xdidian.keryhu.authserver.service;


import com.xdidian.keryhu.authserver.domain.AuthUserDto;

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
	public AuthUserDto findByIdentity(String identity) ;
	
	
	
	

}
