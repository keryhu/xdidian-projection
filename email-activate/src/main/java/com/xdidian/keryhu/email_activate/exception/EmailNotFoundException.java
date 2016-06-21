package com.xdidian.keryhu.email_activate.exception;

/**
 * 
 * @Description : email找不到发生的错误
 * @date : 2016年6月18日 下午8:12:25
 * @author : keryHu keryhu@hotmail.com
 */
public class EmailNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -2407783389846201037L;

  public EmailNotFoundException(String message) {
    super(message);
  }

}
