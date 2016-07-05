package com.xdidian.keryhu.property_signup.domain;

import lombok.Data;

import java.io.Serializable;


/**
 * @Description : 物业公司用户注册 web class，此class 提交后会 转换为 PropertyRegisterDto
 * @date : 2016年6月18日 下午9:14:58
 * @author : keryHu keryhu@hotmail.com
 */
@Data
public class PropertyForm implements Serializable {
  private static final long serialVersionUID = 1027409993570777508L;

  private String email;
  private String phone;
  private String password;
  private String companyName; // 公司名字
  private String directName; // 负责人姓名

  public PropertyForm() {
    // TODO Auto-generated constructor stub
    this.email = null;
    this.phone = null;
    this.password = null;
    this.companyName = null;
    this.directName = null;
  }

}