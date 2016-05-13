/**  
* @Title: CannotRestException.java
* @Package com.xdidian.keryhu.emailActivate.exception
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月12日 下午7:43:40
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.exception;

/**
* @ClassName: CannotRestException
* @Description: TODO(当遇到非法调用rest接口的时候，出现此错误。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月12日 下午7:43:40
*/
public class CannotRestException  extends RuntimeException {

	
	private static final long serialVersionUID = 3447239278635053134L;
	
	public CannotRestException (String message){
		super(message);
	}

}
