/**  
* @Title: EmailNotActivedException.java
* @Package com.xdidian.keryhu.authserver.exception
* @Description: TODO(login的时候，如果email还未激活，出现此错误)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月30日 下午4:25:56
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.exception;

/**
* @ClassName: LoginAttemptBlockedException
* @Description: TODO(用户因为过度登陆失败，而引起的exception)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月30日 下午4:25:56
*/
public class EmailNotActivedException extends RuntimeException {

	
	private static final long serialVersionUID = 8049511731463539471L;

	public EmailNotActivedException(String message){
		super(message);
	}
	
	

}
