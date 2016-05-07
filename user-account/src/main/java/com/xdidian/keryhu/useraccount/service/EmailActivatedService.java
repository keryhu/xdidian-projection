/**  
* @Title: EmailActivatedService.java
* @Package com.xdidian.keryhu.useraccount.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午2:48:26
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.service;

/**
* @ClassName: EmailActivatedService
* @Description: TODO(email激活的一些基础接口)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午2:48:26
*/
public interface EmailActivatedService {
	
	/**
	 * 
	* @Title: emailActivateTimesNotOver
	* @Description: TODO(根据email，查看当前尝试的次数有没有超过限制)
	* @param @param email
	* @param @param emailActivatedCode
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean emailActivateSendTimesNotOver(String email);
	
	
	/**
	 * 
	* @Title: emailActivatedStatus
	* @Description: TODO(查询email 有没有被激活，（true or false）)
	* @param @param email
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean emailActivatedStatus(String email);
	
	
	/**
	 * 
	* @Title: emailActivatedExpired
	* @Description: TODO(根据email查询的值，在emailActivated 为false的情况下，看现在时间有没有超过
	* 注册时间＋最大允许的过期时间)
	* @param @param email
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean emailActivatedExpired(String email);
	
	/**
	 * 
	* @Title: emailActivatedCodeExist
	* @Description: TODO(根据eamil查询的值，查看EmailActivatedCodeExist是否存在)
	* @param @param email
	* @param @param emailActivatedCode
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean emailActivatedCodeExist(String email,String emailActivatedCode);
	
	/**
	 * 
	* @Title: retryEmailActivated
	* @Description: TODO(当user-account接受到再次发送邮件激活命令时的接口)
	* @param @param email    设定文件
	* @return void    返回类型
	* @throws
	 */
	public void retryEmailActivated(String email);

}
