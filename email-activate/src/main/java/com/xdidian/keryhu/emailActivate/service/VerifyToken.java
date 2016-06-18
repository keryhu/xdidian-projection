/**
 * @Title: VerifyToken.java
 * @Package com.xdidian.keryhu.emailActivate.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月17日 上午10:51:02
 * @version V1.0
 */
package com.xdidian.keryhu.emailActivate.service;

import com.xdidian.keryhu.emailActivate.domain.TokenType;


/**
 * Description : 验证token是否存在于数据库
 * 有3中情况的token需要被验证
 * emailToken  用于email激活时验证的token
 * resendToken 用于点击“重新发送”的token验证
 * reregisterToken  用于点击“重新注册”的token验证
 * Date : 2016年06月18日 上午9:28
 * Author : keryHu keryhu@hotmail.com
 */
public interface VerifyToken {

    /**
     * 查看email所在的数据库中，是否存在和参数一致的token
     * 根据tokenType的不同类型，分别判断对应的token，是否存在于数据库
     */
    public boolean tokenExist(String email, String token, TokenType tokenType);

}
