package com.xdidian.keryhu.useraccount.repository;

import com.xdidian.keryhu.useraccount.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


/**
 * 此数据库存储的user 不会直接对外提供rest api
 * 
 *
 * 操作示范：
 *
 * post 保存user
 *
 * curl -i -X POST -H "Content-Type:application/json" -d '{ "firstName":xx}'
 * http://localhost:8001/users
 *
 * put 更新user,多用户更新除了id之外的所有变量更新
 *
 * curl -X PUT -H "Content-Type:application/json" -d '{ "firstName": "Bil"}'
 * http://localhost:8001/users/9c8154f1-fd44-4339-8e3d-806245278414
 *
 *
 * patch 局部更新user，只更新部分信息，用户用户资料变更。
 *
 * curl -X PATCH -H "Content-Type:application/json" -d '{ "firstName": "xx"}'
 * http://localhost:8001/users/9c8154f1-fd44-4339-8e3d-806245278414
 *
 * delete 用户
 *
 * curl -X DELETE http://localhost:8080/people/53149b8e3004990b1af9f229
 */


/**
 * @Description : user 的数据库操作
 * @date : 2016年6月18日 下午9:20:55
 * @author : keryHu keryhu@hotmail.com
 */
public interface UserRepository extends MongoRepository<User, String> {


  // http://localhost:8001/users/search/findByEmail?email=au@aua.com name后面的就是参数
  // @PreAuthorize("permitAll")
  public Optional<User> findByEmail(String email);

  public Optional<User> findByPhone(String phone);

  public Optional<User> findById(String id);

  public Optional<User> findByCompanyName(String companyName);

  public Optional<User> deleteById(String id);



}
