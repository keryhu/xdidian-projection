/**  
* @Title: EmailActivatedSentTimesOverException.java
* @Package com.xdidian.keryhu.useraccount.exception
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午9:54:35
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.exception;

/**
* @ClassName: EmailActivatedSentTimesOverException
* @Description: TODO(emai 激活时候，发现激活次数已经超过规定次数时报的错误。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午9:54:35
*/
public class EmailActivatedSentTimesOverException extends RuntimeException {

	private static final long serialVersionUID = 7994089423318304208L;
	
	public EmailActivatedSentTimesOverException(String message){
		super(message);
	}

}
