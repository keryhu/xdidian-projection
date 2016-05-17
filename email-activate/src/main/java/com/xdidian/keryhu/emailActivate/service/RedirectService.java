/**  
* @Title: RedirectService.java
* @Package com.xdidian.keryhu.emailActivate.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月17日 下午2:35:14
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xdidian.keryhu.domain.Role;

/**
* @ClassName: RedirectService
* @Description: TODO(实现页面跳转功能,这个接口只在service package下有效)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月17日 下午2:35:14
*/
public interface RedirectService {
	
	 /**
	  * 
	 * @Title: redirectLoginPage
	 * @Description: TODO(导航到login页面)
	 * @param @param email
	 * @param @param attr  导航时，转移参数的设置
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * @throws
	  */
	 ModelAndView redirectLoginPage(final String email,final RedirectAttributes attr);
	 
	 /**
	  * 
	 * @Title: redirectRegisterPage
	 * @Description: TODO(根据role不同，导航到不同的注册页面，默认是 ／register页面)
	 * @param @param email
	 * @param @param attr   导航时，转移参数设置
	 * @param @param roles
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * @throws
	  */
	 ModelAndView redirectRegisterPage(final String email,final RedirectAttributes attr,
	    		final List<Role> roles);
	 
	 
	 /**
	  * 
	 * @Title: redirectResendPage
	 * @Description: TODO(导航到resend and reregister 页面)
	 * @param @param email
	 * @param @param attr
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * @throws
	  */
     ModelAndView redirectResendPage(String email, RedirectAttributes attr);

}
