/**  
* @Title: LoginAttemptUserRepository.java
* @Package com.xdidian.keryhu.authserver.repository
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年4月30日 下午1:55:49
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xdidian.keryhu.authserver.domain.LoginAttemptUser;

/**
* @ClassName: LoginAttemptUserRepository
* @Description: TODO(记录LoginAttemptUser的 mongo data rest repository)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月30日 下午1:55:49
*/
public interface LoginAttemptUserRepository extends MongoRepository<LoginAttemptUser,String> {
	
	/**
	 * 
	* @Title: findByRemoteIp
	* @Description: TODO(根据remoteIp寻找系统的记录)
	* @param @param remoteIp
	* @param @return    设定文件
	* @return Optional<LoginAttemptUser>    返回类型
	* @throws
	 */
	public Optional<LoginAttemptUser>  findByRemoteIp(String remoteIp);
	
	/**
	 * 
	* @Title: findByUserId
	* @Description: TODO(根据用户的登陆名，来查询相应的LoginAttemptUser数据)
	* @param @param userId
	* @param @return    设定文件
	* @return Optional<LoginAttemptUser>    返回类型
	* @throws
	 */
	public Optional<LoginAttemptUser>  findByLoginName(List<String> loginName);
	
	/**
	 * 
	* @Title: findById
	* @Description: TODO(根据LoginAttemptUser本身的id来查询。)
	* @param @param id
	* @param @return    设定文件
	* @return Optional<LoginAttemptUser>    返回类型
	* @throws
	 */
	public Optional<LoginAttemptUser> findById(String id);
	
	/**
	 * 
	* @Title: findByUserId
	* @Description: TODO(如果userId存在的情况下，通过此参数查询数据库)
	* @param @param userId
	* @param @return    设定文件
	* @return Optional<LoginAttemptUser>    返回类型
	* @throws
	 */
	public Optional<LoginAttemptUser> findByUserId(String userId);

	

}
