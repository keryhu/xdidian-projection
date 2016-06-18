/**
 * @Title: HostProperty.java
 * @Package com.xdidian.keryhu.mailServer.mail
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年5月7日 下午7:34:59
 * @version V1.0
 */
package com.xdidian.keryhu.emailActivate.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;


/**
 * 
 * @Description : 自定义host，通过application取得docker 的ip地址作为host，主要用在email激活时， 需要填写的url地址
 * @date : 2016年6月18日 下午8:09:53
 * @author : keryHu keryhu@hotmail.com
 */
@Component
@ConfigurationProperties(prefix = "activated")
@Getter
@Setter
public class ActivatedProperties implements Serializable {

  private static final long serialVersionUID = 6219341996080208763L;

  private int maxSendTimes;
  private int sendCycleMinutes; // 点击“再次发送激活邮件的 间隔时间 单位分钟数
  private String resendUrl;
  private String reregisterUrl;
  private String registerResultUrl;
  private String loginUrl;
  private String defaultRegisterUrl;


  public String getResendUrl(String email, String resendToken) {
    return new StringBuffer(this.getResendUrl()).append(email).append("&token=").append(resendToken)
        .toString();
  }

  public String getReregisterUrl(String email, String reregisterToken) {
    return new StringBuffer(this.getReregisterUrl()).append(email).append("&token=")
        .append(reregisterToken).toString();
  }
}
