package com.xdidian.keryhu.pcgateway.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description : 前台angular2 host 和 相关配置的设置
 * Date : 2016年06月18日 上午10:44
 * Author : keryHu keryhu@hotmail.com
 */
@Component
@ConfigurationProperties(prefix = "front")
@Getter
@Setter
public class FrontUrl {
    private String assetHost;
    private String assetManifestUrl;

}
