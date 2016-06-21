package com.xdidian.keryhu.auth_server.domain;

import com.xdidian.keryhu.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Description : 过远程res登录的时候，需要验证的选项，除了密码，password，还需要验证
 * emailActivatedSendTimes有没有超过规定的次数，不过这个次数可以通t获取， emailActivatedStatus
 * @date : 2016年6月18日 下午7:53:26
 * @author : keryHu keryhu@hotmail.com
 */
@AllArgsConstructor
@Data
public class AuthUserDto implements Serializable {

  private static final long serialVersionUID = -8674622062902968568L;
  private String id;
  private String password;
  private List<Role> roles;
  private boolean emailStatus;

  public AuthUserDto() {
    // TODO Auto-generated constructor stub
    this.id = null;
    this.password = null;
    this.roles = null;
    this.emailStatus = false;
  }
}
