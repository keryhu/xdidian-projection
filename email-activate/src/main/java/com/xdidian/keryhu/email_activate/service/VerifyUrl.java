package com.xdidian.keryhu.email_activate.service;


import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xdidian.keryhu.email_activate.domain.TokenType;


/**
 * @Description : 来用核定显示在前端的Url的真伪，需要判断的细节有 email是否存在、email是否已经激活、对应的token是否存在于数据库，激活时间有没有过期)
 * @date : 2016年6月18日 下午8:57:54
 * @author : keryHu keryhu@hotmail.com
 */
public interface VerifyUrl {



  /**
   *
   * 判断url的激活时间有没有过期
   */
  boolean activatedExpired(String email);

  /**
   * 验证email token 和 相关属性是否符合要求,主要用在email验证
   */
  Object verify(String email, String token, RedirectAttributes attr, TokenType tokenType);

}
