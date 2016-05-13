/**  
* @Title: ExceptionControllerAdvice.java
* @Package com.xdidian.keryhu.useraccount.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月30日 上午10:33:44
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.exception;

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
	
	 
	 /**
	  * 
	 * @Title: handleEmailNotFoundException
	 * @Description: TODO(在email激活时候，如果无法找到对应的eamil数据库记录，则报告此错误)
	 * @param @param ex
	 * @param @return    设定文件
	 * @return ErrorMessage    返回类型
	 * @throws
	  */
	 @ExceptionHandler(EmailNotFoundException.class)
	 @ResponseBody
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 ErrorMessage handleEmailNotFoundException( Throwable ex) {
	        
	        //每次定义错误的时候，需要手动加上错误的HttpStatus.NOT_FOUND.value() 的类型
	        return new ErrorMessage(HttpStatus.NOT_FOUND.value(),ex.getMessage());
	    }
	 
	 
	 /**
	  * 
	 * @Title: handleTokenNotFoundException
	 * @Description: TODO(当用户点击的激活账户的url中包含的token，不存在于数据库时候，报告的错误)
	 * @param @param ex
	 * @param @return    设定文件
	 * @return ErrorMessage    返回类型
	 * @throws
	  */
	 @ExceptionHandler(TokenNotFoundException.class)
	 @ResponseBody
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 ErrorMessage handleTokenNotFoundException( Throwable ex) {
	        
	        //每次定义错误的时候，需要手动加上错误的HttpStatus.NOT_FOUND.value() 的类型
	        return new ErrorMessage(HttpStatus.NOT_FOUND.value(),ex.getMessage());
	    }
	 
	 
	 
	 /**
	  * 
	 * @Title: handleSendTimesOverException
	 * @Description: TODO(当发送次数超过规定的最大限制的时候，报告此错误。)
	 * @param @param ex
	 * @param @return    设定文件
	 * @return ErrorMessage    返回类型
	 * @throws
	  */
	 @ExceptionHandler(SendTimesOverException.class)
	 @ResponseBody
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 ErrorMessage handleSendTimesOverException( Throwable ex) {
	        
	        //每次定义错误的时候，需要手动加上错误的HttpStatus.NOT_FOUND.value() 的类型
	        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
	    }
	 
	 /**
	  * 
	 * @Title: handleCannotRestException
	 * @Description: TODO(当有些rest是无效的请求时，执行的exception)
	 * @param @param ex
	 * @param @return    设定文件
	 * @return ErrorMessage    返回类型
	 * @throws
	  */
	 @ExceptionHandler(CannotRestException.class)
	 @ResponseBody
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 ErrorMessage handleCannotRestException( Throwable ex) {
	        
	        //每次定义错误的时候，需要手动加上错误的HttpStatus.NOT_FOUND.value() 的类型
	        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
	    }

}
