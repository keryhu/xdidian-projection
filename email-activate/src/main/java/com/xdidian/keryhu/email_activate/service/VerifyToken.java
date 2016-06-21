package com.xdidian.keryhu.email_activate.service;

import com.xdidian.keryhu.email_activate.domain.TokenType;



/**
 * @Description : 验证token是否存在于数据库 有3中情况的token需要被验证 emailToken 用于email激活时验证的token resendToken
 *              用于点击“重新发送”的token验证 reregisterToken 用于点击“重新注册”的token验证
 * @date : 2016年6月18日 下午8:57:34
 * @author : keryHu keryhu@hotmail.com
 */
public interface VerifyToken {

  /**
   * 查看email所在的数据库中，是否存在和参数一致的token 根据tokenType的不同类型，分别判断对应的token，是否存在于数据库
   */
  public boolean tokenExist(String email, String token, TokenType tokenType);

}
