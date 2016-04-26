package com.xdidian.keryhu.authserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdidian.keryhu.domain.AuthUser;

import com.xdidian.keryhu.authserver.client.AuthUserClient;
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

	private final AuthUserClient authUserClient;

	/**
	 * 
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

	/**
	 * 
	 * @param email  传入需要被查询的参数email
	 * @return  查看user-account接口数据库中是否存在此email
	 */
	@Override
	public boolean EmailIsExist(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 * @param phone  传入需要被查询的参数phone
	 * @return  查看user-account接口数据库中是否存在此phone
	 */
	@Override
	public boolean phoneIsExist(String phone) {
		// TODO Auto-generated method stub
		return false;
	}

}
