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

import com.xdidian.keryhu.useraccount.domain.ErrorMessage;

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
	 
	 /**
	  * 
	 * @Title: handlePropertySaveException
	 * @Description: TODO(物业公司注册信息，不合法的错误处理)
	 * @param @param ex
	 * @param @return    设定文件
	 * @return ErrorMessage    返回类型
	 * @throws
	  */
	 @ExceptionHandler(PropertySaveException.class)
	 @ResponseBody
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 ErrorMessage handlePropertySaveException( Throwable ex) {
	        
	        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
	    }

}
