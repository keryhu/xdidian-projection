package com.xdidian.keryhu.authserver.service;


import com.xdidian.keryhu.authserver.domain.AuthUserDto;

import javax.servlet.http.HttpServletRequest;
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
   * 获取客户端当前ip
   */
  public String getIP(HttpServletRequest request);


}
