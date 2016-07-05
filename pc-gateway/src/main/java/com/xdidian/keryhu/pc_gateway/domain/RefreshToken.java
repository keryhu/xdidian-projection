package com.xdidian.keryhu.pc_gateway.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;

/**
 * @Description : TODO(保存客户端 angular2 refreshtoken)
 * @date : 2016年7月3日 下午2:45:16
 * @author : keryHu keryhu@hotmail.com
 */
@Data
public class RefreshToken implements Serializable {
  
  private static final long serialVersionUID = 2316470617357258043L;

  @Id
  private String id=UUID.randomUUID().toString();
 
  
  private String loginName;           //前台登录名
  private String refreshToken;        // refreshToken
  
  @DateTimeFormat(iso = ISO.DATE_TIME)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime expiredTime;  //reFreshToken 过期时间
  
 

}
