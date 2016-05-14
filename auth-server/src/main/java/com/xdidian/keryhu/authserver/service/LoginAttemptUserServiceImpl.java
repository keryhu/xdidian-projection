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
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.xdidian.keryhu.authserver.domain.LoginAttemptProperties;
import com.xdidian.keryhu.authserver.domain.LoginAttemptUser;
import com.xdidian.keryhu.authserver.repository.LoginAttemptUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: LoginAttemptUserServiceImpl
 * @Description: TODO(LoginAttemptUserService interface 具体的 实现类)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年4月30日 下午2:21:11
 */
@Component("loginAttemptUserService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class LoginAttemptUserServiceImpl implements LoginAttemptUserService {

	private final LoginAttemptProperties loginAttemptProperties;
	private final LoginAttemptUserRepository repository;
	

	/**
	* <p>Title: loginFail</p>
	* <p>Description: 用户登陆失败，促发的事件，并且传递参数用户ip，和userid，此种情况是userId不为空</p>
	* 如果IP不存在于数据库，说明是第一次登陆，那么所有的基本信息都需要新建
	* 如果IP存在于数据库，首先判断清零时间是否到
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
		
		
		repository.findByRemoteIp(ip).ifPresent(e->{
			
			//如果FirstAttemptTime 为null的话，就设置为当前时间
			if(e.getFirstAttemptTime()==null){
				e.setFirstAttemptTime(LocalDateTime.now());
			}
			
			//指定的输错自动恢复的周期时间，是否已到(例如设定了3个小时内最多输入10次，就锁定账户，那么只要3个小时内次数没有超过10次，
			//就可以继续登录平台，过了3个小时，输入次数恢复到0，第一次输错时间恢复为null
			
		    boolean isPeriodExpired=LocalDateTime.now().isAfter(
		    		e.getFirstAttemptTime().plusHours(loginAttemptProperties.getTimeOfPerid()));
		    if(isPeriodExpired){
		    	//如果已经到了恢复时间，那么就恢复到初始值＋第一次输错的时间和次数
		    	e.setFirstAttemptTime(LocalDateTime.now());
		    	e.setAlreadyAttemptTimes(1);	
		    } else {
		    	//如果输错次数已经等同于规定的最大次数－1
		    	
		    	if(e.getAlreadyAttemptTimes()==loginAttemptProperties.getMaxAttemptTimes()-1){
		    		e.setLocked(true);
		    		e.setLockedTime(LocalDateTime.now());    		
		    	}
		    	AtomicInteger atomic=new AtomicInteger(e.getAlreadyAttemptTimes());
		    	//原子性＋1
		    	e.setAlreadyAttemptTimes(atomic.incrementAndGet());  	
		    }
		    addLoginName(loginName,e);
		    repository.save(e);
			
		});
		
	}
	
	

	/**
	 
	 * Title: loginSuccess
	 * Description: 用户登陆成功后，所做的具体事情，登录成功，肯定能找的用户userId，所以就纪录数据库
	 * 如果ip不存在，则无需做任何事情，
	 * ip存在的情况下，恢复默认数据
	 * @see com.xdidian.keryhu.authserver.service.LoginAttemptUserService#loginSuccess()
	 */
	@Override
	public void loginSuccess(String ip,String userId) {
		// TODO Auto-generated method stub   
		
		repository.findByRemoteIp(ip)
		          .ifPresent(e->{  
		        	      e.setFirstAttemptTime(null);
		        	      e.setAlreadyAttemptTimes(0);	        		  
		        		  //如果userId不存在于数据库的情况下，保存它到数据库
		  				  if(!repository.findByUserId(userId).isPresent()){
		  					e.setUserId(userId);
		  				  }
		  				  repository.save(e);
		        	 
		          });		
	}

	/**
	 * 
	 * @Title: isBlocked @Description: TODO(根据当前ip判断当前远程用户是否处于被锁定24小时状态。)
	 * 首先如果IP地址不存在于数据库，那么直接返回，就是 orElse 返回的 false
	 * 如果IP存在于数据库 ，且账户被locked(使用的Optional 的filter实现)，那么就判断锁定的时间，到现在有没有超过最大的 指定限制时间，
	 * 如果没有超过最大限定时间，则直接返回true，不需要做其他任何事情。
	 * 否则，执行后续的动作，意思是超时了－－，初始化数据库，增加被锁定的时间＋1，否则就说明账户还是处于被锁定状态
	 * 
	 * @param @param ip 
	 * @param @return 设定文件
	 *  @return boolean 返回类型 @throws
	 */
	@Override
	public boolean isBlocked(String ip) {
		
		//锁定的状态下，看是否超时，如果没有超时则直接返回true，如果超时了，且当前处于锁定状态,则执行恢复初始化状态，且返回false，默认返回false
		return repository.findByRemoteIp(ip)
				         .filter(e->e.isLocked())
				         .map(e->{			        	 
				        	 //获取用户锁定的时间点
				    		 LocalDateTime lockedTime=e.getLockedTime();
				  				log.info(new StringBuffer("数据库中ip账户锁定的时间点是 : ")
				  						.append(lockedTime)
				  						.append(" , 目前的ISO时间是 : ")
				  						.append(LocalDateTime.now())
				  						.append(" , 预计什么时间点过期 : ")
				  						.append(lockedTime.plusHours(loginAttemptProperties.getTimeOfLock()))
				  						.append(" , 目前锁定时间是否过期 : ")
				  						.append(isLockedTimeOut(e))
				  						.append(" , 距离上次锁定时间到现在已经过去了多久 : ")
				  						.append(Duration.between(lockedTime, LocalDateTime.now()).toMinutes())
				  						.append(" , 距离解锁时间，还有多久 ？ ")
				  						.append(loginAttemptProperties.getTimeOfLock()*60-Duration.between(lockedTime, LocalDateTime.now()).toMinutes())
				  						.toString());
				  				
				  			   // 如果没有超时，且locded为true，那么直接返回true，不需要做其它任何动作
					 			if (!isLockedTimeOut(e)) {
					 				return true;
					 			} 
					 			 //否则就是超时了，那么就执行恢复动作
					 			else {
					 				 e.setFirstAttemptTime(null);	
					 				 e.setAlreadyAttemptTimes(0);
					 				 e.setLocked(false);
					 				 e.setLockedTime(null);
					 				 AtomicInteger atomic=new AtomicInteger(e.getAlreadyLockedTimes());
					 				 //原子性＋1
					 				 e.setAlreadyLockedTimes(atomic.incrementAndGet());
					 				 repository.save(e);
					 				 return false;
					 			}
				         }).orElse(false);
				
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
		 
		 boolean loginNameEmpty=loginName==null||loginName.isEmpty();
		 
		 if((!loginNameEmpty)&&(!loginAttemptUser.containLoginName(loginName))){
			 loginAttemptUser.addLoginName(loginName);
   	  }
	 }
	 
	 /**
	  * 
	 * @Title: isLockedTimeOut
	 * @Description: TODO(判断系统中用户,此时锁定状态有没有超时)
	 * @param @param loginAttemptUser
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @throws
	  */
	 private boolean isLockedTimeOut(LoginAttemptUser loginAttemptUser){
		 
		return  LocalDateTime.now().isAfter(
				   loginAttemptUser.getLockedTime().plusHours(loginAttemptProperties.getTimeOfLock()));
		  
	 }
}
