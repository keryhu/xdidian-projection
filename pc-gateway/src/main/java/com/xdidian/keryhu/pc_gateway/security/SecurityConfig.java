package com.xdidian.keryhu.pc_gateway.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description : SSO 主方法
 * @date : 2016年6月18日 下午9:12:22
 * @author : keryHu keryhu@hotmail.com
 */
@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {


  @Override
  public void configure(HttpSecurity http) throws Exception {
    String[] notSecuredUrls={
        "/", "/signup", "/signup/**", "/favicon.ico","/login","/home","/404","/t"
    };
    http.logout().permitAll()
        .and().antMatcher("/**").authorizeRequests()
        .antMatchers(notSecuredUrls).permitAll()
        // 只有未登陆用户，才能提交注册。
        .antMatchers(HttpMethod.POST, "/property-signup/register").permitAll()
        .antMatchers(HttpMethod.GET, "/user-account/users/query/**").permitAll()
        .antMatchers(HttpMethod.GET, "/email-activate/email/**").permitAll()
        .antMatchers(HttpMethod.GET, "/recover/code").permitAll().anyRequest().authenticated().and()
        .csrf().csrfTokenRepository(csrfTokenRepository()).and()
        .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
  }

  /**
   * csrf配置
   */
  private Filter csrfHeaderFilter() {
    return new OncePerRequestFilter() {
      @Override
      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
          FilterChain filterChain) throws ServletException, IOException {
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrf != null) {
          Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
          String token = csrf.getToken();
          if (cookie == null || token != null && !token.equals(cookie.getValue())) {
            cookie = new Cookie("XSRF-TOKEN", token);
            cookie.setPath("/");
            response.addCookie(cookie);
          }
        }
        filterChain.doFilter(request, response);
      }
    };
  }

  private CsrfTokenRepository csrfTokenRepository() {
    HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
    repository.setHeaderName("X-XSRF-TOKEN");
    return repository;
  }

}
