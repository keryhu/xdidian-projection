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
 * Description : 点击“重新发送激活邮件”所需要的接口
 * 此接口部分验证直接调用的 VerifyUrl service
 * Date : 2016年06月18日 上午9:27
 * Author : keryHu keryhu@hotmail.com
 */
public interface ResendService {
	
	 void exec(final String email);

}
