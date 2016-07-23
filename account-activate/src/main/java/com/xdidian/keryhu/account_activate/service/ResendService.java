package com.xdidian.keryhu.account_activate.service;

import java.util.Map;

/**
 * @Description : 点击“重新发送激活邮件”所需要的接口 此接口部分验证直接调用的 VerifyUrl service
 * @date : 2016年6月18日 下午8:56:46
 * @author : keryHu keryhu@hotmail.com
 */
public interface ResendService {

  /**
   * 
   * 当用户点击“重新发送”，所验证的动作，包含冷却时间是否已经到。
   */
  void clickResend(final String email);
  
  /**
   * 当用户点击“重新发送”之后，页面需要重新设置 resend和resignup url，
   */
  Map<String,String> resetUrlParams(final String email);

}
