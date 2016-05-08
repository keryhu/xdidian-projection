/**  
* @Title: ExceptionControllerAdvice.java
* @Package com.xdidian.keryhu.useraccount.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月30日 上午10:33:44
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.xdidian.keryhu.domain.ErrorMessage;
import com.xdidian.keryhu.exception.MessageNotSendException;


/**
* @ClassName: ExceptionControllerAdvice
* @Description: TODO(用户登录成功后，发送userId出去，发送失败)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月30日 上午10:33:45
*/
@ControllerAdvice
public class ExceptionControllerAdvice {
	
	 @ExceptionHandler(MessageNotSendException.class)
	 @ResponseBody
	 @ResponseStatus(HttpStatus.BAD_GATEWAY)
	 ErrorMessage handleUserNotFoundException( Throwable ex) {
	        
	        //每次定义错误的时候，需要手动加上错误的HttpStatus.NOT_FOUND.value() 的类型
	        return new ErrorMessage(HttpStatus.BAD_GATEWAY.value(),ex.getMessage());
	    }
	 
	 
	
	 

}
