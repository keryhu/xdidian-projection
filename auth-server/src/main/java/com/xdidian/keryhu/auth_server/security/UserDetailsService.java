package com.xdidian.keryhu.auth_server.security;

import com.xdidian.keryhu.auth_server.domain.AuthUserDto;
import com.xdidian.keryhu.auth_server.exception.BlockedException;
import com.xdidian.keryhu.auth_server.exception.EmailNotActivatedException;
import com.xdidian.keryhu.auth_server.service.LoginAttemptUserService;
import com.xdidian.keryhu.auth_server.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 
 * @Description : 关于user的操作接口
 * @date : 2016年6月18日 下午8:05:20
 * @author : keryHu keryhu@hotmail.com
 */
@Component("userDetailsService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserDetailsService
    implements org.springframework.security.core.userdetails.UserDetailsService {

  private final UserServiceImpl userService;

  private final HttpServletRequest request;

  private final LoginAttemptUserService loginAttemptUserService;

  /**
   * 根据用户名，查询数据库系统后台(user-account),是否存在此用户,同时增加了两个拦截器 email账户是否验证激活的拦截，如果没有则报错 请注意，block拦截器必须要放在
   * 查询用户系统之前，否则其功能会被spring security 代替。 一个loginAttempt 的拦截限制，一旦用户被冻结，则锁定账户多少小时
   * 目前此项目中，增加了messageSource，获取拦截的具体提示信息， 参数｛｝的格式，参考java.text.MessageFormat Object[] args 代表
   * message中 {0} ,{1},{2} 等信息的设置的 数组。 支持 Number Date Time String getMessage,的第二个参数，放上面的
   * Object[],，第三个参数放 默认的 message，意思是如果系统找不到 前面的 Object[] args，第三个参数就是替代品 请注意，spring
   * security里面的所有错误提示，都不能使用 Assert，
   */
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    
    log.info("username is : {} ",username);

    if (loginAttemptUserService.isBlocked(userService.getIP(request))) {
      log.info("您的IP已经被锁定账户了");
      throw new BlockedException("您的IP已经被锁定账户了");
    }
    
    AuthUserDto dto=userService.findByIdentity(username)
        .orElseThrow(()->new UsernameNotFoundException("您的账户不存在，请先注册！"));
    
    if(!dto.isEmailStatus()){
      throw new EmailNotActivatedException("您的email尚未激活，请查看邮箱，或垃圾邮件，或重新注册！");
    }

    return new org.springframework.security.core.userdetails.User(dto.getId(), dto.getPassword(),
        true, true, true, true, getAuthorities(dto));
 
  }

  /**
   * 获取用户的权限, 增加了roles 为null的逻辑判断。
   */
  private Collection<? extends GrantedAuthority> getAuthorities(AuthUserDto user) {

    return (user.getRoles() == null) ? null
        : (user.getRoles().stream().filter(e -> e != null)
            .map(e -> new SimpleGrantedAuthority(e.name())).collect(Collectors.toList()));


  }

}