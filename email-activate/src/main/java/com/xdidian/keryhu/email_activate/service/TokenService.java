package com.xdidian.keryhu.email_activate.service;

import com.xdidian.keryhu.email_activate.domain.ActivatedToken;


/**
 * @Description : 用于email激活 token 的一些基本接口
 * @date : 2016年6月18日 下午8:57:09
 * @author : keryHu keryhu@hotmail.com
 */
public interface TokenService {

  /**
   * 保存对象到数据库
   */
  public ActivatedToken save(ActivatedToken activatedToken);

}
