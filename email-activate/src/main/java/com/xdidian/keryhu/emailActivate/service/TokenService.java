/**
 * @Title: ActivatedTokenService.java
 * @Package com.xdidian.keryhu.emailActivate.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月11日 下午1:31:49
 * @version V1.0
 */
package com.xdidian.keryhu.emailActivate.service;

import com.xdidian.keryhu.emailActivate.domain.ActivatedToken;


/**
 * Description : 用于email激活 token 的一些基本接口
 * Date : 2016年06月18日 上午9:27
 * Author : keryHu keryhu@hotmail.com
 */
public interface TokenService {

    /**
     * 保存对象到数据库
     */
    public ActivatedToken save(ActivatedToken activatedToken);

}
