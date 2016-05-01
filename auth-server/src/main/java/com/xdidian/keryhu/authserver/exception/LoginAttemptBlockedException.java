/**  
* @Title: LoginAttemptBlockedException.java
* @Package com.xdidian.keryhu.authserver.exception
* @Description: TODO(用一句话描述该文件做什么)
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
public class LoginAttemptBlockedException extends RuntimeException {

	
	private static final long serialVersionUID = 1955314308115005191L;
	
	public LoginAttemptBlockedException(String message){
		super(message);
	}
	
	

}
