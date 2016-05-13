/**  
* @Title: EmailActivatedTokenRepository.java
* @Package com.xdidian.keryhu.emailActivate.repository
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月11日 上午11:32:00
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xdidian.keryhu.emailActivate.domain.ActivatedToken;


/**
* @ClassName: EmailActivatedTokenRepository
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月11日 上午11:32:00
*/
public interface ActivatedTokenRepository extends MongoRepository<ActivatedToken,String> {

	public Optional<ActivatedToken>  findByEmail(String email);
		
	public Optional<ActivatedToken> deleteByEmail (String email);
	
}
