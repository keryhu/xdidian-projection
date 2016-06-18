package com.xdidian.keryhu.authserver.exception;


/**
 * Description : 当客户端登录时，ip被冻结出现的错误
 * Date : 2016年06月17日 下午9:05
 * Author : keryHu keryhu@hotmail.com
 */
public class BlockedException extends RuntimeException {

	private static final long serialVersionUID = 6402749556958017985L;
	
	public BlockedException(String message){
		super(message);
	}

}
