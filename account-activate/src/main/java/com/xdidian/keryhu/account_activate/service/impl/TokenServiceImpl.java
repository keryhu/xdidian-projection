package com.xdidian.keryhu.account_activate.service.impl;


import com.xdidian.keryhu.account_activate.domain.LocalActivateDto;
import com.xdidian.keryhu.account_activate.domain.PhoneActivatedToken;
import com.xdidian.keryhu.account_activate.domain.EmailActivatedToken;
import com.xdidian.keryhu.account_activate.domain.TokenType;
import com.xdidian.keryhu.account_activate.repository.EmailActivatedTokenRepository;
import com.xdidian.keryhu.account_activate.repository.PhoneActivatedTokenRepository;
import com.xdidian.keryhu.account_activate.service.ActivatedExpired;
import com.xdidian.keryhu.account_activate.service.TokenService;
import com.xdidian.keryhu.account_activate.service.VerifyToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.xdidian.keryhu.util.StringValidate.isEmail;
import static com.xdidian.keryhu.util.StringValidate.isPhone;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;



/**
 * @Description : 继承自 ActivatedTokenService 的接口服务的具体实现。
 * @date : 2016年6月18日 下午8:59:48
 * @author : keryHu keryhu@hotmail.com
 */

@Component("tokenService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j

public class TokenServiceImpl implements TokenService {

  private final EmailActivatedTokenRepository emailRepository;
  private final PhoneActivatedTokenRepository phoneRepository;
  private final VerifyToken verifyToken;
  private final ActivatedExpired activatedExpired;
  private final MessageSource messageSource;



  /**
   * 带有事物回滚的 ActivatedToken 保存到数据库
   */
  

  @Override
  @Transactional
  public EmailActivatedToken saveEmailActivatedToken(EmailActivatedToken activatedToken) {
    // TODO Auto-generated method stub
    return emailRepository.save(activatedToken);
  }


  @Override
  public PhoneActivatedToken savePhoneActivatedToken(PhoneActivatedToken activatedToken) {
    // TODO Auto-generated method stub
    return phoneRepository.save(activatedToken);
  }

  /**
   *
   * 判断url的激活时间有没有过期 验证token有没有过期，对应的有email和phone
   */
  
  @Override
  public boolean tokenExpired(String id) {
    // TODO Auto-generated method stub
    if (isEmail(id)) {
      return emailRepository.findByEmail(id).filter(e -> e.getExpiryDate() != null)
          .map(e -> LocalDateTime.now().isAfter(e.getExpiryDate())).orElse(true);
    } else if (isPhone(id)) {
      // 如果是phone对于的验证码，暂时先空着，
      return phoneRepository.findByPhone(id).filter(e -> e.getExpiryDate() != null)
          .map(e -> LocalDateTime.now().isAfter(e.getExpiryDate())).orElse(true);
    }

    // 如果两个类型都不是，那么返回true
    return true;
  }


  /**
   * 当用户前台输入email的token的时候，后台根据前台提交的token和对应的email或phone验证，是否匹配，是否在有效期内
   * 这个方法我单独验证token，resend和resignup公用的方法。所以提炼出。
   */

  @Override
  public ResponseEntity<?> checkTokenAndEmail(LocalActivateDto dto, TokenType type) {
    // TODO Auto-generated method stub
   
    
    // email 不存在于本地的情况下。
    
    String errExist =
        messageSource.getMessage("message.email.notFound", null, LocaleContextHolder.getLocale());
    
    Assert.isTrue(emailRepository.findByEmail(dto.getEmail()).isPresent(),errExist);

    
   
    // 如果token不存在于对于的email数据库
    
    String errToken =
        messageSource.getMessage("message.token.notFound", null, LocaleContextHolder.getLocale());

    // 如果token不存在于对于的email数据库
    Assert.isTrue(verifyToken.emailTokenExist(dto.getEmail(), dto.getToken(), type), errToken);

    
    /**
     * 如果token 已经过期。那么执行
     * 
     * 1 account-activated中删除此user的数据记录 2 前台根据string 提示信息 导航到该用户的注册页面（前台做）。 3
     * account-activate 发送包含email的message 出去。user-account接受到消息，删除对应的email所在的user。
     */
    
    if (tokenExpired(dto.getEmail())) {
      log.info("email为 {} 激活时间已经过期", dto.getEmail());
      String expire = activatedExpired.executeExpired(dto.getEmail());
      return ResponseEntity.ok(expire);
    }
    // 如果其它情形，暂时返回null，留作后面的程序执行。
    return null;
  }


  /**
   * 当用户前台输入phone的token的时候，后台根据前台提交的token和对应的email或phone验证，是否匹配，是否在有效期内
   * 这个方法我单独验证token，resend和resignup公用的方法。所以提炼出。
   */

  @Override
  public ResponseEntity<?> checkTokenAndPhone(LocalActivateDto dto, TokenType type) {
    // TODO Auto-generated method stub
    // 方法名字起好了，后续只好 将phone 的细节 加到 这个里面去。

    // phone 不存在于本地的情况下。
    String errExist =
        messageSource.getMessage("message.phone.notFound", null, LocaleContextHolder.getLocale());
    Assert.isTrue(phoneRepository.findByPhone(dto.getPhone()).isPresent(), errExist);

    String errToken =
        messageSource.getMessage("message.token.notFound", null, LocaleContextHolder.getLocale());

    // 如果token不存在于对于的email数据库
    Assert.isTrue(verifyToken.phoneTokenExist(dto.getPhone(), dto.getToken(), type), errToken);


    /**
     * 如果token 已经过期。那么执行
     * 
     * 1 account-activated中删除此user的数据记录 2 前台根据role导航到该用户的注册页面（前台做），如果token过期了，返回role值。然后前台再导航。 3
     * account-activate 发送包含email的message 出去。user-account接受到消息，删除对应的email所在的user。
     */
    
    if (tokenExpired(dto.getPhone())) {
      log.info("email为 {} 激活时间已经过期", dto.getPhone());
      String expire = activatedExpired.executeExpired(dto.getPhone());
      return ResponseEntity.ok(expire);
    }
    // 如果其它情形，暂时返回null，留作后面的程序执行。
    return null;
  }


}
