/**  
* @Title: EmailNotFoundException.java
* @Package com.xdidian.keryhu.emailActivate.exception
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月14日 下午9:59:52
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.exception;

/**
* @ClassName: EmailNotFoundException
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月14日 下午9:59:52
*/
public class EmailNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2407783389846201037L;
	
	public EmailNotFoundException(String message){
		super(message);
	}

}
