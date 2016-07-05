/**
 * @Title: HostProperty.java
 * @Package com.xdidian.keryhu.mailServer.mail
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年5月7日 下午7:34:59
 * @version V1.0
 */
package com.xdidian.keryhu.auth_server.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 
 * @Description :自定义host，通过application取得docker 的ip地址作为host，主要用在email激活时，
 *  需要填写的url地址
 * @date : 2016年6月18日 下午7:54:10
 * @author : keryHu keryhu@hotmail.com
 */
@Component("hostProperty")
@ConfigurationProperties(prefix = "hostProperty")
@Getter
@Setter
public class Host implements Serializable {

  private static final long serialVersionUID = -6904038768907594026L;

  private String hostName;

}