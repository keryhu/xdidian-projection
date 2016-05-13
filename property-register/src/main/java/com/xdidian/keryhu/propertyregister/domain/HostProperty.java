/**  
* @Title: HostProperty.java
* @Package com.xdidian.keryhu.mailServer.mail
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午7:34:59
* @version V1.0  
*/ 
package com.xdidian.keryhu.propertyregister.domain;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

/**
* @ClassName: HostProperty
* @Description: TODO(自定义host，通过application取得docker 的ip地址作为host，主要用在email激活时，
* 需要填写的url地址)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午7:34:59
*/
@Component
@ConfigurationProperties(prefix="hostProperty")
@Data
public class HostProperty implements Serializable{
	
	private static final long serialVersionUID = -6904038768907594026L;
	
	private String hostName;

}
