/**
 * @Title: ResendServiceImpl.java
 * @Package com.xdidian.keryhu.emailActivate.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月17日 下午5:24:27
 * @version V1.0
 */
package com.xdidian.keryhu.emailActivate.service.impl;

import com.xdidian.keryhu.emailActivate.domain.ActivatedProperties;
import com.xdidian.keryhu.emailActivate.repository.ActivatedTokenRepository;
import com.xdidian.keryhu.emailActivate.service.ConverterUtil;
import com.xdidian.keryhu.emailActivate.service.ResendService;
import com.xdidian.keryhu.emailActivate.stream.ResendProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description : 当客户点击“重新发送激活邮件服务”的时候，需要执行的命令，其中验证url已经由其它service
 * Date : 2016年06月18日 上午8:49
 * Author : keryHu keryhu@hotmail.com
 */
@Component("resendService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@EnableConfigurationProperties(ActivatedProperties.class)
class ResendServiceImpl implements ResendService {

    private final ActivatedTokenRepository repository;
    private final ActivatedProperties activatedProperties;
    private final ResendProducer resendProducer;
    private final ConverterUtil converterUtil;

    /**
     * 当客户点击“重新发送激活邮件服务”所执行的命令
     * 1 其中验证url已经由verifyUrl完成
     * 2 查看 点击url 的冷却时间有没有 过期 方法 clickCoolingExpired
     * 如果没有过期则报告错误提示， 如果已经过期了，那么接下来判断－－
     * 3 查看发送次数有没有超过限制： 由sendMoreThanLimit 方法完成
     * 如果超过限制，直接报错提醒，如果没有超过限制，那么执行接下来的数据库更新操作，发送 激活邮件的message
     */
    @Override
    public void exec(String email) {
        // TODO Auto-generated method stub
        Assert.isTrue(clickCoolingExpired(email), "点击'再次发送'的冷却时间未到！");
        log.info("'重新发送激活邮件'的url，被点击！");

        if (clickCoolingExpired(email)) {
            Assert.isTrue(!sendMoreThanLimit(email), "您点击的太过频繁，请稍后再试！");
            if (!sendMoreThanLimit(email)) {
                //数据库处理
                repository.findByEmail(email).ifPresent(e -> {
                    e.setEmailToken(UUID.randomUUID().toString());
                    e.setReregisterToken(UUID.randomUUID().toString());
                    e.setResendToken(UUID.randomUUID().toString());
                    AtomicInteger atomic = new AtomicInteger(e.getSentTimes());
                    e.setSentTimes(atomic.incrementAndGet());
                    //设置下次  点击 “重新发送激活邮件”的冷却时间。
                    e.setSendExpiryDate(LocalDateTime.now().plusMinutes(activatedProperties.getSendCycleMinutes()));
                    repository.save(e);
                    resendProducer.send(converterUtil.activatedTokenToEmailActivatedDto.apply(e));
                });
            }
        }

    }

    /**
     * 点击“重新发送”url的冷却时间，也就是客户不能连续点击 该url
     * 每次客户点击完url，开始计时，目前设定的是2分钟，只有等过了2分钟，客户才能继续点击此url
     * 如果email不存在，则默认返回false，即假如email都不存在，那么肯定不能点击此url)
     */
    private boolean clickCoolingExpired(String email) {
        return repository.findByEmail(email).map(e ->
                e.getSendExpiryDate() == null ?
                        true :
                        LocalDateTime.now().isAfter(e.getSendExpiryDate())
        ).orElse(false);
    }


    /**
     * 查看当前email，点击“重新发送激活邮件的次数有没有超过限制
     * 如果email不存在，肯定不让点击此url，所以默认是true
     */
    private boolean sendMoreThanLimit(String email) {
        return repository.findByEmail(email).map(
                e -> e.getSentTimes() >= activatedProperties.getMaxSendTimes())
                .orElse(true);
    }

}
