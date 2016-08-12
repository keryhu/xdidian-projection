package com.xdidian.keryhu.user_account.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.xdidian.keryhu.user_account.domain.User;



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
   * 删除传递进来的user对象
   */
  public void deleteUser(final User user);


  /**
   * 根据loginName查看所在的user数据库的emailStatus状态，
   */
  public boolean emailStatus(final String loginName);


  /**
   * 根据loginName查看所在的user数据库的phoneStatus状态，
   */
  public boolean phoneStatus(final String loginName);


  /**
   * 
  * @Title: findByBirthday
  * @Description: TODO(找出生日是某个时间的user)
  * @param @param date
  * @param @return    设定文件
  * @return List<User>    返回类型
  * @throws
   */
  public List<User> findByBirthdayAndCompanyId(final LocalDate date,final String companyId);
  
  
  /**
   * 
  * @Title: isInComopany
  * @Description: TODO(查看制定的user id的用户，是否已经在公司组织里)
  * @param @param id
  * @param @return    设定文件
  * @return boolean    返回类型
  * @throws
   */
  
  public boolean isInComopany( final String id);
}
