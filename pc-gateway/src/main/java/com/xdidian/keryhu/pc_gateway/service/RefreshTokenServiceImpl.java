package com.xdidian.keryhu.pc_gateway.service;

import static com.xdidian.keryhu.util.StringValidate.*;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.xdidian.keryhu.pc_gateway.domain.RefreshToken;

@Component("refreshTokenService")
public class RefreshTokenServiceImpl implements RefreshTokenService {

  @Override
  public void validate(RefreshToken token) {
    // TODO Auto-generated method stub
    Assert.isTrue(isEmail(token.getLoginName()) || isPhone(token.getLoginName()),
        "loginName格式不正确！");
    
    Assert.hasText(token.getRefreshToken(), "RefreshToken不能为空");
    

  }


}
