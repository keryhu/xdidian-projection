/**  
* @Title: JwtAuthenticationEntryPoint.java
* @Package com.xdidian.keryhu.authserver.security
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月1日 上午9:41:21
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
* @ClassName: JwtAuthenticationEntryPoint
* @Description: TODO(处理没有权限的rst json回复)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月1日 上午9:41:21
*/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	/**
	* <p>Title: commence</p>
	* <p>Description: </p>
	* @param request
	* @param response
	* @param authException
	* @throws IOException
	* @throws ServletException
	* @see org.springframework.security.web.AuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	*/ 
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "您没有权限访问此网站!");
		
	}
	
	

}
