/**  
* @Title: EmailActivatedTokenRepository.java
* @Package com.xdidian.keryhu.emailActivate.repository
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月11日 上午11:32:00
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.repository;

import com.xdidian.keryhu.emailActivate.domain.ActivatedToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Description : email激活码数据库操作的接口
 * Date : 2016年06月18日 上午8:38
 * Author : keryHu keryhu@hotmail.com
 */
public interface ActivatedTokenRepository extends MongoRepository<ActivatedToken,String> {

	public Optional<ActivatedToken>  findByEmail(String email);
		
	public Optional<ActivatedToken> deleteByEmail (String email);
	
}
