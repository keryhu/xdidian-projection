package com.xdidian.keryhu.authserver.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdidian.keryhu.authserver.client.UserAccountClient;
import com.xdidian.keryhu.authserver.domain.AuthUserDto;

import lombok.RequiredArgsConstructor;


/**
 * @ClassName: UserService
 * @Description: TODO(Auth-server的service)
 * @ClassName: UserService
 * @author keryhu keryhu@hotmail.com
 * @date 2015年11月20日 下午3:26:50
 *
 */
@Service("userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

	private final UserAccountClient authUserClient;

	/**
	 * 
	 * @Title: findByLoginName (从远程接口UserAccount 获取需要被验证的AuthUser 对象。
	 * @Description: TODO(根据User 的 唯一标志符 用户登录的帐号。例如 email ，phone 等查询唯一的数据库用户)
	 * @param @param identity
	 * @param @return @param 传入的唯一标示符 @return 返回查询到AuthUser结果，返回结果是Optional形式
	 */
	@Override
	public Optional<AuthUserDto> findByLoginName(String loginName) {
					
		return authUserClient.ByLoginName(loginName);
	}


}
