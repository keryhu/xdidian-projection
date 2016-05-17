/**  
* @Title: VerifyUrl.java
* @Package com.xdidian.keryhu.emailActivate.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月17日 上午10:46:58
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.service;


import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.xdidian.keryhu.emailActivate.domain.TokenType;

/**
* @ClassName: VerifyUrl
* @Description: TODO(来用核定显示在前端的Url的真伪，需要判断的细节有
* email是否存在、email是否已经激活、对应的token是否存在于数据库，激活时间有没有过期)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月17日 上午10:46:58
*/
public interface VerifyUrl {
	

	
	/**
	 * 
	* @Title: activatedExpired
	* @Description: TODO(判断url的激活时间有没有过期)
	* @param @param email
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
    boolean activatedExpired(String email);

	/**
	* @Title: verify
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param email
	* @param @param token
	* @param @param attr
	* @param @param tokenType
	* @param @return    设定文件
	* @return Object    返回类型
	* @throws
	*/
	Object verify(String email, String token, RedirectAttributes attr, 
			TokenType tokenType);

}
