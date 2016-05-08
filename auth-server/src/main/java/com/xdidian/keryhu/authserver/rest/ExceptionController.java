/**  
* @Title: ExceptionController.java
* @Package com.xdidian.keryhu.authserver.rest
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月8日 上午11:46:43
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.rest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.esotericsoftware.minlog.Log;
import com.xdidian.keryhu.authserver.exception.EmailActivatedSentTimesOverException;

/**
* @ClassName: ExceptionController
* @Description: TODO(专门用来处理单独的exception controller)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月8日 上午11:46:43
*/
@RestController
public class ExceptionController {
	
	 @ExceptionHandler(EmailActivatedSentTimesOverException.class)
	 public String handleEmailActivatedSentTimesOverException( Throwable ex,RedirectAttributes attr) {
	        
	     //定义一个错误页面，专门显示－－您的email还未激活，请查看邮箱，或再次发送激活邮件，或者更换email
		  Log.info("auth-server is handler EmailActivatedSentTimesOverException  ");
		  attr.addAttribute("emailActivatedSentTimesOver", ex.getMessage());
		 
	      return "redirect:/login";
	    }

}
