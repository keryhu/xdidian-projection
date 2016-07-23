package com.xdidian.keryhu.account_activate.service.impl;

import com.xdidian.keryhu.account_activate.repository.EmailActivatedTokenRepository;
import com.xdidian.keryhu.account_activate.repository.PhoneActivatedTokenRepository;
import com.xdidian.keryhu.account_activate.service.ActivatedConfirm;
import com.xdidian.keryhu.account_activate.stream.ActivatedSuccessProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.xdidian.keryhu.util.StringValidate.isEmail;
import static com.xdidian.keryhu.util.StringValidate.isPhone;


/**              
 *  @Description  用户输入email或phone验证码，验证成功后，所做的事情
 *              1 前台导航到login 页面。
 *              2 account-activated发送包含id(包含了email或phone）的message（主题激活成功）
 *              3 account-activated删除此user数据库记录
 *              4 user-account接受到消息，查询email或phone所在的user，修改eamilStatus＝true或phoneStatus
 * @date : 2016年6月18日 下午8:54:41
 * @author : keryHu keryhu@hotmail.com
 */

@Component("activatedConfirm")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ActivatedConfirmImpl implements ActivatedConfirm {

  private final ActivatedSuccessProducer activatedSuccessProducer;
  private final EmailActivatedTokenRepository emailRepository;
  private final PhoneActivatedTokenRepository phoneRepository;


  
  @Override
  public void exec(String id) {
    // TODO Auto-generated method stub
    if(isEmail(id)){
      emailRepository.findByEmail(id).ifPresent(e->emailRepository.delete(e));
    }
     if(isPhone(id)){
       phoneRepository.findByPhone(id).ifPresent(e->phoneRepository.delete(e));
     }
    
    log.info("已经从数据库中成功删除了email-activate，{} 所在的数据", id);
    // 发送包含email激活成功的message出去，
    activatedSuccessProducer.send(id);

  }

}
