/**  
* @Title: CustomAuthenticationFailureHandler.java
* @Package com.xdidian.keryhu.authserver.security
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月16日 上午10:47:39
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: CustomAuthenticationFailureHandler
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月16日 上午10:47:39
*/
@Component
@Slf4j
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	@Autowired
    private MessageSource messageSource;
	
	@Override
    public void onAuthenticationFailure(final HttpServletRequest request, 
    		final HttpServletResponse response, final AuthenticationException exception) 
    				throws IOException, ServletException {
        setDefaultFailureUrl("/login?error");

        super.onAuthenticationFailure(request, response, exception);

        
        if(exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
            log.info("遇到了 badCreadentials 错误");
            String x="jsdjsjdkskdk";
            Object[] args={x};
            String err= messageSource.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", 
					args, LocaleContextHolder.getLocale());
            //request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, err);
      }
        
      
        
    }


}
