package com.xdidian.keryhu.propertyregister.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Description : 自定义host，通过application取得docker 的ip地址作为host，主要用在email激活时， 需要填写的url地址
 * @date : 2016年6月18日 下午9:14:40
 * @author : keryHu keryhu@hotmail.com
 */
@Component
@ConfigurationProperties(prefix = "hostProperty")
@Getter
@Setter
public class HostProperty implements Serializable {

  private static final long serialVersionUID = -6904038768907594026L;

  private String hostName;

}
