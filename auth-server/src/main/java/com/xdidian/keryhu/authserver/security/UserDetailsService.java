package com.xdidian.keryhu.authserver.security;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.amazonaws.util.StringUtils;
import com.xdidian.keryhu.authserver.domain.AuthUserDto;
import com.xdidian.keryhu.authserver.domain.LoginAttemptProperties;
import com.xdidian.keryhu.authserver.exception.EmailNotActivedException;
import com.xdidian.keryhu.authserver.exception.LoginAttemptBlockedException;
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
		
	/**
	 * <p>
	 * Title: loadUserByUsername
	 * Description: 根据用户名，查询数据库系统后台(user-account),是否存在此用户,同时增加了两个拦截器
	 * email账户是否验证激活的拦截，如果没有则报错
	 * 一个loginAttempt 的拦截限制，一旦用户被冻结，则锁定账户多少小时
	 * @param username  包含uuid，email phone 的任何一种
	 * @return 当前用户的spring security details
	 * @throws UsernameNotFoundException  当前用户不存在
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		return (UserDetails) userService.findByLoginName(username).map(a -> {
			
			if (StringUtils.isNullOrEmpty(a.getId())) {
				throw new UsernameNotFoundException("新地点不存在此账户，请先注册！");
			}
			
			//查看当前账户有没有被冻结－－
			if(loginAttemptUserService.isBlocked(userService.getIP(request))){
				
				String msg=new StringBuffer("您在").append(loginAttemptProperties.getTimeOfPerid())
						.append("个小时内，登陆失败了").append(loginAttemptProperties.getMaxAttemptTimes())
						.append("次，被系统锁定了")
						.append(loginAttemptProperties.getTimeOfLock())
						.append("小时！").toString();
				log.error("系统日志错误，原因 : "+msg);
				//当用户在某个时间点类登陆错误次数过多，就冻结此账户的ip地址。
				throw new LoginAttemptBlockedException(msg);
			}
			
			//如果email未激活，后台只负责过滤，报错，让它堵塞，前台负责跳转指定的错误页面和发送message出去。
			if(!a.isEmailStatus()){
				throw new EmailNotActivedException("您的email尚未激活，请查看邮箱，或垃圾邮件，或重新注册！");					
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

		if (user.getRoles() == null) {
			return null;
		}

		return user.getRoles().stream().filter(e -> e != null).map(e -> new SimpleGrantedAuthority(e.name()))
				.collect(Collectors.toList());
	}
	
	
}
