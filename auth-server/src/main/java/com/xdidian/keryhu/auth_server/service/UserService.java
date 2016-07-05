package com.xdidian.keryhu.auth_server.service;


import javax.servlet.http.HttpServletRequest;

import com.xdidian.keryhu.auth_server.domain.AuthUserDto;

import java.util.Optional;

/**
 * 
 * @Description : Auth-service的一些方法接口
 * @date : 2016年6月18日 下午8:07:00
 * @author : keryHu keryhu@hotmail.com
 */
public interface UserService {

  /**
   * 根据登录名来查询数据库的user
   */
  public Optional<AuthUserDto> findByLoginName(String loginName);
  
  /**
   * 根据id，email，phone任何一种来查询user是否存在
   * @param identity
   * @return
   */
  public Optional<AuthUserDto> findByIdentity(String identity);
  /**
   * 获取客户端当前ip
   */
  public String getIP(HttpServletRequest request);


}
