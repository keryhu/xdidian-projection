/**  
* @Title: ExceptionControllerAdvice.java
* @Package com.xdidian.keryhu.useraccount.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月30日 上午10:33:44
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.xdidian.keryhu.domain.ErrorMessage;



/**
* @ClassName: ExceptionControllerAdvice
* @Description: TODO(用户监测exception发生的时候，促发相应的json response)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月30日 上午10:33:45
*/
@ControllerAdvice
public class ExceptionControllerAdvice {
	
	
	 @ExceptionHandler(UserNotFoundException.class)
	 @ResponseBody
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 ErrorMessage handleUserNotFoundException( Throwable ex) {
	        
	        //每次定义错误的时候，需要手动加上错误的HttpStatus.NOT_FOUND.value() 的类型
	        return new ErrorMessage(HttpStatus.NOT_FOUND.value(),ex.getMessage());
	    }
	 
	 
	 @ExceptionHandler(EmailNotFoundException.class)
	 @ResponseBody
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 ErrorMessage handleEmailNotFoundException( Throwable ex) {
	        
	        //每次定义错误的时候，需要手动加上错误的HttpStatus.NOT_FOUND.value() 的类型
	        return new ErrorMessage(HttpStatus.NOT_FOUND.value(),ex.getMessage());
	    }
	//捕获Assert验证的错误信息。
	 @ExceptionHandler(IllegalArgumentException.class)
	 @ResponseBody
	 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 ErrorMessage handleIllegalArgumentException( Throwable ex) {
	        
	        //每次定义错误的时候，需要手动加上错误的HttpStatus.NOT_FOUND.value() 的类型
	        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage());
	    }	 	 

}
