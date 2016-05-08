/**  
* @Title: EmailActivatedCodeNotExistException.java
* @Package com.xdidian.keryhu.useraccount.exception
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午9:42:26
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.exception;

/**
* @ClassName: EmailActivatedCodeNotExistException
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午9:42:26
*/
public class EmailActivatedCodeNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = -6582168084998668864L;
	
	public EmailActivatedCodeNotFoundException(String message){
		super(message);
	}

}
