/**  
* @Title: CustomLogoutHandler.java
* @Package com.xdidian.keryhu.pcgateway.security
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月28日 下午4:12:37
* @version V1.0  
*/ 
package com.xdidian.keryhu.pcgateway.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
* @ClassName: CustomLogoutHandler
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月28日 下午4:12:37
*/
@Component
public class CustomLogoutHandler implements LogoutHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomLogoutHandler.class);

    public CustomLogoutHandler(){
        super();
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

    	logger.error("cusotomer logout handler is running ..");
    	
        if (authentication != null && authentication.getDetails() != null && authentication.isAuthenticated()) {
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
        if(httpServletRequest.getSession() != null){
            httpServletRequest.getSession().invalidate();
        }

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(null);
        SecurityContextHolder.clearContext();
    }
}