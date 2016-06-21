package com.xdidian.keryhu.email_activate.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xdidian.keryhu.email_activate.domain.ActivatedToken;

import java.util.Optional;


/**
 * 
 * @Description : email激活码数据库操作的接口
 * @date : 2016年6月18日 下午8:13:25
 * @author : keryHu keryhu@hotmail.com
 */
public interface ActivatedTokenRepository extends MongoRepository<ActivatedToken, String> {

  public Optional<ActivatedToken> findByEmail(String email);

  public Optional<ActivatedToken> deleteByEmail(String email);

}
