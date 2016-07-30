package com.xdidian.keryhu.account_activate.service;


/**
 * Description : Date : 2016年06月18日 上午9:25 Author : keryHu keryhu@hotmail.com
 */

/**
 * 
 * @Description : 当email激活时，或者发送“重新激活”，或者点击“重新注册”时候，遇到激活时间已经过期， 需要执行的service
 * @date : 2016年6月18日 下午8:54:59
 * @author : keryHu keryhu@hotmail.com
 */
public interface ActivatedExpired {

  /**
   * 当激活时间过期时，需要执行的动作，可以是email过期，也可以是phone过期。
   */
  String executeExpired(final String id);
  
  
}
