/**  
* @Title: LoginAttemptProperties.java
* @Package com.xdidian.keryhu.authserver.domain
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月30日 下午1:06:32
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.domain;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
* @ClassName: LoginAttemptProperties
* @Description: TODO(通过application.yml来配置一些 loginAttempt 的属性值)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月30日 下午1:06:32
*/
@Component
@ConfigurationProperties(prefix="LoginAttempt")
@Data
public class LoginAttemptProperties implements Serializable{

	
	private static final long serialVersionUID = 9188216543512476830L;
	
	//设置最大的loginAttemplt 次数。
	private int maxAttemptTimes;
	
	//设置固定的LoginAttemptTimes 清零时间，单位为：“小时” ，正数倍
	private int timeOfPerid;
	
	//设置锁定ip，登陆的小时数。
	private int timeOfLock;
	
	
	

}