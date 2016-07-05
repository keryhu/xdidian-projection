package com.xdidian.keryhu.auth_server.service;


import com.xdidian.keryhu.auth_server.client.UserClient;
import com.xdidian.keryhu.auth_server.domain.AuthUserDto;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


/**
 * 
 * @Description : Auth-server的service
 * @date : 2016年6月18日 下午8:07:19
 * @author : keryHu keryhu@hotmail.com
 */
@Service("userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

  private final UserClient userClient;

  /**
   * 根据User 的 唯一标志符 用户登录的帐号。例如 email ，phone 等查询唯一的数据库用户
   */
  @Override
  public Optional<AuthUserDto> findByLoginName(String loginName) {

    return Optional.ofNullable(userClient.ByLoginName(loginName));
  }
  
  /**
   * 根据id，email，phone任何一种来查询user是否存在
   * @param identity
   * @return
   */
  @Override
  public Optional<AuthUserDto> findByIdentity(String identity) {
    // TODO Auto-generated method stub
    
    return Optional.ofNullable(userClient.findByIdentity(identity));
  }
  

  /**
   * 获取当前ip地址
   */
  @Override
  public String getIP(HttpServletRequest request) {
    // TODO Auto-generated method stub
    String xfHeader = request.getHeader("X-Forwarded-For");
    return (xfHeader == null || xfHeader.isEmpty()) ? request.getRemoteAddr()
        : xfHeader.split(",")[0];

  }



 


}
