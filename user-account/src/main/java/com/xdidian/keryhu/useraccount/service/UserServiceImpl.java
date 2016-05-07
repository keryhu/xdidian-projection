package com.xdidian.keryhu.useraccount.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.xdidian.keryhu.util.StringValidate;
import com.xdidian.keryhu.useraccount.domain.EmailActivatedProperties;
import com.xdidian.keryhu.useraccount.domain.User;
import com.xdidian.keryhu.useraccount.repository.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @ClassName: UserServiceImpl
 * @Description: TODO(继承自userService 的方法实现)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年4月26日 下午5:17:39
 */
@Component("userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	private final EmailActivatedProperties emailActivatedProperties;

	/**
	 * @Title: findByLoginName
	 * @Description: TODO(登录login 的登录名loginName查询数据库中是否存在user数据)
	 * @param @param        loginName
	 * @param @param
	 *            传入的唯一标示符 @return 返回查询到数据库User结果，如无结果则为null
	 */

	@Override
	public Optional<User> findByLoginName(String loginName) {
		// TODO Auto-generated method stub

		switch (StringValidate.checkType(loginName)) {
		case EMAIL:
			return  repository.findByEmail(loginName.toLowerCase());
			
		case PHONE:
			return  repository.findByPhone(loginName);
		
		default:
			return  Optional.empty();

		}
		
	}

	/**
	
	 * Title: save
	 * Description: 保存需要被保存的user 对象到mongo数据库 ,因为此方法不对外公开，所以所有的合法性验证都放在上一步的dto处理
	 * @param user
	 * @return 刚刚保存的user 对象
	 * @see com.xdidian.keryhu.useraccount.service.UserService#save(com.xdidian.keryhu.useraccount.domain.User)
	 */
	@Override
	@Transactional
	public User save(User user) {
		// TODO Auto-generated method stub
		return repository.save(user);

	}

	/**
	
	 * Title: EmailIsExist
	 * Description: 查看数据库中是否有此email
	
	 * @param email   传入需要被查询的email参数
	 * @return 返回是否有此email，如果有，返回true，否则false
	 * @see com.xdidian.keryhu.useraccount.service.UserService#EmailIsExist(java.lang.String)
	 */
	@Override
	public boolean emailIsExist(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email).isPresent();
	}

	/**
	
	 * Title: phoneIsExist
	 * Description: 查询数据库中是否有此phone对象
	
	 * @param phone          需要被查询的phone对象
	 * @return 如果数据库中存在此phone，则返回true，否则false
	 * @see com.xdidian.keryhu.useraccount.service.UserService#phoneIsExist(java.lang.String)
	 */
	@Override
	public boolean phoneIsExist(String phone) {
		// TODO Auto-generated method stub
		return repository.findByPhone(phone).isPresent();
	}

	/**
	
	 * Title: companyNameIsExist
	 * Description: 查询数据库中是否有此公司名字
	 * @param companyName   需要被查询的公司名字
	 * @return 如果存在则返回true，否则false
	 * @see com.xdidian.keryhu.useraccount.service.UserService#companyNameIsExist(java.lang.String)
	 */
	@Override
	public boolean companyNameIsExist(String companyName) {
		// TODO Auto-generated method stub
		return repository.findByCompanyName(companyName).isPresent();
	}

	

	/**
	* <p>Title: deleteById</p>
	* <p>Description:  根据user ID 删除user</p>
	* @param id
	* @return
	* @see com.xdidian.keryhu.useraccount.service.UserService#deleteById(java.lang.String)
	*/ 
	@Override
	@Transactional
	public Optional<User> deleteById(String id) {
		// TODO Auto-generated method stub
		return repository.deleteById(id);
	}

	

}
