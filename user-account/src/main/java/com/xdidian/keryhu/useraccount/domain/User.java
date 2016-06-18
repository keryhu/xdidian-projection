package com.xdidian.keryhu.useraccount.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
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

  private String directName; // 负责人姓名,物业公司更换负责人姓名，手机号，email必须上传公司证明（盖章）

  // 公司名字，不需要设成独立的，只需要在物业公司注册时候，强制检查这个公司名字没有注册过
  // 后续，可以在公司名字已经注册的情况下，由公司负责人添加 公司的抄表员账户，这个账户附在这个公司名下
  @Indexed
  private String companyName; // 公司名字,必须和营业执照一致，否则无法通过（如需更换，必须提供营业执照，书面申请盖章证明）


  private List<Role> roles = new ArrayList<Role>(); // 权限

  // 邮箱是否被激活 ，之所以要加上index索引，方便定时清理未激活的账户
  @Indexed
  private boolean emailStatus;


  // 用户新注册时候的时候，自动生成Id,其它的变量都为null
  public User() {
    this.id = UUID.randomUUID().toString();
    this.companyName = null;
    this.email = null;
    this.password = null;
    this.phone = null;
    this.directName = null;
    this.registerTime = null;
    this.emailStatus = false;
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
