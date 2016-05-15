/**  
* @Title: HostProperty.java
* @Package com.xdidian.keryhu.mailServer.mail
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午7:34:59
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.domain;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

/**
* @ClassName: HostProperty
* @Description: TODO(自定义host，通过application取得docker 的ip地址作为host，主要用在email激活时，
* 需要填写的url地址)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午7:34:59
*/
@Component
@ConfigurationProperties(prefix="activatedProperties")
@Getter
@Setter
public class ActivatedProperties implements Serializable{

	private static final long serialVersionUID = 6219341996080208763L;
	
	private int maxSendTimes;
	private String resendUrl;
	private String reregisterUrl;
	private String registerResultUrl;
	private String loginUrl;
	private String defaultRegisterUrl;
	

	public String getResendUrl(String email){
		return new StringBuffer(this.getResendUrl())
				.append(email)
				.toString();
	}
	
	public String  getReregisterUrl(String email){
		return new StringBuffer(this.getReregisterUrl())
				.append(email)
				.toString();
	}
}
