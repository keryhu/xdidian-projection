package com.xdidian.keryhu.account_activate.domain;

import lombok.Data;
import static com.xdidian.keryhu.util.StringValidate.isEmail;
import static com.xdidian.keryhu.util.StringValidate.isPhone;

import java.io.Serializable;
import java.util.stream.Stream;

/**
 * @Description : TODO( 此对象加上了 local 表示是 基于本地的dto，用在前台http提交的时候，传上来的需要验证的 相关数据。
 * 用于帐号激活时，激活email或者phone，前台终端提交的包含email或者phone 和 6位随即数字的 token 的domain)
 * @date : 2016年7月20日 上午10:45:07
 * @author : keryHu keryhu@hotmail.com
 */
@Data
public class LocalActivateDto implements Serializable {

  private static final long serialVersionUID = 4760884050124736500L;
  private String email;
  private String phone;
  private String token;
  private ActivateType type;  //包含email或者phone两种方式的一种。
  
  public LocalActivateDto(){}
  
  // 因为email和phone，只能设置一个，所以constructor，设置可选参数。
  //type 必须是email或者phone String 的一种。
  public LocalActivateDto(String token,ActivateType type, String... m) {

    this.token = token;
    this.type=type;

    Stream.of(m).filter(e -> e != null).forEach(e -> {
      if (isEmail(e)) {
        this.email = e;
      } else if (isPhone(e)) {
        this.phone = e;
      }
    });
  }

  
}
