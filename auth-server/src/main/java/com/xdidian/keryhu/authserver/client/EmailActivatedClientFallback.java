/**  
* @Title: EmailActivatedClientFallback.java
* @Package com.xdidian.keryhu.authserver.client
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月6日 下午7:47:03
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.client;

import org.springframework.stereotype.Component;

/**
* @ClassName: EmailActivatedClientFallback
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月6日 下午7:47:03
*/
@Component
public class EmailActivatedClientFallback implements  EmailActivatedClient{

	/**
	* <p>Title: findEmailActivatedStatus</p>
	* <p>Description: 默认的返回邮件激活返回状态</p>
	* @param email
	* @return
	* @see com.xdidian.keryhu.authserver.client.EmailActivatedClient#findEmailActivatedStatus(java.lang.String)
	*/ 
	@Override
	public boolean emailActivatedStatus(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	* <p>Title: emailActivatedExpired</p>
	* <p>Description: 查看邮件激活的时间是否过期</p>
	* @param email
	* @return
	* @see com.xdidian.keryhu.authserver.client.EmailActivatedClient#emailActivatedExpired(java.lang.String)
	*/ 
	@Override
	public boolean emailActivatedExpired(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	* <p>Title: emailActivateSentTimesNotOver</p>
	* <p>Description: </p>
	* @param email
	* @return
	* @see com.xdidian.keryhu.authserver.client.EmailActivatedClient#emailActivateSentTimesNotOver(java.lang.String)
	*/ 
	@Override
	public boolean emailActivateSentTimesNotOver(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}
