/**
 * @Title: ActivatedTokenServiceImpl.java
 * @Package com.xdidian.keryhu.emailActivate.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月11日 下午9:50:17
 * @version V1.0
 */
package com.xdidian.keryhu.emailActivate.service.impl;


import com.xdidian.keryhu.emailActivate.domain.ActivatedToken;
import com.xdidian.keryhu.emailActivate.repository.ActivatedTokenRepository;
import com.xdidian.keryhu.emailActivate.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description : 继承自 ActivatedTokenService 的接口服务的具体实现。
 * Date : 2016年06月18日 上午9:21
 * Author : keryHu keryhu@hotmail.com
 */
@Component("tokenService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenServiceImpl implements TokenService {

    private final ActivatedTokenRepository repository;


    /**
     * 带有事物回滚的 ActivatedToken 保存到数据库
     */
    @Override
    @Transactional
    public ActivatedToken save(ActivatedToken activatedToken) {
        // TODO Auto-generated method stub
        return repository.save(activatedToken);
    }

}
