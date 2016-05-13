/**  
* @Title: EmailNotFoundException.java
* @Package com.xdidian.keryhu.useraccount.exception
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月6日 下午8:21:53
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.exception;

/**
* @ClassName: EmailNotFoundException
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月6日 下午8:21:53
*/
public class EmailNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 4150301770264026174L;
	
	public EmailNotFoundException(String message){
		super(message);
	}

}
