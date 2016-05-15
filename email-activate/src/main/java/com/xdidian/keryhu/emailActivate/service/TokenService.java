/**  
* @Title: ActivatedTokenService.java
* @Package com.xdidian.keryhu.emailActivate.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月11日 下午1:31:49
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xdidian.keryhu.domain.Role;
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
	
	
	/**
	 * 
	* @Title: ConfirmUrl
	* @Description: TODO(验证email激活，发送到邮箱的url，用户点击url，后台处理get 的url，查看是否此url
	* 传来的参数email和token是否能够通过验证，如果通过验证，那么就更新数据库，设置user账户emailStatus为true，
	* 删除emailActivate 服务器里面的此user数据，并且后台导航页面到login页面（提示激活成功），否则报错
	* @param @param email
	* @param @param token
	* @param @param attr    设定文件   需要这个参数，传递导航的提示信息
	* @return ModelAndView    返回类型
	* @throws
	 */
	public ModelAndView ConfirmUrl(String email,String token,RedirectAttributes attr);
	
	/**
	 * 
	* @Title: activateExpired
	* @Description: TODO(查看当前email所在的数据库，激活时间是否已经过期。)
	* @param @param email
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean activateExpired(String email);
	
	/**
	 * 
	* @Title: doWhenExpired
	* @Description: TODO(如果email激活码已经过期，需要执行的程序)
	* @param @param email
	* @param @param attr    设定文件
	* @return ModelAndView    返回类型
	* @throws
	 */
	public ModelAndView doWhenExpired(final String email,
			final RedirectAttributes attr);
	
	
	/**
	 * 
	* @Title: doWhenNotExpired
	* @Description: TODO(当激活码没有过期，需要执行的方法，就是将email参数，一起redirect到 
	* hostname/8080/register/result 页面，方便用户点击“再次发送”和“重新注册”)
	* @param @param email
	* @param @param attr    设定文件
	* @return ModelAndView    返回类型
	* @throws
	 */
	public ModelAndView doWhenNotExpired(final String email,
			final RedirectAttributes attr);

	/**
	 * 
	* @Title: doWithResend
	* @Description: TODO(当用户点击“重新发送”的时候，需要执行的方法)
	* @param @param email    设定文件
	* @return void    返回类型
	* @throws
	 */
	public void doWithResend(final String email);
	
	
	
	/**
	 * 
	* @Title: notRegister
	* @Description: TODO(当用户email未注册的时候，需要导航到页面，和提示信息)
	* @param @param email
	* @param @param attr
	* @param @return    设定文件
	* @return ModelAndView    返回类型
	* @throws
	 */
	public ModelAndView notRegister(final String email,final RedirectAttributes attr,
			List<Role> roles);
	
	/**
	 * 
	* @Title: hasRegister
	* @Description: TODO(当email已经注册时候，需要导航到的页面和提示信息)
	* @param @param email
	* @param @param attr
	* @param @return    设定文件
	* @return ModelAndView    返回类型
	* @throws
	 */
	public ModelAndView hasRegister(final String email,final RedirectAttributes attr);
}
