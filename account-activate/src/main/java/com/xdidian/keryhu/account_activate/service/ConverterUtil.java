/**
 * @Title: ConverterUtil.java
 * @Package com.xdidian.keryhu.emailActivate.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年5月11日 下午9:16:46
 * @version V1.0
 */
package com.xdidian.keryhu.account_activate.service;

import com.xdidian.keryhu.account_activate.domain.ActivatedProperties;
import com.xdidian.keryhu.account_activate.domain.EmailActivatedToken;
import com.xdidian.keryhu.account_activate.domain.PhoneActivatedToken;
import com.xdidian.keryhu.domain.AccountActivatedDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;


/**
 * 
 * @Description : 用于email激活的时候，类型的转换
 * @date : 2016年6月18日 下午8:55:13
 * @author : keryHu keryhu@hotmail.com
 */
@Component
@EnableConfigurationProperties(ActivatedProperties.class)
public class ConverterUtil {
	
	@Autowired
	private  ActivatedProperties activatedProperties;

  /**
   * 将message中传递的AccountActivatedDto转为email激活对象的Dto，转为本地的ActivatedToken，用户本地保存数据库
   */
  public Function<AccountActivatedDto, EmailActivatedToken> AccountActivatedDtoToEmailActivatedToken = x -> {
    EmailActivatedToken activatedToken = new EmailActivatedToken();
    activatedToken.setEmail(x.getId());
    activatedToken.setExpiryDate(x.getExpireDate());
    activatedToken.setSentTimes(0);
    activatedToken.setEmailToken(x.getToken());
    activatedToken.setRe_signup_token(x.getResignupToken());
 // 设置下次 点击 “重新发送激活邮件”的冷却时间。
    activatedToken.setSendExpiryDate(
        LocalDateTime.now().plusMinutes(activatedProperties.getMinutesOfSendCycle()));
    activatedToken.setResendToken(x.getResendToken());
    return activatedToken;
  };
  
  /**
   * 将message中传递的AccountActivatedDto转为phone激活对象的Dto，转为本地的ActivatedToken，用户本地保存数据库
   */
  public Function<AccountActivatedDto, PhoneActivatedToken> AccountActivatedDtoToPhoneActivatedToken = x -> {
    PhoneActivatedToken activatedToken = new PhoneActivatedToken();
    activatedToken.setPhone(x.getId());
    activatedToken.setExpiryDate(x.getExpireDate());
    activatedToken.setSentTimes(0);
    activatedToken.setPhoneToken(x.getToken());
    activatedToken.setRe_signup_token(x.getResignupToken());
    activatedToken.setResendToken(x.getResendToken());
    return activatedToken;
  };

  /**
   * 将本地的对象emailActivatedToken，转为EmailActivatedDto；这个用户重新发送的时候。
   */
  public Function<EmailActivatedToken, AccountActivatedDto> emailActivatedTokenToAccountActivatedDto = x -> {
    AccountActivatedDto dto = new AccountActivatedDto();
    dto.setId(x.getEmail());
    dto.setToken(x.getEmailToken());
    dto.setResignupToken(x.getRe_signup_token());
    dto.setResendToken(x.getResendToken());
    dto.setExpireDate(x.getExpiryDate());
    return dto;
  };

  /**
   * 将本地的对象phoneActivatedToken，转为EmailActivatedDto；这个用户重新发送的时候。
   */
  public Function<PhoneActivatedToken, AccountActivatedDto> phoneActivatedTokenToAccountActivatedDto = x -> {
    AccountActivatedDto dto = new AccountActivatedDto();
    dto.setId(x.getPhone());
    dto.setToken(x.getPhoneToken());
    dto.setResignupToken(x.getRe_signup_token());
    dto.setResendToken(x.getResendToken());
    dto.setExpireDate(x.getExpiryDate());
    return dto;
  };

}
