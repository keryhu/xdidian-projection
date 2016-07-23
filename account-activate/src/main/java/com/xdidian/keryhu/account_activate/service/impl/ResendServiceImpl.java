package com.xdidian.keryhu.account_activate.service.impl;

import com.xdidian.keryhu.account_activate.domain.ActivatedProperties;
import com.xdidian.keryhu.account_activate.repository.EmailActivatedTokenRepository;
import com.xdidian.keryhu.account_activate.repository.PhoneActivatedTokenRepository;
import com.xdidian.keryhu.account_activate.service.ConverterUtil;
import com.xdidian.keryhu.account_activate.service.ResendService;
import com.xdidian.keryhu.account_activate.stream.ResendEmailProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static com.xdidian.keryhu.util.GeneratorRandomNum.get;
import static com.xdidian.keryhu.util.StringValidate.isEmail;
import static com.xdidian.keryhu.util.StringValidate.isPhone;

/**
 * @Description : 当客户点击“重新发送激活邮件服务”的时候，需要执行的命令，其中验证url已经由其它service
 * @date : 2016年6月18日 下午8:59:30
 * @author : keryHu keryhu@hotmail.com
 */
@Component("resendService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@EnableConfigurationProperties(ActivatedProperties.class)
class ResendServiceImpl implements ResendService {

  private final EmailActivatedTokenRepository emailRepository;
  private final PhoneActivatedTokenRepository phoneRepository;
  private final ActivatedProperties activatedProperties;
  private final ResendEmailProducer resendProducer;
  private final ConverterUtil converterUtil;

  /**
   * 当客户点击“重新发送激活邮件服务”所执行的命令 1 其中验证url已经由verifyUrl完成 2 查看 点击url 的冷却时间有没有 过期 方法 clickCoolingExpired
   * 如果没有过期则报告错误提示， 如果已经过期了，那么接下来判断－－ 3 查看发送次数有没有超过限制： 由sendMoreThanLimit 方法完成
   * 如果超过限制，直接报错提醒，如果没有超过限制，那么执行接下来的数据库更新操作，发送 激活邮件的message
   */

  @Override
  public void clickResend(String id) {
    // TODO Auto-generated method stub


    emailRepository.findByEmail(id).ifPresent(e -> {
      // 先判断冷却时间是否已到，未到则报错,之所以需要这么设置，是因为有可能是第一次存在email数据，有可能数据库还没有
      // SendExpiryDate
      boolean clickCoolingExpired =
          e.getSendExpiryDate() == null ? true : LocalDateTime.now().isAfter(e.getSendExpiryDate());

      Assert.isTrue(clickCoolingExpired, "点击'再次发送'的冷却时间未到！");
      if (e.isLocked()) {
        log.info("click is locked ! ");
        // 查看是否超时
        boolean timeout = LocalDateTime.now()
            .isAfter(e.getLockedTime().plusHours(activatedProperties.getHoursOfLocked()));

        Assert.isTrue(timeout, "您点击的太过频繁，请稍后再试！");
        // 执行默认的恢复
        e.setSentTimes(1);
        e.setFirstTime(LocalDateTime.now());
        e.setLocked(false);
        e.setLockedTime(null);
      } else {
        // 如果是第一次登录，那么设定第一次登录时间为现在
        if (e.getFirstTime() == null) {
          e.setFirstTime(LocalDateTime.now());         
        }

        // 是否已到固定的发送次数清零时间
        boolean timeToEraseSendTimes = LocalDateTime.now()
            .isAfter(e.getFirstTime().plusHours(activatedProperties.getHoursOfResetSendTimes()));

        // yes
        if (timeToEraseSendTimes) {
          e.setSentTimes(1);
          e.setFirstTime(LocalDateTime.now());
        } else {
          // 如果发送次数大于等于最低最大的发送限制－1
          if (e.getSentTimes() >= activatedProperties.getMax_send_times()-1) {
            e.setLocked(true);
            e.setLockedTime(LocalDateTime.now());

          }
          // 使用原子性＋1
          AtomicInteger atomic = new AtomicInteger(e.getSentTimes());
          e.setSentTimes(atomic.incrementAndGet());
        }

        // 调用的是自建的创建随机码的方法。
        e.setEmailToken(get(6));
        e.setRe_signup_token(UUID.randomUUID().toString());
        e.setResendToken(UUID.randomUUID().toString());
       
        // 设置下次 点击 “重新发送激活邮件”的冷却时间。
        e.setSendExpiryDate(
            LocalDateTime.now().plusMinutes(activatedProperties.getMinutesOfSendCycle()));
        emailRepository.save(e);
        resendProducer.send(converterUtil.emailActivatedTokenToAccountActivatedDto.apply(e));

      }
    });

    
    //如果是phone
    
    phoneRepository.findByPhone(id).ifPresent(e -> {
      // 先判断冷却时间是否已到，未到则报错,之所以需要这么设置，是因为有可能是第一次存在email数据，有可能数据库还没有
      // SendExpiryDate
      boolean clickCoolingExpired =
          e.getSendExpiryDate() == null ? true : LocalDateTime.now().isAfter(e.getSendExpiryDate());

      Assert.isTrue(clickCoolingExpired, "点击'再次发送'的冷却时间未到！");
      if (e.isLocked()) {
        log.info("click is locked ! ");
        // 查看是否超时
        boolean timeout = LocalDateTime.now()
            .isAfter(e.getLockedTime().plusHours(activatedProperties.getHoursOfLocked()));

        Assert.isTrue(timeout, "您点击的太过频繁，请稍后再试！");
        // 执行默认的恢复
        e.setSentTimes(1);
        e.setFirstTime(LocalDateTime.now());
        e.setLocked(false);
        e.setLockedTime(null);
      } else {
        // 如果是第一次登录，那么设定第一次登录时间为现在
        if (e.getFirstTime() == null) {
          e.setFirstTime(LocalDateTime.now());         
        }

        // 是否已到固定的发送次数清零时间
        boolean timeToEraseSendTimes = LocalDateTime.now()
            .isAfter(e.getFirstTime().plusHours(activatedProperties.getHoursOfResetSendTimes()));

        // yes
        if (timeToEraseSendTimes) {
          e.setSentTimes(1);
          e.setFirstTime(LocalDateTime.now());
        } else {
          // 如果发送次数大于等于最低最大的发送限制－1
          if (e.getSentTimes() >= activatedProperties.getMax_send_times()-1) {
            e.setLocked(true);
            e.setLockedTime(LocalDateTime.now());

          }
          // 使用原子性＋1
          AtomicInteger atomic = new AtomicInteger(e.getSentTimes());
          e.setSentTimes(atomic.incrementAndGet());
        }

        // 调用的是自建的创建随机码的方法。
        e.setPhoneToken(get(6));
        e.setRe_signup_token(UUID.randomUUID().toString());
        e.setResendToken(UUID.randomUUID().toString());
       
        // 设置下次 点击 “重新发送激活邮件”的冷却时间。
        e.setSendExpiryDate(
            LocalDateTime.now().plusMinutes(activatedProperties.getMinutesOfSendCycle()));
        phoneRepository.save(e);
        resendProducer.send(converterUtil.phoneActivatedTokenToAccountActivatedDto.apply(e));

      }
    });
  }



  /**
   * 当用户点击“重新发送”之后，将resend token 和 resignup token 一并发送到前端，用于前台页面更新
   */

  @Override
  public Map<String, String> resetUrlParams(String id) {
    // TODO Auto-generated method stub
    Map<String, String> map = new HashMap<String, String>();

    if (isEmail(id)) {
      String resend_token = emailRepository.findByEmail(id).map(e -> e.getResendToken()).orElse("");

      String signup_token = emailRepository.findByEmail(id).map(e -> e.getRe_signup_token()).orElse("");

      map.put("resendToken", resend_token);
      map.put("resignupToken", signup_token);
    }

    else if (isPhone(id)) {
      String resend_token = phoneRepository.findByPhone(id).map(e -> e.getResendToken()).orElse("");

      String signup_token = phoneRepository.findByPhone(id).map(e -> e.getRe_signup_token()).orElse("");

      map.put("resendToken", resend_token);
      map.put("resignupToken", signup_token);
    }


    return map;
  }

}
