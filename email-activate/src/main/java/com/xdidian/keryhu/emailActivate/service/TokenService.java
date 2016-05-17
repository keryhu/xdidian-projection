/**  
* @Title: ActivatedTokenService.java
* @Package com.xdidian.keryhu.emailActivate.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月11日 下午1:31:49
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.service;

import com.xdidian.keryhu.emailActivate.domain.ActivatedToken;

/**
* @ClassName: ActivatedTokenService
* @Description: TODO(用于email激活 token 的一些基本接口)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月11日 下午1:31:49
*/
public interface TokenService {
	
	/**
	 * 
	* @Title: save
	* @Description: TODO(保存对象到数据库)
	* @param @param activatedToken
	* @param @return    设定文件
	* @return ActivatedToken    返回类型
	* @throws
	 */
	public ActivatedToken  save(ActivatedToken activatedToken);
	
}
