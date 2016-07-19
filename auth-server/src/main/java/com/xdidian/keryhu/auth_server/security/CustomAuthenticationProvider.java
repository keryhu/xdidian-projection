package com.xdidian.keryhu.auth_server.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.xdidian.keryhu.auth_server.domain.AuthUserDto;
import com.xdidian.keryhu.auth_server.exception.BlockedException;
import com.xdidian.keryhu.auth_server.exception.EmailNotActivatedException;
import com.xdidian.keryhu.auth_server.service.LoginAttemptUserService;
import com.xdidian.keryhu.auth_server.service.UserServiceImpl;
import com.xdidian.keryhu.auth_server.service.Utils;
import com.xdidian.keryhu.auth_server.stream.LoginSuccessProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description : TODO(使用自定义的CustomAuthenticationProvider，来判断用户的用户名，密码是否输入的正确，和记录登录失败 和成功的事件。
 * 处理的顺序，用户名是否存在－>email是否激活－>密码是否匹配。)
 * @date : 2016年7月14日 下午4:28:50
 * @author : keryHu keryhu@hotmail.com
 */
@Component("customAuthenticationProvider")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final PasswordEncoder passwordEncoder;
  private final UserDetailsService userDetailsService;
  private final UserServiceImpl userService;
  private final HttpServletRequest request;
  private final LoginSuccessProducer sendSource;
  private final MessageSource messageSource;
  private final Utils utils;
  private final LoginAttemptUserService loginAttemptUserService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String err="";

    String ip=utils.getIp(request);
    // 为了优先检查这个 ，放在这里，而不是 UserDetailsService，因为这个 优先运行。
    if (loginAttemptUserService.isBlocked(ip)) {
      log.info("您的IP已经被锁定账户了");
      throw new BlockedException(loginAttemptUserService.getBlockMsg());
    }

    UsernamePasswordAuthenticationToken token =
        (UsernamePasswordAuthenticationToken) authentication;
    String name = token.getName();
    
    UserDetails user = userDetailsService.loadUserByUsername(name);
        
    //用户点击 登录按钮后，如果email未激活，优先报错处理
    AuthUserDto dto = userService.findByIdentity(name).get();
    
    if (!dto.isEmailStatus()) {
      err=messageSource.getMessage("message.email.notActivated", null, LocaleContextHolder.getLocale());
      throw new EmailNotActivatedException(err);
    }
    
    String password = user.getPassword();
    String tokenPassword = (String) token.getCredentials();
    // 用户名不存在的情况，已经在 UserDetailsService 实现了，这里无需重复
    if (!passwordEncoder.matches(tokenPassword, password)) {
      loginAttemptUserService.loginFail(ip, name);
      throw new BadCredentialsException(userService.getLoginFailMsg(request));
    }

    

    loginAttemptUserService.loginSuccess(ip, name);
    // 登录成功后，通过spring cloud stream rabbit 将登录成功的userId发送出去，user－account接受
    sendSource.send(name);
    return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    // TODO Auto-generated method stub
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
  
  



}
