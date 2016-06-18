package com.xdidian.keryhu.pcgateway.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @Description : 自定义logout方法
 * @date : 2016年6月18日 下午9:12:04
 * @author : keryHu keryhu@hotmail.com
 */
@Component
@Slf4j
public class CustomLogoutHandler implements LogoutHandler {


  public CustomLogoutHandler() {
    super();
  }

  @Override
  public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      Authentication authentication) {

    log.error("cusotomer logout handler is running ..");

    if (authentication != null && authentication.getDetails() != null
        && authentication.isAuthenticated()) {
      authentication.setAuthenticated(false);
    }

    Cookie[] cookies = httpServletRequest.getCookies();
    if (cookies != null && cookies.length > 0) {
      for (int i = 0; i < cookies.length; i++) {
        cookies[i].setValue("");
        cookies[i].setPath(cookies[i].getPath());
        cookies[i].setMaxAge(0);
        httpServletResponse.addCookie(cookies[i]);
      }
    }
    if (httpServletRequest.getSession() != null) {
      httpServletRequest.getSession().invalidate();
    }

    SecurityContext context = SecurityContextHolder.getContext();
    context.setAuthentication(null);
    SecurityContextHolder.clearContext();
  }
}
