/**  
* @Title: ActivatedTokenServiceImpl.java
* @Package com.xdidian.keryhu.emailActivate.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月11日 下午9:50:17
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xdidian.keryhu.emailActivate.client.UserClient;
import com.xdidian.keryhu.emailActivate.domain.ActivatedProperties;
import com.xdidian.keryhu.emailActivate.domain.ActivatedToken;
import com.xdidian.keryhu.emailActivate.exception.EmailNotFoundException;
import com.xdidian.keryhu.emailActivate.exception.SendTimesOverException;
import com.xdidian.keryhu.emailActivate.exception.TokenNotFoundException;
import com.xdidian.keryhu.emailActivate.repository.ActivatedTokenRepository;
import com.xdidian.keryhu.emailActivate.stream.ActivatedSuccessProducer;
import com.xdidian.keryhu.emailActivate.stream.RemoveUserProducer;
import com.xdidian.keryhu.emailActivate.stream.ResendProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: ActivatedTokenServiceImpl
* @Description: TODO(继承自 ActivatedTokenService 的接口服务的具体实现。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月11日 下午9:50:17
*/
@Component("tokenService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TokenServiceImpl  implements TokenService{
	
	private final ActivatedTokenRepository repository;
	
    private final RemoveUserProducer removeUserProducer;
    
    private final ActivatedSuccessProducer activatedSuccessProducer;
    
    private final UserClient userClient;
    
    private final ActivatedProperties activatedProperties;
    
    private final ResendProducer resendProducer;
    
    private final ConverterUtil converterUtil;
    


	/**
	* <p>Title: save</p>
	* <p>Description:  带有事物回滚的 ActivatedToken 保存到数据库</p>
	* @param activatedToken
	* @return
	* @see com.xdidian.keryhu.emailActivate.service.TokenService#save(com.xdidian.keryhu.emailActivate.domain.ActivatedToken)
	*/ 
	@Override
	@Transactional
	public ActivatedToken save(ActivatedToken activatedToken) {
		// TODO Auto-generated method stub
		return repository.save(activatedToken);
	}

	/**
	* <p>Title: ConfirmUrl</p>
	* <p>Description:验证email激活，发送到邮箱的url，用户点击url，后台处理get 的url，查看是否此url
	* 传来的参数email和token是否能够通过验证，如果通过验证，那么就更新数据库，设置user账户emailStatus为true，
	* 删除emailActivate 服务器里面的此user数据，并且后台导航页面到login页面（提示激活成功），否则报错 
	* 这个方法主要处理的情况是，email存在，且eamilStatus为false的背景
	* 需要判断的情况为：
	* 1 验证码不存在，直接报错
	* 2 验证码存在，但是激活时间已经过期，－－－A  发送删除user（包含email）的message，user-account接受到，删除user
	*                                   B  email－activate，从数据库中删除此email所在的数据
	*                                   c  根据role导航到改role所在的注册页面。
	* 3 上面的判断加好后，留下来的就是验证成功的方法入口－－
	* </p>
	* @param email
	* @param token
	* @param @param mav     需要这个参数，进行相应的页面导航
	* @param @param attr    设定文件   需要这个参数，传递导航的提示信息
	* @see com.xdidian.keryhu.emailActivate.service.TokenService#ConfirmUrl(java.lang.String, java.lang.String)
	*/ 
	@Override
	public void ConfirmUrl(String email, String token,ModelAndView mav,RedirectAttributes attr) {
		// TODO Auto-generated method stub
		
		String message=new StringBuffer("尊敬的 ：").append(email).append("  用户，您已成功激活，请直接登录！").toString();
		
		String redirectUrl=activatedProperties.getLoginUrl();
			
		 //如果token不存在于数据库，报错
		 if (!tokenExist(email,token)){
			 log.info("您要激活的验证码不存在，到时候，导到前台页面显示错误！");
			throw new TokenNotFoundException("您的email激活码不存在，无法通过验证。");
		 } 
		 
		 //验证成功的方法。  
		  else if (!activateExpired(email)){			 
			  confirmSuccess(email);			 
			  attr.addAttribute("emailActivated",message);
			  mav.setViewName(redirectUrl);
			  
		  }
		  //激活过期
		  else if(activateExpired(email)){
			  doWhenExpired(email,mav,attr);
		  }
		    	 
	   }
	
	
	/**
	 * 
	* @Title: confirmSuccess
	* @Description: TODO(如果用户点击激活账户的url验证成功，所需要操作的方法。
	* 包含了3件事情：
	*            1  页面跳转到login页面，提示激活成功的信息，这个在controller已经做了处理
	*            2  删除此email所在的 本service，token数据。因为既然email已经激活成功了，保留在数据库已经毫无作用，不如清理空间
	*            3  发送包含email的账户email激活成功的message出去，user-account接受到后，更改emailStatus为true)
	* @param @param email    设定文件
	* @return void    返回类型
	* @throws
	 */
	private void confirmSuccess(String email){
		
		repository.deleteByEmail(email);
		log.info("已经从数据库中成功删除了email-activate，{} 所在的数据",email);
		//发送包含email激活成功的message出去，
		activatedSuccessProducer.send(email);
	}
	
	/**
	 * 
	* @Title: tokenExist
	* @Description: TODO(检测提交的参数eamil和token，是否在数据库中存在对应的关系
	* 使用此方法的前提是email已经存在于数据库了
	* 如果token 不存在，默认返回false
	* 如果token，不为空，则验证2个token是否相等。)
	* @param @param email
	* @param @param token
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	private boolean tokenExist(String email,String token){
		
		return repository.findByEmail(email).map(e->{
			if(e.getToken().trim().length()>0){
				return e.getToken().equals(token);
			  }
			    return false;
		   }).get();
		
	}
	
	/**
	 * 
	* @Title: doWhenExpired
	* @Description: TODO(当激活码过期或者用户点击“重新注册”需要执行的方法。)
	* @param    email 设定文件
	* @return void    返回类型
	* @throws
	 */
	@Override
	public void doWhenExpired(final String email,final ModelAndView mav,
			final RedirectAttributes attr){
		
		String message,redirectUrl;
		
		 //发送删除user的message
		  removeUserProducer.send(email);
		  log.info("发出message，删除此 {} 所在的user数据 。",email);
		  
		  List<String> roles=userClient.findRolesByEmail(email);
		  
		  log.info("客户之前注册权限是 ： {} , 后续会根据不同的权限，分配不同的注册页面。",roles);
		  
		  //默认导航到这个注册页面，到时候根据email所属的role，分配不同的页面
		  redirectUrl=activatedProperties.getDefaultRegisterUrl();
		  message=new StringBuffer("尊敬的 ：").append(email)
					 .append("  用户，您提交的email还未注册，请先注册！").toString();
		  
		  attr.addAttribute("notRegister",message);
		  //删除本地的 email token记录
		  repository.deleteByEmail(email);
		 
		  mav.setViewName(redirectUrl);
		
	}

	/**
	* <p>Title: activateExpired</p>
	* <p>Description: 使用这个方法的前提是email已经存在于数据库了，此方法用来判断，当前email，激活时间是否过期
	* </p>
	* @param email
	* @return
	* @see com.xdidian.keryhu.emailActivate.service.TokenService#activateExpired(java.lang.String)
	*/ 
	@Override
	public boolean activateExpired(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email)
				         .filter(e->e.getExpiryDate()!=null)
				         .map(e->LocalDateTime.now().isAfter(e.getExpiryDate()))
				         .orElseThrow(()->new EmailNotFoundException("您要激活的email不存在于数据库"));	
	}

	/**
	* <p>Title: doWhenNotExpired</p>
	* <p>Description:当激活码没有过期，需要执行的方法，就是将email参数，一起redirect到 
	* hostname/8080/register/result 页面，方便用户点击“再次发送”和“重新注册”) </p>
	* @param email
	* @param mav
	* @param attr
	* @see com.xdidian.keryhu.emailActivate.service.TokenService#doWhenNotExpired(java.lang.String, org.springframework.web.servlet.ModelAndView, org.springframework.web.servlet.mvc.support.RedirectAttributes)
	*/ 
	@Override
	public void doWhenNotExpired(String email, ModelAndView mav, RedirectAttributes attr) {
		// TODO Auto-generated method stub
		//重新发送的url
		String resend=new StringBuffer(activatedProperties.getResendUrl())
				.append(email)
				.toString();		
				
		//重新注册的url
		String reregister=new StringBuffer(activatedProperties.getReregisterUrl())
				.append(email)
				.toString();	
		
		String redirectUrl=activatedProperties.getRegisterResultUrl();
			
		attr.addAttribute("resend", resend);
		attr.addAttribute("reregister", reregister);
		mav.setViewName(redirectUrl);
	}
	
	
	/**
	 * 
	* @Title: sendTimesOver
	* @Description: TODO(判断当前email的发送次数有没有超，如果超了，返回true，否则返回false。)
	* @param @param email
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	@Override
	public boolean sendTimesOver(String email){
		return repository.findByEmail(email).map(
				e->e.getSentTimes()>activatedProperties.getMaxSendTimes())
		        .orElseThrow(()->new EmailNotFoundException("您要激活的email不存在于数据库"));
	}
		
	/**
	 * 
	* <p>Title: doWithResend</p>
	* <p>Description: 当用户点击“重新发送”的时候，需要执行的方法</p>
	* @param email
	* @see com.xdidian.keryhu.emailActivate.service.TokenService#doWithResend(java.lang.String)
	 */
	@Override
	public void doWithResend(final String email){
		
		if(sendTimesOver(email)){
			throw new SendTimesOverException("您点击的太过频繁，请稍后再试！");
		}
		repository.findByEmail(email).ifPresent(e->{
			e.setToken(UUID.randomUUID().toString());
			AtomicInteger atomic=new AtomicInteger(e.getSentTimes());
			e.setSentTimes(atomic.incrementAndGet());
			repository.save(e);
			resendProducer.send(converterUtil.activatedTokenToEmailActivatedDto.apply(e));
		});
	
	}

}
