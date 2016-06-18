package com.xdidian.keryhu.propertyregister.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Description : 激活信息的配置属性
 * Date : 2016年06月18日 上午11:03
 * Author : keryHu keryhu@hotmail.com
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "emailActivate")
public class ActivatedProperties implements Serializable {


    private static final long serialVersionUID = -917850265000066502L;

    private int expiredTime;//默认是小时为单位


}
