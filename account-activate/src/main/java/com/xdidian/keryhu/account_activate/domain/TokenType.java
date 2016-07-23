package com.xdidian.keryhu.account_activate.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @Description : email或phone激活时，共有3个token类型，需要前台页面输入验证码的，为 confirm类型
 *  用户点击激活url中含有的token resend 用于 用户点击
 *              “重新发送”url中含有的需要被验证的token reregister 用户点击“重新注册”url中含有的需要被验证的token
 * @date : 2016年6月18日 下午8:11:36
 * @author : keryHu keryhu@hotmail.com
 */
public enum TokenType {

  CONFIRM, // 前台输入email或phone 的验证码，
  RESEND, // 用于 用户点击 “重新发送”url中含有的需要被验证的token
  RESIGNUP; // 用户点击“重新注册”url中含有的需要被验证的token

  private static Map<String, TokenType> tokenMap = new HashMap<String, TokenType>(3);

  static {
    tokenMap.put("CONFIRM", CONFIRM);
    tokenMap.put("RESEND", RESEND);
    tokenMap.put("RESIGNUP", RESIGNUP);
  }

  @JsonCreator
  public static TokenType forValue(String value) {
    return tokenMap.get(value);
  }

  @JsonValue
  public String toValue() {
    for (Entry<String, TokenType> token : tokenMap.entrySet()) {
      if (token.getValue() == this)
        return token.getKey();
    }
    return null;
  }
}
