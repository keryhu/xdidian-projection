package com.xdidian.keryhu.emailActivate.service.impl;

import com.xdidian.keryhu.emailActivate.repository.ActivatedTokenRepository;
import com.xdidian.keryhu.emailActivate.service.ActivatedConfirm;
import com.xdidian.keryhu.emailActivate.stream.ActivatedSuccessProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Description : 用户点击 激活邮箱的url，当验证完url是合法的，即－－ email存在，未激活，且未过期的情况下，后续需要执行的命令,除了页面跳转 login任务
 *              包含email activated 删除此email所在的数据库记录 发送包含email的激活成功的message出去
 * @date : 2016年6月18日 下午8:58:33
 * @author : keryHu keryhu@hotmail.com
 */
@Component("activatedConfirm")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ActivatedConfirmImpl implements ActivatedConfirm {

  private final ActivatedSuccessProducer activatedSuccessProducer;
  private final ActivatedTokenRepository repository;


  @Override
  public void exceptRedirect(String email) {
    // TODO Auto-generated method stub
    repository.deleteByEmail(email);
    log.info("已经从数据库中成功删除了email-activate，{} 所在的数据", email);
    // 发送包含email激活成功的message出去，
    activatedSuccessProducer.send(email);

  }

}
