package com.xdidian.keryhu.account_activate.service.impl;

import com.xdidian.keryhu.account_activate.repository.EmailActivatedTokenRepository;
import com.xdidian.keryhu.account_activate.repository.PhoneActivatedTokenRepository;
import com.xdidian.keryhu.account_activate.service.ActivatedExpired;
import com.xdidian.keryhu.account_activate.stream.RemoveUserProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.xdidian.keryhu.util.StringValidate.isEmail;
import static com.xdidian.keryhu.util.StringValidate.isPhone;
import static com.xdidian.keryhu.util.Constants.EMAIL_ACTIVATE_EXPIRED; 


/**
 * @Description : 当email或phone验证码激活时，“重新发送激活邮件或短信”，“再次注册”时，
 * 如果email或phone激活时间已经过期，执行的方法
 * @date : 2016年6月18日 下午8:58:52
 * @author : keryHu keryhu@hotmail.com
 */

@Component("activatedExpired")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j

public class ActivatedExpiredImpl implements ActivatedExpired {

  private final EmailActivatedTokenRepository emailRepository;
  private final PhoneActivatedTokenRepository phoneRepository;
  private final RemoveUserProducer removeUserProducer;

  /**
   * 都是用于前台注册的时候，验证手机号和email
   * 该方法执行主要分为3点 
   * 1 直接翻译激活过期的 string 给前台。
   * 2 删除此email或phone所在的 ActivatedToken
   * 数据库记录，由repository 完成 3 spring cloud stream发送删除此email所在的 user 数据库记录的message出去
   * </p>
   */
  
  @Override
  public String executeExpired(String id) {
    // TODO Auto-generated method stub
    
    
    removeUserProducer.send(id);
    
    if(isEmail(id)){
      emailRepository.findByEmail(id).ifPresent(e->emailRepository.delete(e));
    }
     if(isPhone(id)){
       phoneRepository.findByPhone(id).ifPresent(e->phoneRepository.delete(e));
     }
    
    log.info("ActivatedToken 删除 email 为 {} 的数据库记录 。", id);
    
    return EMAIL_ACTIVATE_EXPIRED;
  }

}
