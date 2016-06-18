/**
 * @Title: VerifyTokenImpl.java
 * @Package com.xdidian.keryhu.emailActivate.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月17日 上午11:41:01
 * @version V1.0
 */
package com.xdidian.keryhu.emailActivate.service.impl;

import com.xdidian.keryhu.emailActivate.domain.TokenType;
import com.xdidian.keryhu.emailActivate.repository.ActivatedTokenRepository;
import com.xdidian.keryhu.emailActivate.service.VerifyToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description : VerifyToken 具体实现方法，根据三种不同的tokenType类型，分别判断
 * 传递进来的token是否存在对应的email数据库中
 * Date : 2016年06月18日 上午9:23
 * Author : keryHu keryhu@hotmail.com
 */
@Component("verifyToken")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class VerifyTokenImpl implements VerifyToken {

    private final ActivatedTokenRepository repository;

    /**
     * VerifyToken 具体实现方法，根据三种不同的tokenType类型，分别判断
     * 传递进来的token是否存在对应的email数据库中)
     * 如果email不存在，默认返回false
     */
    @Override
    public boolean tokenExist(String email, String token, TokenType tokenType) {
        // TODO Auto-generated method stub

        return repository.findByEmail(email).map(e -> {

            boolean result = false;

            switch (tokenType) {
                case CONFIRM: {
                    result = e.getEmailToken() == null || e.getEmailToken().isEmpty() ?
                            false :
                            e.getEmailToken().equals(token);
                    break;
                }
                case RESEND: {

                    result = e.getResendToken() == null || e.getResendToken().isEmpty() ?
                            false :
                            e.getResendToken().equals(token);
                    break;
                }

                case REREGISTER: {
                    result = e.getReregisterToken() == null || e.getReregisterToken().isEmpty() ?
                            false :
                            e.getReregisterToken().equals(token);
                    break;
                }

                default:
                    break;
            }

            log.info("token is exist ? {} ", result);
            return result;

        }).orElse(false);

    }

}
