/**  
* @Title: MessageNotSendException.java
* @Package com.xdidian.keryhu.authserver.exception
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月4日 下午3:07:43
* @version V1.0  
*/ 
package com.xdidian.keryhu.propertyregister.exception;

/**
* @ClassName: MessageNotSendException
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月4日 下午3:07:43
*/
public class MessageNotSendException extends RuntimeException {

	
	private static final long serialVersionUID = -5036240430350966956L;
	
	public MessageNotSendException(String message){
		super(message);
	}

}
