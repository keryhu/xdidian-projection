package com.xdidian.keryhu.passwordReset.domain;

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
 * @Description : 忘记密码，重设密码时，需要用到存储在数据库中的带有随机token的 对象
 * @date : 2016年6月18日 下午9:08:44
 * @author : keryHu keryhu@hotmail.com
 */
@Data
public class PasswordToken implements Serializable {

  private static final long serialVersionUID = 1687892710600393359L;

  @Id
  private String id;

  private String passwordToken; // 重设密码时，需要设置的随机码

  private String resendToken; // 跟随重新发送邮件的token

  private String userId; // user-accouont中的id，用途是关联此domain 与 user

  @DateTimeFormat(iso = ISO.DATE_TIME)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime expiryDate; // token 过期时间

  /**
   * 对象初始化时候的设置
   */
  public PasswordToken() {
    this.id = UUID.randomUUID().toString();
    this.passwordToken = null;
    this.resendToken = null;
    this.expiryDate = null;
    this.userId = null;
  }


}
