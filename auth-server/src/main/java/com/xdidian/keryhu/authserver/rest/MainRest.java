package com.xdidian.keryhu.authserver.rest;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.amazonaws.util.StringUtils;
import com.xdidian.keryhu.authserver.client.UserAccountClient;
import com.xdidian.keryhu.authserver.domain.LoginAttemptProperties;
import com.xdidian.keryhu.authserver.repository.LoginAttemptUserRepository;
import lombok.RequiredArgsConstructor;


/**
* @ClassName: MainRest
* @Description: TODO(获取当前在线用户。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月25日 下午9:24:48
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainRest {
	
	private final UserAccountClient userAccountClient;
	
	private final LoginAttemptUserRepository repository;
	
	private final LoginAttemptProperties loginAttemptProperties;
	
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
	* @Title: isEmailExist
	* @Description: TODO(用于前台web页面登录时，查询登陆的email是否存在于数据库，此为调用的后台接口)
	* @param @param email  需要被查询的email
	* @param @return    设定文件 返回的是一个json对象 Map对象，， 如果存在此email，则返回true，否则false
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(value="/query/isEmailExist",method=RequestMethod.GET)
	public ResponseEntity<?> isEmailExist(@RequestParam("email") String email){
		
		return ResponseEntity.ok(userAccountClient.isEmailExist(email));
	}

	/**
	 * 
	* @Title: isPhoneExist
	* @Description: TODO(用于前台web页面登录时，查询登陆的phone是否存在于数据库，此为调用的后台接口)
	* @param @param phone  需要被调用的phone
	* @param @return    设定文件   如果存在此email，则返回true，否则false
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(value="/query/isPhoneExist",method=RequestMethod.GET)
	public ResponseEntity<?> isPhoneExist(@RequestParam("phone") String phone){
		
		return ResponseEntity.ok(userAccountClient.isPhoneExist(phone));
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
		
	    //获取当前用户的ip地址	
		
		String xfHeader = request.getHeader("X-Forwarded-For");
	    String ip=StringUtils.isNullOrEmpty(xfHeader)?request.getRemoteAddr():xfHeader.split(",")[0];
	       
	    //设置一个默认值，只有当ip不存在的时候，返回这个值
	    Map<String,Integer> result=new HashMap<String,Integer>();
	    result.put("leftLoginAttemptTimes", loginAttemptProperties.getMaxAttemptTimes());
	    
	   return repository.findByRemoteIp(ip).map(e->{
		   //目前还剩几此输错的机会
	       int leftLoginAttemptTimes=loginAttemptProperties.getMaxAttemptTimes()-
	    		   e.getAlreadyAttemptTimes();
	       //将结果转为map对象。
		    Map<String,Integer> result1=new HashMap<String,Integer>();
		    result1.put("leftLoginAttemptTimes",leftLoginAttemptTimes);
	       
		    return ResponseEntity.ok(result1);
	    }).orElse(ResponseEntity.ok(result));
	        
	    
	}
	
}
