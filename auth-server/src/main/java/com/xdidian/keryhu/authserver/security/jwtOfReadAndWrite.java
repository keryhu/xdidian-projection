package com.xdidian.keryhu.authserver.security;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;


/**
* @ClassName: JwtSettings
* @Description: TODO(将yml配置文件中，自定义的jwt.clientId,clientsecret等属性配置起了)
* @author keryhu  keryhu@hotmail.com
* @date 2016年2月27日 上午8:59:26
 */
@Component
@ConfigurationProperties(prefix="jwtOfReadAndWrite")
@Data
public class jwtOfReadAndWrite implements Serializable {
	
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;
	private String clientId;
	private String clientSecret;
	private boolean autoApproval;
	private String[] scopes;
	private String[] grantTypes;
	private String[] resourceIds;
	
	
	
}
