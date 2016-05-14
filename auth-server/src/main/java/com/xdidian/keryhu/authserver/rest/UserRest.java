package com.xdidian.keryhu.authserver.rest;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.xdidian.keryhu.util.StringValidate;

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
		
		Assert.hasText(loginName,"登录名loginName不能为空");
		Map<String,Object> result=new HashMap<String,Object>();
		
		String ip=userService.getIP(request);
		
		if(loginAttemptService.isBlocked(ip)){
			result.put("error", "由于您操作太过频繁，您当前的ip已经被冻结！请稍后再试。");
			return ResponseEntity.ok(result);
		}
		else if(StringValidate.isEmail(loginName)){
			if(!userClient.isEmailExist(loginName)){
				result.put("error", "email不存在于数据库");
				return ResponseEntity.ok(result);
			}
		}
		else if(StringValidate.isPhone(loginName)){
			if(!userClient.isPhoneExist(loginName)){
				result.put("error", "phone不存在于数据库");
				return ResponseEntity.ok(result);
			}
		}
		result.put("emailStatus", userClient.emailStatus(loginName));
		return ResponseEntity.ok(result);
	}
	
	
}
