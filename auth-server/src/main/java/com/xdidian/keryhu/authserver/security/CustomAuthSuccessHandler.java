/**  
* @Title: CustomAuthSuccessHandler.java
* @Package com.xdidian.keryhu.authserver.security
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月27日 下午8:17:44
* @version V1.0  
*/
package com.xdidian.keryhu.authserver.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @ClassName: CustomAuthSuccessHandler
 * @Description: TODO(自定义用户成功登陆有，页面跳转component，一般用户只跳转之前请求失败的页面，
 *               但是admin用户，登陆后，直接跳转admin 页面)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年4月27日 下午8:17:44
 */
@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthSuccessHandler.class);

	/**
	 * <p>
	 * Title: onAuthenticationSuccess
	 * </p>
	 * <p>
	 * Description: 用户登陆成功后的执行的方法。
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws IOException
	 * @throws ServletException
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub

		// 找出含有admin权限的用户
		boolean b = authentication.getAuthorities().stream().filter(e -> e != null)
				.anyMatch(e -> e.getAuthority().equals("ROLE_ADMIN"));
		
		String adminUrl=new StringBuffer(request.getServerName())
				.append(":8080/admin").toString();

		//if (b) {
			//logger.info("您是admin，将为您跳转 xx 页面，等正式上线时候，定义url，目前获取到的admin url is ： "+adminUrl);
			// response.sendRedirect(adminUrl);
	//	}

	}

}
