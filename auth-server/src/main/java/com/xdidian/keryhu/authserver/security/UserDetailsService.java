package com.xdidian.keryhu.authserver.security;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.xdidian.keryhu.authserver.domain.AuthUserDto;
import com.xdidian.keryhu.authserver.domain.LoginAttemptProperties;
import com.xdidian.keryhu.authserver.exception.BlockedException;
import com.xdidian.keryhu.authserver.exception.EmailNotActivatedException;
import com.xdidian.keryhu.authserver.service.LoginAttemptUserService;
import com.xdidian.keryhu.authserver.service.UserServiceImpl;

@Component("userDetailsService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final UserServiceImpl userService;

	private final HttpServletRequest request;
	
	private final LoginAttemptUserService loginAttemptUserService;
	
	private final LoginAttemptProperties loginAttemptProperties;
	
	private final MessageSource messageSource;
	
	/**
	 * <p>
	 * Title: loadUserByUsername
	 * Description: 根据用户名，查询数据库系统后台(user-account),是否存在此用户,同时增加了两个拦截器
	 * email账户是否验证激活的拦截，如果没有则报错
	 * 请注意，block拦截器必须要放在 查询用户系统之前，否则其功能会被spring security 代替。
	 * 一个loginAttempt 的拦截限制，一旦用户被冻结，则锁定账户多少小时
	 * 目前此项目中，增加了messageSource，获取拦截的具体提示信息， 参数｛｝的格式，参考java.text.MessageFormat
	 * Object[] args 代表 message中 {0} ,{1},{2} 等信息的设置的 数组。 支持 Number Date Time String
	 * getMessage,的第二个参数，放上面的 Object[],，第三个参数放  默认的 message，意思是如果系统找不到 
	 * 前面的 Object[] args，第三个参数就是替代品
	 * 
	 * 请注意，spring security里面的所有错误提示，都不能使用 Assert，
	 * @param username  包含uuid，email phone 的任何一种
	 * @return 当前用户的spring security details
	 * @throws UsernameNotFoundException  当前用户不存在
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		Object[] args={loginAttemptProperties.getTimeOfPerid(),loginAttemptProperties.getMaxAttemptTimes(),
				loginAttemptProperties.getTimeOfLock()};
		String message=messageSource.getMessage("message.ip.blocked", 
				args, "您的IP已经被锁定，请稍后再试！", LocaleContextHolder.getLocale());
		
		if(loginAttemptUserService.isBlocked(userService.getIP(request))){
			log.info("您的IP已经被锁定账户了");
			throw new BlockedException(message);
		}
		
		
		return (UserDetails) userService.findByLoginName(username).map(a -> {
			Object[] args1={username};
			String accountNotExist=messageSource.getMessage("message.account.noAccount",
					args1 ,"您的账户不存在，请先注册！",LocaleContextHolder.getLocale());
			
			if(a.getId()==null||a.getId().isEmpty()){
				throw new UsernameNotFoundException(accountNotExist);
			}
			
			String emailNotActivated=messageSource.getMessage("message.email.notActivated",null ,
					"您的email尚未激活，请查看邮箱，或垃圾邮件，或重新注册！",LocaleContextHolder.getLocale());
			//email 未激活拦截
			if(!a.isEmailStatus()){
				throw new EmailNotActivatedException(emailNotActivated);
			}
				
			return new org.springframework.security.core.userdetails.User(a.getId(), a.getPassword(), true, true, true,
					true, getAuthorities(a));
		}).get();
	}

	/**
	 * @Title: getAuthorities 
	 * @Description: TODO(获取用户的权限,增加了roles 为null
	 * 的逻辑判断。)
	 *  @param @param 需要被获取的用户class
	 *   @param @return 返回用户当前权限 
	 *   @return Collection<? extends GrantedAuthority> 返回类型 @throws
	 */
	private Collection<? extends GrantedAuthority> getAuthorities(AuthUserDto user) {

		return (user.getRoles() == null)?
				null:
			 (user.getRoles().stream()
					 .filter(e -> e != null)
					 .map(e -> new SimpleGrantedAuthority(e.name()))
			         .collect(Collectors.toList()));
		
		
	}
	
}
