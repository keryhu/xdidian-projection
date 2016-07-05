package com.xdidian.keryhu.pc_gateway.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xdidian.keryhu.pc_gateway.domain.RefreshToken;

/**
 * @Description : TODO(查找数据库中的refreshToken值)
 * @date : 2016年7月3日 下午3:24:38
 * @author : keryHu keryhu@hotmail.com
 */
public interface RefreshTokenRepository extends MongoRepository<RefreshToken,String>{
  
  public Optional<RefreshToken> findByLoginName(String loginName);
  
  public Optional<RefreshToken> findById(String id);

}
