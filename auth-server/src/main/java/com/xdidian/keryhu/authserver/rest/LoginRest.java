/**  
* @Title: LoginRest.java
* @Package com.xdidian.keryhu.authserver.rest
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年6月5日 下午8:01:54
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xdidian.keryhu.authserver.domain.LoginAttemptProperties;
import com.xdidian.keryhu.authserver.service.LoginAttemptUserService;
import com.xdidian.keryhu.authserver.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

/**
* @ClassName: LoginRest
* @Description: TODO(login error rest)
* @author keryhu  keryhu@hotmail.com
* @date 2016年6月5日 下午8:01:54
*/
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginRest {
	
	private final LoginAttemptUserService loginAttemptService;
	private final UserServiceImpl userService;
	private final LoginAttemptProperties loginAttemptProperties;
	
	/**
	 * 
	* @Title: loginError
	* @Description: TODO(当用户名，密码输入错误的时候，使用的rest ，同时还显示了 剩余的次数。)
	* @param @param model
	* @param @param error
	* @param @param request
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginError(Model model,HttpServletRequest request,
			@RequestParam(value="error",required=false) String error){
		
		
		if(loginAttemptService.isBlocked(userService.getIP(request))){
			//您多长时间内登录失败多少次，帐号被冻结xx久。
			String blockMsg=new StringBuffer("您 ")
					.append(loginAttemptProperties.getTimeOfPerid())
					.append(" 小时内，登录失败 ")
					.append(loginAttemptProperties.getMaxAttemptTimes())
					.append(" 次，帐号被冻结 ")
					.append(loginAttemptProperties.getTimeOfLock())
					.append(" 个小时！")
					.toString();
			model.addAttribute("blockMsg", blockMsg);
		}
		
		else{
			int leftTimes=loginAttemptService.leftLoginTimes(request);
			String errorMsg=new StringBuffer("帐号、密码不匹配，您还有 ")
					.append(leftTimes)
					.append(" 次机会！")
					.toString();
			model.addAttribute("errorMsg", errorMsg);
		}
		
			
		return "login";
	}

}
