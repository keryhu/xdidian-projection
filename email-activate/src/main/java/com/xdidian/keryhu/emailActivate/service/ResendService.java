/**  
* @Title: ResendService.java
* @Package com.xdidian.keryhu.emailActivate.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月17日 下午5:11:22
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.service;

/**
* @ClassName: ResendService
* @Description: TODO(点击“重新发送激活邮件”所需要的接口
* 此接口部分验证直接调用的 VerifyUrl service)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月17日 下午5:11:22
*/
public interface ResendService {
	
	 void exec(final String email);

}
