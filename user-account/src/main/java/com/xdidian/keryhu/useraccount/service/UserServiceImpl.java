package com.xdidian.keryhu.useraccount.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.xdidian.keryhu.util.StringValidate;
import com.xdidian.keryhu.useraccount.domain.EmailActivatedProperties;
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
	
	 * Title: emailActivatedCodeExist
	 * Description:如果email不存在，返回false，如果 查询的对象，不存在emailActivatedCode，则返回false
	 * 否则判断 参数emailActivatedCode 是否和 查询的结果 emailActivatedCode 相等。
	
	 * @param email
	 * @param emailActivatedCode
	 * @return
	 * @see com.xdidian.keryhu.useraccount.service.UserService#emailActivatedCodeExist(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean emailActivatedCodeExist(String email, String emailActivatedCode) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email).map(e -> {
			if (e.getEmailActivatedCode() != null) {
				return e.getEmailActivatedCode().equals(emailActivatedCode);
			}
			return false;

		}).orElse(false);

	}

	/**
	 * Title: emailActivatedExpired
	 * Description: 如果email 存在的情况下，查看注册时间 是否 已经大于 最大的允许时间
	 * 默认的执行此方法的前提是： email存在，且email的 emailActivated＝false ，在这个前提下，才会执行此方法
	 * @param email
	 * @return 
	 * @see com.xdidian.keryhu.useraccount.service.UserService#emailActivatedCodeExpired(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean emailActivatedExpired(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email)
				         .filter(e->!e.isEmailActivatedStatus())
		                 .map(e->LocalDateTime.now().isAfter(e.getRegisterTime()
		                		 .plusHours(emailActivatedProperties.getExpiredTime())))
		                 .orElseThrow(()->new EmailNotFoundException("您需要查询的email不存在于数据库"));	
	}

	/**
	 * Title: findEmailActivatedStatus

	 * Description: 查询当前email所在的user 数据库账户，email 有没有被激活
	 * @param email
	 * @return 如果email 不存在，返回默认fasle
	 * @see com.xdidian.keryhu.useraccount.service.UserService#findEmailActivated(java.lang.String)
	 */
	@Override
	public boolean findEmailActivatedStatus(String email) {
		// TODO Auto-generated method stub

		return repository.findByEmail(email).map(e -> e.isEmailActivatedStatus()).orElse(false);
	}

	/**
	
	 * Description: 查看当前用户的email，邮件激活发送的次数，有没有超过最大的限定次数，默认是返回true（就是没有超过）
	 * 如果email不存在于数据库，则返回默认值true
	 * @param email
	 * @return
	 * @see com.xdidian.keryhu.useraccount.service.UserService#emailActivateSendTimesNotOver(java.lang.String)
	 */
	@Override
	public boolean emailActivateSendTimesNotOver(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email)
				.map(e -> e.getEmailActivatedSendTimes() < emailActivatedProperties.getMaxSendTimes()).orElse(true);

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
