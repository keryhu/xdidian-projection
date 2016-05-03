/**  
* @Title: UserService.java
* @Package com.xdidian.keryhu.propertyregister.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月3日 上午11:25:37
* @version V1.0  
*/ 
package com.xdidian.keryhu.propertyregister.service;

import com.xdidian.keryhu.propertyregister.domain.PropertyForm;

/**
* @ClassName: UserService
* @Description: TODO(物业公司注册的一些service 接口)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月3日 上午11:25:37
*/
public interface UserService {
	
	public void vlidateBeforSave(PropertyForm propertyForm);

}
