package com.xdidian.keryhu.useraccount.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import com.xdidian.keryhu.util.StringValidate;
import com.xdidian.keryhu.useraccount.domain.User;
import com.xdidian.keryhu.useraccount.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Component("userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService  {

	private final UserRepository repository;

	/**
	 * @Title: findByIdentity
	 * @Description: TODO(根据User 的 唯一标志符 例如 email，userId ，phone 等查询唯一的数据库用户)
	 * @param @param
	 *            identity
	 * @param @return
	 * 			@param 传入的唯一标示符 @return 返回查询到数据库User结果，如无结果则为null
	 */

	@Override
	public Optional<User> findByIdentity(String identity) {
		// TODO Auto-generated method stub
		
		if (StringValidate.IsUuid(identity)) {
		 return	 repository.findById(identity);
		} else if (StringValidate.IsEmail(identity)) {
		return	repository.findByEmail(identity.toLowerCase());
			
		} else if (StringValidate.IsPhone(identity)) {
		return	 repository.findByPhone(identity);
		} 

		return Optional.empty();
	}

	@Override
	@Transactional
	public User save(User user) {
		// TODO Auto-generated method stub
		Assert.notNull(user,"被保存的user对象不能为空！！");
	return	repository.save(user);
		
	}
	
	
	//只是初略的验证用户的输入格式是否正确。
	

}
