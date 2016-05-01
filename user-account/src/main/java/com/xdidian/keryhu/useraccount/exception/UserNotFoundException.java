/**  
* @Title: UserNotFoundException.java
* @Package com.xdidian.keryhu.useraccount.domain
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月30日 上午10:38:12
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.exception;

/**
* @ClassName: UserNotFoundException
* @Description: TODO(当根据identity查询数据库是，查询不到对应的user的时候，报此错误)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月30日 上午10:38:12
*/
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8378668714759997552L;

	public UserNotFoundException(String message){
		super(message);
	}
}
