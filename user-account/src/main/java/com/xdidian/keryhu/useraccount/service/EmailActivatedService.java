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
	* @Title: emailActivateSentTimesNotOver
	* @Description: TODO(根据登录帐号（email或password），查看当前尝试的次数有没有超过限制)
	* @param @param loginName
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean emailActivateSentTimesNotOver(String loginName);
	
	
	/**
	 * 
	* @Title: emailActivatedStatus
	* @Description: TODO(查询登录帐号（email或phone） 有没有被激活，（true or false）)
	* @param @param loginName
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean emailActivatedStatus(String loginName);
	
	
	/**
	 * 
	* @Title: emailActivatedExpired
	* @Description: TODO(根据登录帐号查看（phone或email），在emailActivated 为false的情况下，看现在时间有没有超过
	* 注册时间＋最大允许的过期时间)
	* @param @param loginName
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean emailActivatedExpired(String loginName);
	
	/**
	 * 
	* @Title: emailActivatedCodeExist
	* @Description: TODO(根据登录帐号（email或phone）查询的值，查看EmailActivatedCodeExist是否存在)
	* @param @param loginName
	* @param @param emailActivatedCode
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean emailActivatedCodeExist(String loginName,String emailActivatedCode);
	
	/**
	 * 
	* @Title: retryEmailActivated
	* @Description: TODO(当user-account接受到再次发送邮件激活命令时的接口)
	* @param @param loginName    设定文件
	* @return void    返回类型
	* @throws
	 */
	public void retryEmailActivated(String loginName);
	
	/**
	 * 
	* @Title: validateEmailActivatedAndSave
	* @Description: TODO(验证email激活，发送到邮箱的url，用户点击url，后台处理get 的url，查看是否此url
	* 能够通过验证，如果通过验证，那么就更新数据库，否则报错)
	* @param @param email
	* @param @param emailActivatedCode    设定文件
	* @return Optional<User>    返回类型
	* @throws
	 */
	public void validateEmailActivatedAndSave(String email,String emailActivatedCode);

}
