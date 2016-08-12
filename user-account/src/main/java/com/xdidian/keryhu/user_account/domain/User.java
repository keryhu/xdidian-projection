package com.xdidian.keryhu.user_account.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.xdidian.keryhu.domain.Role;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Description : 此User是user account 存储数据库的user 模型，不对外公开。
 * @date : 2016年6月18日 下午9:19:42
 * @author : keryHu keryhu@hotmail.com
 */
@Data
@ToString(exclude = "password")
public class User implements Serializable {

  private static final long serialVersionUID = -8881254891826611809L;

  @Id
  private String id;

  @Indexed(unique = true)
  private String email; // 注册邮箱

  @Indexed(unique = true)
  private String phone; // 注册手机

  @JsonIgnore
  // 在spring rest 数据库查询中不显示密码
  @RestResource(exported = false)
  private String password; // 为加密后的密码


  // 一定要json 序列化 java8 time ,注意json序列号和反序列化中的LocalDateTimeDeserializer，
  // 和LocalDateDeserializer的区别
  @DateTimeFormat(iso = ISO.DATE_TIME)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime registerTime; // 用户注册时间


  @DateTimeFormat(iso = ISO.DATE_TIME)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime lastLoginTime; // 记录上次登录时间


  private List<Role> roles = new ArrayList<Role>(); // 权限

  // 邮箱是否被激活 ，之所以要加上index索引，方便定时清理未激活的账户
  @Indexed
  private boolean emailStatus;
  
  //手机是否被激活，之所以要加上index索引，方便定时清理未激活的账户
  @Indexed
  private boolean phoneStatus;
  
  //user 对应的company 的id，如果这个id为null或 "",那么就证明他目前没有公司。如果companyId为uuid，那么就证明现在有公司
  private String companyId;
  
  //增加用户头像。
  
  private String name; // 对外公布的名字。
  
  // 用户的生日，只需要月份－日期，其中年份统一为0000
  @DateTimeFormat(iso = ISO.DATE_TIME)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  @Indexed
  private LocalDate birthday;


  // 用户新注册时候的时候，自动生成Id,其它的变量都为null
  public User() {
    this.id = UUID.randomUUID().toString();
    this.email = null;
    this.password = null;
    this.phone = null;
    this.registerTime = null;
    this.emailStatus = false;
    this.phoneStatus=false;
    this.companyId=null;
    this.name=null;
    this.birthday=null;
    // roles 已经设置了默认值。
  }


  public void addRole(Role role) {
    this.roles.add(role);
  }

  public boolean hasRole(Role role) {
    return (this.roles.contains(role));
  }

  public void removeRole(Role role) {
    this.roles = this.roles.stream()
        // 将符合条件的相同role去掉
        .filter(e -> !e.equals(role)).collect(Collectors.toList());
  }

}
