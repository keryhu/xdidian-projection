package com.xdidian.keryhu.emailActivate.exception;

import com.xdidian.keryhu.domain.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 
 * @Description : 用户监测exception发生的时候，促发相应的json response
 * @date : 2016年6月18日 下午8:12:46
 * @author : keryHu keryhu@hotmail.com
 */
@ControllerAdvice
public class ExceptionControllerAdvice {


  // 捕获Assert验证的错误信息。
  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  ErrorMessage handleIllegalArgumentException(Throwable ex) {

    // 每次定义错误的时候，需要手动加上错误的HttpStatus.NOT_FOUND.value() 的类型
    return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
  }

  // 捕获emailnotfound。
  @ExceptionHandler(EmailNotFoundException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ErrorMessage handleEmailNotFoundException(Throwable ex) {

    // 每次定义错误的时候，需要手动加上错误的HttpStatus.NOT_FOUND.value() 的类型
    return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
  }

  // 捕获tokennotfound。
  @ExceptionHandler(TokenNotFoundException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ErrorMessage handleTokenNotFoundException(Throwable ex) {

    // 每次定义错误的时候，需要手动加上错误的HttpStatus.NOT_FOUND.value() 的类型
    return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
  }
}
