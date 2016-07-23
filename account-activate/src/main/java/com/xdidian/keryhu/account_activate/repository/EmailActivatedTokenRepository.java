package com.xdidian.keryhu.account_activate.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xdidian.keryhu.account_activate.domain.EmailActivatedToken;

import java.util.Optional;


/**
 * 
 * @Description : email激活码数据库操作的接口
 * @date : 2016年6月18日 下午8:13:25
 * @author : keryHu keryhu@hotmail.com
 */
public interface EmailActivatedTokenRepository extends MongoRepository<EmailActivatedToken, String> {

  public Optional<EmailActivatedToken> findByEmail(String email);

}
