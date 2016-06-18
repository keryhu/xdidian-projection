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
 * Description : 物业公司注册的一些service 接口
 * Date : 2016年06月18日 上午11:11
 * Author : keryHu keryhu@hotmail.com
 */
public interface UserService {

    /**
     * 物业公司注册提交的web form 数据，需要经过具体合法性验证的接口
     */
    public void vlidateBeforSave(PropertyForm propertyForm);


}
