package com.xdidian.keryhu.authserver.exception;

/**
 * 
* @ClassName: BlockedException
* @Description: TODO(当客户端登录时，ip被冻结出现的错误)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月15日 上午11:05:25
 */
public class EmailNotActivatedException extends RuntimeException {

	private static final long serialVersionUID = 6402749550958017985L;
	
	public EmailNotActivatedException(String message){
		super(message);
	}

}
