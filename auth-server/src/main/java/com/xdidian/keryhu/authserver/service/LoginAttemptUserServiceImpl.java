/**  
* @Title: LoginAttemptUserServiceImpl.java
* @Package com.xdidian.keryhu.authserver.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月30日 下午2:21:11
* @version V1.0  
*/
package com.xdidian.keryhu.authserver.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.amazonaws.util.StringUtils;
import com.xdidian.keryhu.authserver.domain.LoginAttemptProperties;
import com.xdidian.keryhu.authserver.domain.LoginAttemptUser;
import com.xdidian.keryhu.authserver.repository.LoginAttemptUserRepository;
import com.xdidian.keryhu.authserver.security.UserDetailsService;

import lombok.RequiredArgsConstructor;

/**
 * @ClassName: LoginAttemptUserServiceImpl
 * @Description: TODO(LoginAttemptUserService interface 具体的 实现类)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年4月30日 下午2:21:11
 */
@Component("loginAttemptUserService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginAttemptUserServiceImpl implements LoginAttemptUserService {

	private final LoginAttemptProperties loginAttemptProperties;
	private final LoginAttemptUserRepository repository;
	
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

	/**
	* <p>Title: loginFail</p>
	* <p>Description: 用户登陆失败，促发的事件，并且传递参数用户ip，和userid，此种情况是userId不为空</p>
	* 如果IP不存在于数据库，说明是第一次登陆，那么所有的基本信息都需要新建
	* 如果IP存在于数据库，
	* @param ip
	* @param userId
	* @see com.xdidian.keryhu.authserver.service.LoginAttemptUserService#loginFail(java.lang.String, java.lang.String)
	*/ 
	@Override
	public void loginFail(String ip, String loginName) {
		// TODO Auto-generated method stub
		//如果IP不存在于数据库
		if(!repository.findByRemoteIp(ip).isPresent()){
			
			LoginAttemptUser loginAttemptUser=new LoginAttemptUser();
			
			 Optional.of(loginAttemptUser).ifPresent(e->{
				    e.setRemoteIp(ip);
					//loginName不为空，执行存储动作
					addLoginName(loginName,e);
					e.setFirstAttemptTime(LocalDateTime.now());
					e.setAlreadyAttemptTimes(1);
					e.setAlreadyLockedTimes(0);
					e.setLocked(false);
					e.setLockedTime(null);
					repository.save(e);
			 });
		}
		
		//如果IP存在于数据库
		repository.findByRemoteIp(ip).ifPresent(e->{
			//loginName不为空，执行存储动作,且系统中并没有记录此loginName的时候，存储，将LoginName 存为数组
			addLoginName(loginName,e);
			
			int alreadyAttemptTimes=e.getAlreadyAttemptTimes();
			
			//如果尝试登陆失败的次数，大于等于设定的 最大尝试次数，那么将锁定该ip地址，
			if(alreadyAttemptTimes>=loginAttemptProperties.getMaxAttemptTimes()){
				e.setLocked(true);
				e.setLockedTime(LocalDateTime.now());
				e.setAlreadyLockedTimes(e.getAlreadyLockedTimes()+1);
			}
			else{
				//否则只需要单独 alreadyAttemptTimes＋1
				e.setAlreadyAttemptTimes(alreadyAttemptTimes+1);
			}
			
			repository.save(e);
			
		});
		
	}
	
	

	/**
	 
	 * Title: loginSuccess
	 * Description: 用户登陆成功后，所做的具体事情，登录成功，肯定能找的用户userId，所以就纪录数据库
	 * 如果ip不存在，则无需做任何事情，
	 * ip存在的情况下，分两种情况，
	 * 一： 用户账户未被锁定的情况： 就是恢复用户的一些数据（差不多是初始化）
	 * 二： 用户账户锁定的情况下：  如果已经锁定了账户，是没办法登录了，所以无需做任何事情
	 * @see com.xdidian.keryhu.authserver.service.LoginAttemptUserService#loginSuccess()
	 */
	@Override
	public void loginSuccess(String ip,String userId,String loginName) {
		// TODO Auto-generated method stub   
		
		repository.findByRemoteIp(ip)
		          .ifPresent(e->{
		        	  
		        	//如果ip存在于数据库的 情况下，且未被锁定的情况下
		        	  if(!e.isLocked()){
		        		  
		        		  addLoginName(loginName,e);
		        		  
		        		  //如果userId不存在于数据库的情况下，保存它到数据库
		  				  if(!repository.findByUserId(userId).isPresent()){
		  					e.setUserId(userId);
		  				  }
		  				  
		  				  //执行通用的初始化方法
		  				  initLoginAttemptInfo(e);
		  				  //保存结果
		  				  repository.save(e);
		        	  }
		          });
		
	}

	/**
	 * 
	 * @Title: isBlocked @Description: TODO(根据当前ip判断当前远程用户是否处于被锁定24小时状态。)
	 * 首先如果IP地址不存在于数据库，那么直接返回，就是 orElse 返回的 false
	 * 如果IP存在于数据库 ，且账户被locked(使用的Optional 的filter实现)，那么就判断第一次输错密码的时间，到现在有没有超过最大的 指定限制时间，
	 * 如果没有超过最大限定时间，则直接返回true，不需要做其他任何事情。
	 * 否则，执行后续的动作，意思是超时了－－，初始化数据库，增加被锁定的时间＋1，否则就说明账户还是处于被锁定状态
	 * 
	 * @param @param ip 
	 * @param @return 设定文件
	 *  @return boolean 返回类型 @throws
	 */
	@Override
	public boolean isBlocked(String ip) {
		
		//如果IP存在的情况下,且锁定的状态下，看是否超时，如果没有超时则直接返回true，如果超时了，且当前处于锁定状态,则执行恢复初始化状态，且返回false，默认返回false
		return repository.findByRemoteIp(ip)
				         .filter(e->e.getLockedTime()!=null)
				         .map(e->{			        	 
				        	 //获取用户第一次登陆失败的时间点
				    		 LocalDateTime attemptTime=e.getFirstAttemptTime();
				   
				    		 // 判断现在的时间点，是否已经超过了（上次被系统锁定的开始时间点＋设置的固定锁定时间）
				  		     boolean isLockedTimeOut = LocalDateTime.now()
				  					.isAfter(attemptTime.plusHours(loginAttemptProperties.getTimeOfLock()));
				  					
				  				logger.info(new StringBuffer("数据库中ip账户第一次失败时间 : ")
				  						.append(attemptTime)
				  						.append(" , 目前的ISO时间是 : ")
				  						.append(LocalDateTime.now())
				  						.append(" , 预计什么时间点过期 : ")
				  						.append(attemptTime.plusHours(loginAttemptProperties.getTimeOfLock()))
				  						.append(" , 目前锁定时间是否过期 : ")
				  						.append(isLockedTimeOut)
				  						.append(" , 距离第一次登陆失败到现在已经过去了多久 : ")
				  						.append(Duration.between(attemptTime, LocalDateTime.now()).toMinutes())
				  						.append(" , 距离解锁时间，还有多久 ？ ")
				  						.append(loginAttemptProperties.getTimeOfLock()*60-Duration.between(attemptTime, LocalDateTime.now()).toMinutes())
				  						.toString());
				  				
				  			   // 如果没有超时，且locded为true，那么直接返回true，不需要做其它任何动作
					 			if ((!isLockedTimeOut) && e.isLocked()) {
					 				return true;
					 			}
					 			
					 			// 否则需要初始化LoginAttemptInfo
				  				
				    			 initLoginAttemptInfo(e);			
				 				// 设置已经被系统锁定24小时的次数，增加1
				 				 e.setAlreadyLockedTimes(e.getAlreadyLockedTimes()+1);
				 			
				    		     //默认返回false	        	 
				        	     return false;
				         }).orElse(false);
		
	}

	/**
	 * @Title: initLoginAttemptUser
	 *  @Description: TODO(当LoginAttemptUser从锁定状态，解除的锁定或者登录成功的时候(促发下面的方法)，
	 *  设置user的初始化信息，并且保存于数据库。)
	 *  @param 设定文件
	 *  @return void 返回类型
	 *  @throws
	 */
	private void initLoginAttemptInfo(LoginAttemptUser loginAttemptUser) {

		//恢复第一次登录失败的时间点为null
		loginAttemptUser.setFirstAttemptTime(null);
		
		// 恢复当前ip地址用户的尝试次数为0
		loginAttemptUser.setAlreadyAttemptTimes(0);

		// 恢复锁定状态为false
		loginAttemptUser.setLocked(false);

		loginAttemptUser.setLockedTime(null);

	}
	 
	 /**
	  * 
	 * @Title: addLoginName
	 * @Description: TODO(将loginName增加到数据库的方法。)
	 * @param @param loginName
	 * @param @param loginAttemptUser    设定文件
	 * @return void    返回类型
	 * @throws
	  */
	 private void addLoginName(String loginName,LoginAttemptUser loginAttemptUser){
		 
		 if((!StringUtils.isNullOrEmpty(loginName))&&(!loginAttemptUser.containLoginName(loginName))){
			 loginAttemptUser.addLoginName(loginName);
   	  }
	 }
}
