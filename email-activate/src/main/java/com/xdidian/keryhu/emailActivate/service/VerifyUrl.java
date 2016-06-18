/**  
* @Title: VerifyUrl.java
* @Package com.xdidian.keryhu.emailActivate.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月17日 上午10:46:58
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.service;


import com.xdidian.keryhu.emailActivate.domain.TokenType;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Description : 来用核定显示在前端的Url的真伪，需要判断的细节有
 * email是否存在、email是否已经激活、对应的token是否存在于数据库，激活时间有没有过期)
 * Date : 2016年06月18日 上午9:28
 * Author : keryHu keryhu@hotmail.com
 */
public interface VerifyUrl {
	

	
	/**
	 *
	 * 判断url的激活时间有没有过期
	 */
    boolean activatedExpired(String email);

	/**
	 * 验证email token 和 相关属性是否符合要求,主要用在email验证
	*/
	Object verify(String email, String token, RedirectAttributes attr, 
			TokenType tokenType);

}
