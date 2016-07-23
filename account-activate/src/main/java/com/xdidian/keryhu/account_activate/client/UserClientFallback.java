package com.xdidian.keryhu.account_activate.client;

import com.xdidian.keryhu.domain.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @Description : feign 连接失败的一个默认方法。
 * @date : 2016年6月18日 下午8:09:32
 * @author : keryHu keryhu@hotmail.com
 */
@Component
public class UserClientFallback implements UserClient {


  /**
   * 如果调用UserAccountClient 对应的spring feign 网络失败，则此方法生效
   */
  @Override
  public Boolean isEmailExist(String email) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * 当局登录名loginName，前台web，ajax查询当前loginName所在的数据库，email是否激活 这个是默认的失败调用
   */
  @Override
  public Boolean emailStatus(String loginName) {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public Boolean phoneStatus(String loginName) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * 根据登录名查询用户所拥有的role权限
   */
  @Override
  public List<Role> findRolesByLoginName(String loginName) {
    // TODO Auto-generated method stub
    return new ArrayList<Role>();

  }

  @Override
  public Boolean isPhoneExist(String phone) {
    // TODO Auto-generated method stub
    return null;
  }

 


}
