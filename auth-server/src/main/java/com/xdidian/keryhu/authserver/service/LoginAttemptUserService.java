/**  
* @Title: LoginAttemptUserService.java
* @Package com.xdidian.keryhu.authserver.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月30日 下午1:58:06
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.service;

/**
* @ClassName: LoginAttemptUserService
* @Description: TODO(设定LoginAttemptUser service 方法。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月30日 下午1:58:06
*/
public interface LoginAttemptUserService {
	
	
	
	/**
	 * 
	* @Title: loginFail
	* @Description: TODO(当用户试图登陆时，登陆失败促发的事件方法)
	* @param @param ip          将用户ip地址传递给后台service
	* @param @param userId      将用户userId地址传递给后台service
	* @return void    返回类型
	* @throws
	 */
	public void loginFail(String ip,String loginName);
	
	/**
	 * 
	* @Title: loginSuccess
	* @Description: TODO(当用户试图登陆时，登陆成功，促发的事件方法。)
	* @param     设定文件
	* @return void    返回类型
	* @throws
	 */
	public void loginSuccess();
	
	
	/**
	 * 
	* @Title: isBlocked
	* @Description: TODO(查看当前ip地址的远程客户，是否因为过度登录失败，而被系统锁定)
	* @param @param ip
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean isBlocked(String ip) ;

}
