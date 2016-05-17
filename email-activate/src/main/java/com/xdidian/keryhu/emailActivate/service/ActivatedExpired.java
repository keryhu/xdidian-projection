/**  
* @Title: ActivatedExpired.java
* @Package com.xdidian.keryhu.emailActivate.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月17日 下午3:36:46
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.service;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
* @ClassName: ActivatedExpired
* @Description: TODO(当email激活时，或者发送“重新激活”，或者点击“重新注册”时候，遇到激活时间已经过期，
* 需要执行的service)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月17日 下午3:36:46
*/
 public interface ActivatedExpired {

	 /**
	  * 
	 * @Title: executeExpired
	 * @Description: TODO(当激活时间过期时，需要执行的动作)
	 * @param @param email
	 * @param @param attr
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * @throws
	  */
	 ModelAndView executeExpired(final String email,final RedirectAttributes attr);
}
