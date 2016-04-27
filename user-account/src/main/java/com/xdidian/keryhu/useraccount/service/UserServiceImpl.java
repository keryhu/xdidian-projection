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

/**
 * 
* @ClassName: UserServiceImpl
* @Description: TODO(继承自userService 的方法实现)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 下午5:17:39
 */
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

	
	/**
	 * 
	* <p>Title: save</p>
	* <p>Description: 保存需要被保存的user 对象到mongo数据库 </p>
	* @param user
	* @return  刚刚保存的user 对象
	* @see com.xdidian.keryhu.useraccount.service.UserService#save(com.xdidian.keryhu.useraccount.domain.User)
	 */
	@Override
	@Transactional
	public User save(User user) {
		// TODO Auto-generated method stub
		Assert.notNull(user,"被保存的user对象不能为空！！");
	return	repository.save(user);
		
	}

	/**
	 *
	* <p>Title: EmailIsExist</p>
	* <p>Description: 查看数据库中是否有此email </p>
	* @param email 传入需要被查询的email参数
	* @return  返回是否有此email，如果有，返回true，否则false
	* @see com.xdidian.keryhu.useraccount.service.UserService#EmailIsExist(java.lang.String)
	 */
	@Override
	public boolean emailIsExist(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email).isPresent();
	}

	/**
	 * 
	* <p>Title: phoneIsExist</p>
	* <p>Description: 查询数据库中是否有此phone对象</p>
	* @param phone  需要被查询的phone对象
	* @return  如果数据库中存在此phone，则返回true，否则false
	* @see com.xdidian.keryhu.useraccount.service.UserService#phoneIsExist(java.lang.String)
	 */
	@Override
	public boolean phoneIsExist(String phone) {
		// TODO Auto-generated method stub
		return repository.findByPhone(phone).isPresent();
	}


	/**
	* <p>Title: companyNameIsExist</p>
	* <p>Description:  查询数据库中是否有此公司名字</p>
	* @param companyName  需要被查询的公司名字
	* @return   如果存在则返回true，否则false
	* @see com.xdidian.keryhu.useraccount.service.UserService#companyNameIsExist(java.lang.String)
	*/ 
	@Override
	public boolean companyNameIsExist(String companyName) {
		// TODO Auto-generated method stub
		return repository.findByCompanyName(companyName).isPresent();
	}
	
	

}
