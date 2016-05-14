/**  
* @Title: EmailActivatedController.java
* @Package com.xdidian.keryhu.authserver.rest
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月6日 下午9:02:03
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.xdidian.keryhu.authserver.client.UserClient;
import com.xdidian.keryhu.authserver.domain.HostProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: EmailActivatedController
* @Description: TODO(email 激活所需要的 rest 接口)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月6日 下午9:02:03
*/
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EmailActivatedController  {
	 
    private final UserClient userClient;
    private final HostProperty hostProperty;
	
	
	
	
	/**
	 * 
	* @Title: callEmailActivatedValidate
	* @Description: TODO(当前台检测到user用户，email未激活的时候，前台ajax此链接，此链接调用email-activate
	* 中的后台方法，处理接下来的事情。)
	* @param @param loginName
	* @param @return    设定文件
	* @return ModelAndView    返回类型
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/query/emailNotActived")
	public ModelAndView callEmailActivatedValidate(@RequestParam("loginName") String loginName){
		Assert.hasText(loginName,"登录名loginName不能为空");
		ModelAndView mav=new ModelAndView();
		String email=userClient.loginNameToEmail(loginName);
		String redirectUrl=new StringBuffer("redirect:")
				 .append(hostProperty.getHostName()).append(":8004/email/notActivedWhilelogin?email=")
				 .append(email)
				 .toString();
		log.info("url is : {} !",redirectUrl);
		mav.setViewName(redirectUrl);
		
		return mav;
	}

}
