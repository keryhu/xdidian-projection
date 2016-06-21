package com.xdidian.keryhu.email_activate.service;


/**
 * @Description : 点击“重新发送激活邮件”所需要的接口 此接口部分验证直接调用的 VerifyUrl service
 * @date : 2016年6月18日 下午8:56:46
 * @author : keryHu keryhu@hotmail.com
 */
public interface ResendService {

  void exec(final String email);

}
