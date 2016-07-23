package com.xdidian.keryhu.account_activate.service;

import org.springframework.http.ResponseEntity;

import com.xdidian.keryhu.account_activate.domain.LocalActivateDto;
import com.xdidian.keryhu.account_activate.domain.PhoneActivatedToken;
import com.xdidian.keryhu.account_activate.domain.EmailActivatedToken;
import com.xdidian.keryhu.account_activate.domain.TokenType;


/**
 * @Description : 用于email激活 token 的一些基本接口
 * @date : 2016年6月18日 下午8:57:09
 * @author : keryHu keryhu@hotmail.com
 */
public interface TokenService {

  /**
   * 保存EmailActivatedToken对象到数据库
   */
  public EmailActivatedToken saveEmailActivatedToken(EmailActivatedToken activatedToken);
  
  /**
   * 保存PhoneActivatedToken对象到数据库
   */
  public PhoneActivatedToken savePhoneActivatedToken(PhoneActivatedToken activatedToken);


  /**
   *
   * 判断url的激活时间有没有过期 验证token有没有过期，对应的有email和phone 参数id可以是email或phone
   */
  boolean tokenExpired(String id);

  /**
   * 当用户前台输入email的token的时候，后台根据前台提交的token和对应的email或phone验证，是否匹配，是否在有效期内 这是一个 confirm，resend，resignup
   * 通用的查询 email或phone 和对应的token 是否匹配的 方法。
   */
  ResponseEntity<?> checkTokenAndEmail(LocalActivateDto dto, TokenType type);

  /**
   * 当用户前台输入phone的token的时候，后台根据前台提交的token和对应的email或phone验证，是否匹配，是否在有效期内 这是一个 confirm，resend，resignup
   * 通用的查询 email或phone 和对应的token 是否匹配的 方法。
   */
  ResponseEntity<?> checkTokenAndPhone(LocalActivateDto dto, TokenType type);


}
