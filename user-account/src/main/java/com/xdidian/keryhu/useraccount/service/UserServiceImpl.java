package com.xdidian.keryhu.useraccount.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.xdidian.keryhu.util.StringValidate;

import com.amazonaws.util.StringUtils;
import com.xdidian.keryhu.useraccount.domain.User;
import com.xdidian.keryhu.useraccount.exception.EmailNotFoundException;
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

	/**
	 * @Title: findByLoginName
	 * @Description: TODO(登录login 的登录名loginName(email或phone）查询数据库中是否存在user数据
	 * 主要用于login服务器的 userDetailsService 的后台调用。spring－feign)
	 * @param @param        loginName
	 * @param @param 传入的唯一标示符
	 *  @return 返回查询到数据库User结果，如无结果则为null
	 */

	@Override
	public Optional<User> findByLoginName(final String loginName) {
		// TODO Auto-generated method stub
		
        Optional<User> user;
        if(StringUtils.isNullOrEmpty(loginName)){
        	return Optional.empty();
        }
		switch (StringValidate.checkType(loginName)) {
		case EMAIL:
			user= repository.findByEmail(loginName.toLowerCase());
			break;
		case PHONE:
			user= repository.findByPhone(loginName);
			break;
		default:
			user= Optional.empty();
			break;

		}	
		return user;
		
	}
	
	
	/**
	* <p>Title: findById</p>
	* <p>Description:这里的id指，email、phone，或user中的id，3个里任何一种，来查看数据库的user </p>
	* @param id
	* @return
	* @see com.xdidian.keryhu.useraccount.service.UserService#findById(java.lang.String)
	*/ 
	@Override
	public Optional<User> findById(final String id) {
		// TODO Auto-generated method stub
		 Optional<User> user;
	        if(StringUtils.isNullOrEmpty(id)){
	        	return Optional.empty();
	        }
			switch (StringValidate.checkType(id)) {
			case EMAIL:
				user= repository.findByEmail(id.toLowerCase());
				break;
			case PHONE:
				user= repository.findByPhone(id);
				break;
			case UUID:
				user=repository.findById(id);
				break;
			default:
				user= Optional.empty();
				break;

			}	
			return user;
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
	public User save(final User user) {
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
	public boolean emailIsExist(final String email) {
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
	public boolean phoneIsExist (final String phone) {
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
	public boolean companyNameIsExist(final String companyName) {
		// TODO Auto-generated method stub
		return repository.findByCompanyName(companyName).isPresent();
	}


	/**
	* <p>Title: deleteUser</p>
	* <p>Description: 删除传递进来的user对象</p>
	* @param user
	* @see com.xdidian.keryhu.useraccount.service.UserService#deleteUser(com.xdidian.keryhu.useraccount.domain.User)
	*/ 
	@Transactional
	@Override
	public void deleteUser(final User user) {
		// TODO Auto-generated method stub
			Assert.notNull(user,"被删除的对象user不能为null");
			repository.findById(user.getId())
			          .ifPresent(e->repository.delete(e));
	}


	/**
	* <p>Title: emailStatus</p>
	* <p>Description: 如果loginName找不到对于的user 数据，返回false，否则返回查询到的 emailStatus激活状态</p>
	* @param loginName
	* @return
	* @see com.xdidian.keryhu.useraccount.service.UserService#emailStatus(java.lang.String)
	*/ 
	@Override
	public boolean emailStatus(final String loginName) {
		// TODO Auto-generated method stub
		return findByLoginName(loginName).map(e->e.isEmailStatus()).orElse(false);
	}


	/**
	* <p>Title: loginNameToEmail</p>
	* <p>Description: 用于login服务器，登录时，根据loginName查询系统中存在的email账户</p>
	* @param loginName
	* @return
	* @see com.xdidian.keryhu.useraccount.service.UserService#loginNameToEmail(java.lang.String)
	*/ 
	@Override
	public String loginNameToEmail(final String loginName) {
		// TODO Auto-generated method stub
		return findById(loginName).map(e->e.getEmail())
				.orElseThrow(()-> new EmailNotFoundException("您要查找的email不存在！"));
		
	}



	

}
