/**
 * @Title: CustomAuthSuccessHandler.java
 * @Package com.xdidian.keryhu.authserver.security
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年4月27日 下午8:17:44
 * @version V1.0
 */
package com.xdidian.keryhu.auth_server.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.xdidian.keryhu.auth_server.domain.Host;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 
 * @Description : 自定义用户成功登陆有，页面跳转component，一般用户只跳转之前请求失败的页面， 
 * 但是admin用户，登陆后，直接跳转admin 页面,必须要使用 @Qualifier，要不然会出错，因为不止一个type符合要求
 * @date : 2016年6月18日 下午8:02:17
 * @author : keryHu keryhu@hotmail.com
 */
@Component
@Slf4j
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
  
  private final Host host;
  
  @Autowired
  public CustomAuthSuccessHandler(@Qualifier("hostProperty")Host host){
    this.host=host;
  }


  /**
   * 用户登陆成功后的执行的方法。
   */
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    // TODO Auto-generated method stub

    // 找出含有admin权限的用户
    Assert.notNull(authentication.getAuthorities(), "需要转换的权限不能为空！");

    boolean isAdminOrProperty = authentication.getAuthorities().stream().anyMatch(
        e -> e.getAuthority().equals("ROLE_ADMIN")||e.getAuthority().equals("ROLE_PROPERTY"));

    // 暂时将role 为admin 或 Property 的用户登录成功后的 页面设置为 ／property
    String propertyUrl = new StringBuffer(host.getHostName()).append(":8080/property").toString();

     if (isAdminOrProperty) {
     log.info("您是admin或property，将为您跳转property 页面");
    response.sendRedirect(propertyUrl);
    }

  }

}
