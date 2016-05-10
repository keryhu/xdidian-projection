/**  
* @Title: EmailActivatedTooOftenException.java
* @Package com.xdidian.keryhu.authserver.exception
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月9日 下午10:12:09
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.exception;

/**
* @ClassName: EmailActivatedTooOftenException
* @Description: TODO(当email激活邮件发送过于频繁时，报告的此错误)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月9日 下午10:12:09
*/
public class EmailActivatedTooOftenException extends RuntimeException {

	private static final long serialVersionUID = 6071288650610179310L;
	
	public EmailActivatedTooOftenException(String message){
		super(message);
	}

}
