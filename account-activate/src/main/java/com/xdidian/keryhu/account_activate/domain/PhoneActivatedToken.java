package com.xdidian.keryhu.account_activate.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * 
 * @Description : 用于phone激活,通过phone发送相关的验证码，点击“再次发送激活邮件”，“重新注册”应用的具体保存数据库的 实体类
 *   这个数据库实体，email和phone是分开的。
 * @date : 2016年6月18日 下午8:10:45
 * @author : keryHu keryhu@hotmail.com
 */
@Data
public class PhoneActivatedToken implements Serializable {

  private static final long serialVersionUID = 425412958779020624L;


  @Id
  private String id;

  private String phone; // 从user的 中取来，或者从注册对象中取来。
  
  private String phoneToken; // 用于用户点击email激活时，需要被验证的token

  private String resendToken; // 用于用户点击“重新发送验证邮件”时，需要被验证的token

  private String re_signup_token; // 用于用户点击“重新注册”时，想要被验证的token
  
  private boolean locked;       //重新发送的url是否被冻结。
  
  private LocalDateTime lockedTime; //被锁定的时间点
  
  private LocalDateTime firstTime; //第一次点击的时间点。

  @DateTimeFormat(iso = ISO.DATE_TIME)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime expiryDate; // token 过期时间

  // 邮件激活，已经重发的次数（包含当前userId下更换email的次数）
  private int sentTimes;

  @DateTimeFormat(iso = ISO.DATE_TIME)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime sendExpiryDate; // 点击重新发送过期时间

  /**
   *
   * 传递userId，初始化EmailActivatedToken
   */
  public PhoneActivatedToken() {
    this.id = UUID.randomUUID().toString();
    this.expiryDate = null;
    this.phone = null;
    this.phoneToken = null;
    this.resendToken = null;
    this.re_signup_token = null;
    this.sentTimes = 0;
    this.sendExpiryDate = null;
    this.locked=false;
    this.lockedTime=null;
    this.firstTime=null;
  }



}
