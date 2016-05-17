package com.xdidian.keryhu.authserver.rest;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.xdidian.keryhu.authserver.client.UserClient;
import com.xdidian.keryhu.authserver.domain.LoginAttemptProperties;
import com.xdidian.keryhu.authserver.repository.LoginAttemptUserRepository;
import com.xdidian.keryhu.authserver.service.LoginAttemptUserService;
import com.xdidian.keryhu.authserver.service.UserService;
import static com.xdidian.keryhu.util.StringValidate.isEmail;
import static com.xdidian.keryhu.util.StringValidate.isPhone;

import lombok.RequiredArgsConstructor;


/**
* @ClassName: MainRest
* @Description: TODO(获取当前在线用户。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月25日 下午9:24:48
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRest {
	
	private final UserClient userClient;
	
	private final LoginAttemptUserRepository repository;
	
	private final LoginAttemptProperties loginAttemptProperties;
	
	private final UserService userService;
	
	private final LoginAttemptUserService loginAttemptService;
	
	private final MessageSource messageSource;
	
	/**
	 * 
	* @Title: user
	* @Description: TODO(返回当前登陆用户，principal)
	* @param @param user
	* @param @return    设定文件
	* @return Principal    返回类型
	* @throws
	 */
	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}
	
	
	/**
	 * 
	* @Title: queryLoginAttemptInfo
	* @Description: TODO(查询当前ip用户，在冻结账户之前，还剩几次输错机会。)
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(value="/query/leftLoginAttemptTimes",method=RequestMethod.GET)
	public ResponseEntity<?> queryLoginAttemptInfo(HttpServletRequest request){
		
	    //设置一个默认值，只有当ip不存在的时候，返回这个值
	    Map<String,Integer> result=new HashMap<String,Integer>();
	    result.put("leftLoginAttemptTimes", loginAttemptProperties.getMaxAttemptTimes());
	    
	   return repository.findByRemoteIp(userService.getIP(request)).map(e->{
		   //目前还剩几此输错的机会
	       int leftLoginAttemptTimes=loginAttemptProperties.getMaxAttemptTimes()-
	    		   e.getAlreadyAttemptTimes();
	       //将结果转为map对象。
		    Map<String,Integer> result1=new HashMap<String,Integer>();
		    result1.put("leftLoginAttemptTimes",leftLoginAttemptTimes);
	       
		    return ResponseEntity.ok(result1);
	    }).orElse(ResponseEntity.ok(result));
	        
	    
	}
	
	
	/**
	 * 前台web能否一次性处理 map 转换的json 的错误信息，显示到前台 到时候设置一致的错误返回结果，
	 * 尝试下。。如果可以的话，优先使用这个rest，，这个rest只要调用一次http请求，节省资源
	 * 另外，调用结束后，在这个方法里面返回 emailStatus的结果，如果为true，那么就不需要调用
	 * “/query/emailNotActived”，，如果为false，则调用此接口
	 * 
	 * 
	* @Title: validateLoginName
	* @Description: TODO(前台web，在检测完email格式，和phone格式正确后，调用此接口，查看的逻辑主要有：
	* 当前账户ip是否被冻结
	* 如果是phone，那么是否存在于数据库
	* 如果是email，那么是否存在于数据库
	* emailStatus的状态是什么，如果yes则通过，如果false，web前台接下来需要调用“/query/emailNotActived”方法
	* )
	* @param @param loginName
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(value="/query/validateLoginName",method=RequestMethod.GET)
    public  ResponseEntity<?> validateLoginName(@RequestParam("loginName") String loginName,
    		HttpServletRequest request){
		
		Assert.hasText(loginName,messageSource.getMessage("message.loginName.notNull", null, 
				LocaleContextHolder.getLocale()));
		
		Map<String,Object> result=new HashMap<String,Object>();
		
		String ip=userService.getIP(request);
		
		Consumer<String> errorM=x->{
			
			if(loginAttemptService.isBlocked(ip)){
				Object[] args={loginAttemptProperties.getTimeOfPerid(),loginAttemptProperties.getMaxAttemptTimes(),
						loginAttemptProperties.getTimeOfLock()};
			    String err= messageSource.getMessage("message.ip.blocked", 
						args, "您的IP已经被锁定，请稍后再试！", LocaleContextHolder.getLocale());
			    result.put("error", err);
			}
			else if(!(isEmail(x)||isPhone(x))){
				Object[] args={x};
				String err= messageSource.getMessage("message.loginName.neitherEmailNorPhone", 
						args, LocaleContextHolder.getLocale());
				result.put("error", err);
			}
			else if(isEmail(x)){
				if(!userClient.isEmailExist(x)){
					Object[] args={x};
					String err= messageSource.getMessage("message.loginName.emailNotExist", 
							args, LocaleContextHolder.getLocale());
					result.put("error", err);
				}
				result.put("emailStatus", userClient.emailStatus(x));
			}
			else if(isPhone(x)){
				if(!userClient.isPhoneExist(x)){
					Object[] args={x};
					String err= messageSource.getMessage("message.loginName.phoneNotExist", 
							args, LocaleContextHolder.getLocale());
					result.put("error", err);
				}
				result.put("emailStatus", userClient.emailStatus(x));
			} 
				
		};
		
		errorM.accept(loginName);
		
		return ResponseEntity.ok(result);
	}
	
	
}
