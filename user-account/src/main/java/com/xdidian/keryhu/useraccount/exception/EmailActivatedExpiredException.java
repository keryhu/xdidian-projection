/**  
* @Title: EmailActivatedExpiredException.java
* @Package com.xdidian.keryhu.useraccount.exception
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午9:49:43
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.exception;

/**
* @ClassName: EmailActivatedExpiredException
* @Description: TODO(点击邮箱中激活email的url时，后台验证此url，发现激活码已经过期，报此错误)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午9:49:43
*/
public class EmailActivatedExpiredException extends RuntimeException {

	
	private static final long serialVersionUID = 7173199640308686172L;
	
	public EmailActivatedExpiredException(String message){
		super(message);
	}

}
