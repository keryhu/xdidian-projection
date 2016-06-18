/**
 * @Title: AuthenticationFailureListener.java
 * @Package com.xdidian.keryhu.authserver.security
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年4月29日 下午9:23:42
 * @version V1.0
 */
package com.xdidian.keryhu.authserver.security;

import com.xdidian.keryhu.authserver.service.LoginAttemptUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description : 用户监听auth－service 用户输入登陆账户，密码错误时，自动运行的class
 * @date : 2016年6月18日 下午8:00:45
 * @author : keryHu keryhu@hotmail.com
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AuthenticationFailureListener
    implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

  private final LoginAttemptUserService loginAttemptUserService;

  /**
   * 监听登陆信息输入有误时，自动运行的方法。
   */
  @Override
  public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
    // TODO Auto-generated method stub
    WebAuthenticationDetails auth = (WebAuthenticationDetails) e.getAuthentication().getDetails();

    // 获取登陆的用户名
    String loginName = e.getAuthentication().getName();

    String ip = auth.getRemoteAddress();
    log.info("用户登录失败， loginName is ： {} ", loginName);
    loginAttemptUserService.loginFail(ip, loginName);


  }

}
