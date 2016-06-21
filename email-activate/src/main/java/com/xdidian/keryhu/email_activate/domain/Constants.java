package com.xdidian.keryhu.email_activate.domain;


/**
 * 
 * @Description : 设定 常量
 * @date : 2016年6月18日 下午8:11:04
 * @author : keryHu keryhu@hotmail.com
 */
public final class Constants {

  private Constants() {}

  // 此常量表示： email 未激活，token存在于数据库，且激活时间未过期。
  public static final String EMAIL_NOT_ACTIVATED_AND_TOKEN_EXIST_AND_NOT_EXPIRED =
      "emailNotActivatedAndTokenExistAndNotExpired";

}
