/**
 * @Title: ConverterUtil.java
 * @Package com.xdidian.keryhu.emailActivate.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年5月11日 下午9:16:46
 * @version V1.0
 */
package com.xdidian.keryhu.email_activate.service;

import com.xdidian.keryhu.domain.EmailActivatedDto;
import com.xdidian.keryhu.email_activate.domain.ActivatedToken;

import org.springframework.stereotype.Component;

import java.util.function.Function;


/**
 * 
 * @Description : 用于email激活的时候，类型的转换
 * @date : 2016年6月18日 下午8:55:13
 * @author : keryHu keryhu@hotmail.com
 */
@Component
public class ConverterUtil {

  /**
   * 将email激活对象的Dto，转为本地的ActivatedToken，用户本地保存数据库
   */
  public Function<EmailActivatedDto, ActivatedToken> EmailActivatedDtoToActivatedToken = x -> {
    ActivatedToken activatedToken = new ActivatedToken();
    activatedToken.setEmail(x.getEmail());
    activatedToken.setExpiryDate(x.getExpireDate());
    activatedToken.setSentTimes(0);
    activatedToken.setEmailToken(x.getEmailToken());
    activatedToken.setRe_signup_token(x.getResignupToken());
    activatedToken.setResendToken(x.getResendToken());
    return activatedToken;
  };

  /**
   * 将本地的对象ActivatedToken，转为EmailActivatedDto；这个用户重新发送的时候。
   */
  public Function<ActivatedToken, EmailActivatedDto> activatedTokenToEmailActivatedDto = x -> {
    EmailActivatedDto dto = new EmailActivatedDto();
    dto.setEmail(x.getEmail());
    dto.setEmailToken(x.getEmailToken());
    dto.setResignupToken(x.getRe_signup_token());
    dto.setResendToken(x.getResendToken());
    dto.setExpireDate(x.getExpiryDate());
    return dto;
  };

}
