/**
 * @Title: RedirectService.java
 * @Package com.xdidian.keryhu.emailActivate.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月17日 下午2:35:14
 * @version V1.0
 */
package com.xdidian.keryhu.emailActivate.service;

import com.xdidian.keryhu.domain.Role;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


/**
 * Description : 实现页面跳转功能,这个接口只在service package下有效
 * Date : 2016年06月18日 上午9:26
 * Author : keryHu keryhu@hotmail.com
 */
public interface RedirectService {

    /**
     * 导航到login页面
     */
    ModelAndView redirectLoginPage(final String email, final RedirectAttributes attr);

    /**
     * 根据role不同，导航到不同的注册页面，默认是 ／register页面
     */
    ModelAndView redirectRegisterPage(final String email, final RedirectAttributes attr,
                                      final List<Role> roles);


    /**
     * 导航到resend and reregister 页面
     */
    ModelAndView redirectResendPage(String email, RedirectAttributes attr);

}
