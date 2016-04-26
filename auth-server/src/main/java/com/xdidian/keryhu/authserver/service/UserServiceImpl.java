package com.xdidian.keryhu.authserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdidian.keryhu.domain.AuthUser;

import com.xdidian.keryhu.authserver.client.AuthUserClient;
import lombok.RequiredArgsConstructor;


/**
 * @ClassName: UserService
 * @Description: TODO(User 实现了 UserDetailsService，并且基于数据库crud的一些操作)
 * @ClassName: UserService
 * @author keryhu keryhu@hotmail.com
 * @date 2015年11月20日 下午3:26:50
 *
 */
@Service("userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

	private final AuthUserClient authUserClient;

	/**
	 * @Title: findByIdentity (从远程接口UserAccount 获取需要被验证的AuthUser 对象。
	 * @Description: TODO(根据User 的 唯一标志符 例如 email，userId ，phone 等查询唯一的数据库用户)
	 * @param @param identity
	 * @param @return @param 传入的唯一标示符 @return 返回查询到AuthUser结果，如无结果则为null
	 */
	@Override
	public AuthUser findByIdentity(String identity) {
		
			AuthUser result=authUserClient.ByIdentity(identity).getBody();
			System.out.println("auth server service 查到的authUser is ： "+result);
		
		return result;
	}

}
