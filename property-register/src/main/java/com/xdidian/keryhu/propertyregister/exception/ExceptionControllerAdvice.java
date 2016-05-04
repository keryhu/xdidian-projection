/**  
* @Title: ExceptionControllerAdvice.java
* @Package com.xdidian.keryhu.useraccount.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月30日 上午10:33:44
* @version V1.0  
*/ 
package com.xdidian.keryhu.propertyregister.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.xdidian.keryhu.domain.ErrorMessage;


/**
* @ClassName: ExceptionControllerAdvice
* @Description: TODO(物业公司注册过程中产生的exception 的 controller、 处理)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月30日 上午10:33:45
*/
@ControllerAdvice
public class ExceptionControllerAdvice {
	
	/**
	 * 
	* @Title: handleMessageNotSendException
	* @Description: TODO(当物业公司用户注册完后，需要发送message到user-account，如果发送消息没有成功，那么就会报告此错误。)
	* @param @param ex
	* @param @return    设定文件
	* @return ErrorMessage    返回类型
	* @throws
	 */
	 @ExceptionHandler(MessageNotSendException.class)
	 @ResponseBody
	 @ResponseStatus(HttpStatus.BAD_GATEWAY)
	 ErrorMessage handleMessageNotSendException( Throwable ex) {
	        
	        //每次定义错误的时候，需要手动加上错误的HttpStatus.NOT_FOUND.value() 的类型
	        return new ErrorMessage(HttpStatus.BAD_GATEWAY.value(),ex.getMessage());
	    }
	 

}
