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
import java.time.Period;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.amazonaws.util.StringUtils;
import com.ctc.wstx.util.StringUtil;
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
	* @param ip
	* @param userId
	* @see com.xdidian.keryhu.authserver.service.LoginAttemptUserService#loginFail(java.lang.String, java.lang.String)
	*/ 
	@Override
	public void loginFail(String ip, String loginName) {
		// TODO Auto-generated method stub
		//如果ip不存在于数据库，说明是第一次记录此ip地址失败登陆，需要新建一个LoginAttemptUser
		
		if(!isIpExist(ip)){
			LoginAttemptUser loginAttemptUser=new LoginAttemptUser();
			loginAttemptUser.setRemoteIp(ip);
			//loginName不为空，执行存储动作
			if(!StringUtils.isNullOrEmpty(loginName)){
				loginAttemptUser.addLoginName(loginName);
			}
			
			loginAttemptUser.setFirstAttemptTime(LocalDateTime.now());
			loginAttemptUser.setAlreadyAttemptTimes(1);
			loginAttemptUser.setAlreadyLockedTimes(0);
			loginAttemptUser.setLocked(false);
			loginAttemptUser.setLockedTime(null);
			repository.save(loginAttemptUser);
		}
		
		//如果ip存在于数据库
		else {
			LoginAttemptUser loginAttemptUser = repository.findByRemoteIp(ip).get();
			//loginName不为空，执行存储动作,且系统中并没有记录此loginName的时候，存储
			
			if((!StringUtils.isNullOrEmpty(loginName))&&(!loginAttemptUser.containLoginName(loginName))){
				loginAttemptUser.addLoginName(loginName);
			}
			int alreadyAttemptTimes=loginAttemptUser.getAlreadyAttemptTimes();
			
			//如果尝试登陆失败的次数，大于等于设定的 最大尝试次数，那么将锁定该ip地址，
			if(alreadyAttemptTimes>=loginAttemptProperties.getMaxAttemptTimes()){
				loginAttemptUser.setLocked(true);
				loginAttemptUser.setLockedTime(LocalDateTime.now());
				int alreadyLockedTimes=loginAttemptUser.getAlreadyLockedTimes();
				loginAttemptUser.setAlreadyLockedTimes(alreadyLockedTimes+1);
			} else{
				
				loginAttemptUser.setAlreadyAttemptTimes(alreadyAttemptTimes+1);
			}
			
			
			repository.save(loginAttemptUser);
			
		}
		
	}
	
	

	/**
	 
	 * Title: loginSuccess
	 * Description:
	 * @see com.xdidian.keryhu.authserver.service.LoginAttemptUserService#loginSuccess()
	 */
	@Override
	public void loginSuccess() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @Title: isBlocked @Description: TODO(根据当前ip判断当前远程用户是否处于被锁定24小时状态。)
	 * 如果ip地址不存在于数据库，那么直接返回false
	 * 
	 * @param @param ip @param @return 设定文件 @return boolean 返回类型 @throws
	 */
	@Override
	public boolean isBlocked(String ip) {
		
		// 如果ip地址不存在于数据库，那么返回false，
		if (!isIpExist(ip)) {
			return false;
		}

		LoginAttemptUser loginAttemptUser = repository.findByRemoteIp(ip).get();

		// 如果ip地址存在于数据库，如果账户ip已经被locked，那么(判断现在时间-锁定时间)是否大于固定的
		// 锁定时间，如果大于，那么返回false，且设置LoginAttemptUser 初始化状态,如果没有超过那个时间
		//
		
		LocalDateTime attemptTime=loginAttemptUser.getFirstAttemptTime(); //获取用户第一次登陆失败的时间点

		if(loginAttemptUser.getLockedTime()!=null) {
			// 判断现在的时间点，是否已经超过了（上次被系统锁定的开始时间点＋设置的固定锁定时间）
			boolean isLockedTimeOut = LocalDateTime.now()
					.isAfter(attemptTime.plusHours(loginAttemptProperties.getTimeOfLock()));

			
			//现在时间－锁定时间 的分钟数
			
			long  betweenMinutes=Duration.between(attemptTime, LocalDateTime.now()).toMinutes();
			
			long stillMinute=loginAttemptProperties.getTimeOfLock()*60-betweenMinutes;
			
			
			logger.info("数据库中ip账户第一次失败时间 : "+attemptTime+" , 目前的ISO时间是 : "+LocalDateTime.now()+
					" , 预计什么时间点过期 : "+attemptTime.plusHours(loginAttemptProperties.getTimeOfLock())+
					" , 目前锁定时间是否过期 : "+isLockedTimeOut+" , 距离第一次登陆失败到现在已经过去了多久 : "+betweenMinutes+" , 距离解锁时间，还有多久 ？ "+stillMinute);
			
			// 如果已经超时，那么运行LoginAttempt 初始化 方法。并且返回false
			if (isLockedTimeOut) {
				initLoginAttemptInfoBecauseLockedTimeOut(loginAttemptUser);
				return false;
			}

			// 如果没有超时，且locded为true，那么返回true
			if ((!isLockedTimeOut) && loginAttemptUser.isLocked()) {
				return true;
			}
		}
		
		

		return false;
	}

	/**
	 * @Title: initLoginAttemptUser
	 *  @Description: TODO(当LoginAttemptUser从锁定状态，解除的锁定的时候(也就是锁定时间超时的 时候，促发下面的方法)，
	 *  设置user的初始化信息，并且保存于数据库。)
	 *  @param 设定文件
	 *  @return void 返回类型
	 *  @throws
	 */
	private void initLoginAttemptInfoBecauseLockedTimeOut(LoginAttemptUser loginAttemptUser) {

		// Assert.notNull(loginAttemptUser,"传入的loginAttemptUser不能为null");

		// 恢复当前ip地址用户的尝试次数为0
		loginAttemptUser.setAlreadyAttemptTimes(0);

		// 恢复锁定状态为false
		loginAttemptUser.setLocked(false);

		
		// 设置已经被系统锁定24小时的次数，增加1
		loginAttemptUser.setAlreadyLockedTimes(loginAttemptUser.getAlreadyLockedTimes()+1);

		loginAttemptUser.setLockedTime(null);

	}
	
	 private boolean isIpExist(String ip){
		 
		// 输入的ip地址不能为
		Assert.hasLength(ip, "ip地址不能为空！");
		
		return repository.findByRemoteIp(ip).isPresent();
	 }

	

	
}
