package com.xdidian.keryhu.emailActivate.exception;



/**
 * 
 * @Description : 当token不存在的时候，报告的错误
 * @date : 2016年6月18日 下午8:13:02
 * @author : keryHu keryhu@hotmail.com
 */
public class TokenNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -3243973140485053254L;

  public TokenNotFoundException(String message) {
    super(message);
  }

}
