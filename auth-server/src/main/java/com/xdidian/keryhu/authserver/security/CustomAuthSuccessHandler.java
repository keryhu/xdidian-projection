/**  
* @Title: CustomAuthSuccessHandler.java
* @Package com.xdidian.keryhu.authserver.security
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月27日 下午8:17:44
* @version V1.0  
*/
package com.xdidian.keryhu.authserver.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Description : 自定义用户成功登陆有，页面跳转component，一般用户只跳转之前请求失败的页面，
 *               但是admin用户，登陆后，直接跳转admin 页面
 * Date : 2016年06月17日 下午9:13
 * Author : keryHu keryhu@hotmail.com
 */
@Component
@Slf4j
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {


	/**
	 * 用户登陆成功后的执行的方法。
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub

		// 找出含有admin权限的用户
		Assert.notNull(authentication.getAuthorities(), "需要转换的权限不能为空！");
		
		boolean b = authentication.getAuthorities().stream() 
				          .anyMatch(e -> e.getAuthority().equals("ROLE_ADMIN"));
		
		String adminUrl=new StringBuffer(request.getServerName())
				.append(":8080/admin").toString();

		//if (b) {
			//logger.info("您是admin，将为您跳转 xx 页面，等正式上线时候，定义url，目前获取到的admin url is ： "+adminUrl);
			// response.sendRedirect(adminUrl);
	//	}

	}

}
