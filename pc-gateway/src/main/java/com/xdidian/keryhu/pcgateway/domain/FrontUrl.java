package com.xdidian.keryhu.pcgateway.domain;


import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "front")
@Getter
@Setter
public class FrontUrl {
    private String assetHost;
    private String assetManifestUrl;

}
