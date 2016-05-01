/**  
* @Title: LoginAttemptIpNotFoundException.java
* @Package com.xdidian.keryhu.authserver.exception
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月1日 下午5:32:04
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.exception;

/**
* @ClassName: LoginAttemptIpNotFoundException
* @Description: TODO(当rest查询的ip地址在数据库中不存在的时候，报此exception)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月1日 下午5:32:04
*/
public class LoginAttemptIpNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = -3121256699529608596L;
	
	public LoginAttemptIpNotFoundException(String message){
		super(message);
	}

}
