package com.xdidian.keryhu.propertyregister.domain;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 
* @ClassName: EmailActivatedProperties
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月5日 下午7:58:42
 */
@Data
@Component
@ConfigurationProperties(prefix="emailActivate")
public class ActivatedProperties  implements Serializable {

	
	private static final long serialVersionUID = -917850265000066502L;
	
	private int expiredTime ;//默认是小时为单位
	

}
