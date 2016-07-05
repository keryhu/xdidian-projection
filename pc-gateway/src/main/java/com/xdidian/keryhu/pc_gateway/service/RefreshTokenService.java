package com.xdidian.keryhu.pc_gateway.service;

import com.xdidian.keryhu.pc_gateway.domain.RefreshToken;

public interface RefreshTokenService {
  
  /**
   * 验证提交的token的合法性
   * @param token
   */
  public void validate(RefreshToken token);

}
