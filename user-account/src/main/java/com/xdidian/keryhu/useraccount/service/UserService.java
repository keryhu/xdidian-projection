package com.xdidian.keryhu.useraccount.service;

import com.xdidian.keryhu.useraccount.domain.User;

import java.util.Optional;



/**
 * @Description : user-account 的一些service
 * @date : 2016年6月18日 下午9:23:16
 * @author : keryHu keryhu@hotmail.com
 */
public interface UserService {


  /**
   * 查看数据库中是否存在指定的loginName，有email或者phone两种格式
   */
  public Optional<User> findByLoginName(final String loginName);


  /**
   * 这里的id指，email、phone，或user中的id，3个里任何一种，来查看数据库的user
   */
  public Optional<User> findById(String id);

  /**
   * 将用户保存于数据库
   */
  public User save(final User user);


  /**
   * 查看email是否存在于数据库
   */
  public boolean emailIsExist(final String email);


  /**
   * 查看手机号是否存在于数据库
   */
  public boolean phoneIsExist(final String phone);


  /**
   * 查看公司名字是否存在于数据库
   */
  public boolean companyNameIsExist(final String companyName);


  /**
   * 删除传递进来的user对象
   */
  public void deleteUser(final User user);


  /**
   * 根据loginName查看所在的user数据库的emailStatus状态，
   */
  public boolean emailStatus(final String loginName);

  /**
   * 根据登录账号email或者phone，查找数据库系统中对应的email 用于login 时rest 需求
   */
  public String loginNameToEmail(final String loginName);


}
