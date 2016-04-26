package com.xdidian.keryhu.authserver.service;

import com.xdidian.keryhu.domain.AuthUser;

/**
* @ClassName: UserService
* @Description: TODO(制造一个根据用户identity ，例如uuid ，emial，phone 查询user-account是否存在此用户的接口。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月25日 下午9:34:44
 */
public interface UserService {
	
	public AuthUser findByIdentity(String identity) ;

}
