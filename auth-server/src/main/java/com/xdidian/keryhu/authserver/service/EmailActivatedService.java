/**  
* @Title: EmailActivatedService.java
* @Package com.xdidian.keryhu.authserver.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 上午8:43:46
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.service;

/**
* @ClassName: EmailActivatedService
* @Description: TODO(email 激活在auth-server中的具体接口)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 上午8:43:46
*/
public interface EmailActivatedService {
	
	
	/**
	 * 
	* @Title: handleEmaiActivated
	* @Description: TODO(用来登录时，登录获取到的email和id，针对email没有激活，做相应的处理)
	* @param @param id
	* @param @param email    设定文件
	* @return void    返回类型
	* @throws
	 */
	public void handleEmaiActivated(String id,String email);

}
