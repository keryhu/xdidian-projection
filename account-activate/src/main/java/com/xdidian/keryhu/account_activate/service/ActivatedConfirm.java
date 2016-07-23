/**
 * @Title: ActivatedConfim.java
 * @Package com.xdidian.keryhu.emailActivate.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年5月17日 下午6:16:33
 * @version V1.0
 */
package com.xdidian.keryhu.account_activate.service;


/**              
 *  @Description  用户输入email或phone验证码，验证成功后，所做的事情
 *              1 前台导航到login 页面。
 *              2 account-activated发送包含id(包含了email或phone）的message（主题激活成功）
 *              3 account-activated删除此user数据库记录
 *              4 user-account接受到消息，查询email或phone所在的user，修改eamilStatus＝true或phoneStatus
 * @date : 2016年6月18日 下午8:54:41
 * @author : keryHu keryhu@hotmail.com
 */
public interface ActivatedConfirm {

  void exec(final String id);

}
