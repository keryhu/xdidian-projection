/**
 * @Title: ActivatedConfim.java
 * @Package com.xdidian.keryhu.emailActivate.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年5月17日 下午6:16:33
 * @version V1.0
 */
package com.xdidian.keryhu.email_activate.service;


/**
 * Description : Date : 2016年06月18日 上午8:43 Author : keryHu keryhu@hotmail.com
 */

/**
 * 
 * @Description : 用户点击 激活邮箱的url，当验证完url是合法的，即－－ email存在，未激活，且未过期的情况下，后续需要执行的命令,除了页面跳转 login任务
 *              包含email activated 删除此email所在的数据库记录 发送包含email的激活成功的message出去
 * @date : 2016年6月18日 下午8:54:41
 * @author : keryHu keryhu@hotmail.com
 */
public interface ActivatedConfirm {

  void exceptRedirect(final String email);

}
