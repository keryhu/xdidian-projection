package com.xdidian.keryhu.useraccount.service;

import com.xdidian.keryhu.useraccount.domain.User;
import com.xdidian.keryhu.useraccount.exception.EmailNotFoundException;
import com.xdidian.keryhu.useraccount.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

import static com.xdidian.keryhu.util.StringValidate.checkType;


/**
 * @Description : 继承自userService 的方法实现
 * @date : 2016年6月18日 下午9:23:30
 * @author : keryHu keryhu@hotmail.com
 */
@Component("userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  /**
   * 登录login 的登录名loginName(email或phone）查询数据库中是否存在user数据 主要用于login服务器的 userDetailsService
   * 的后台调用。spring－feign
   */

  @Override
  public Optional<User> findByLoginName(final String loginName) {
    // TODO Auto-generated method stub

    Optional<User> user;
    if (loginName == null || loginName.isEmpty()) {
      return Optional.empty();
    }
    switch (checkType(loginName)) {
      case EMAIL:
        user = repository.findByEmail(loginName.toLowerCase());
        break;
      case PHONE:
        user = repository.findByPhone(loginName);
        break;
      default:
        user = Optional.empty();
        break;

    }
    return user;

  }


  /**
   * 这里的id指，email、phone，或user中的id，3个里任何一种，来查看数据库的user
   */
  @Override
  public Optional<User> findById(final String id) {
    // TODO Auto-generated method stub
    Optional<User> user;
    if (id == null || id.isEmpty()) {
      return Optional.empty();
    }
    switch (checkType(id)) {
      case EMAIL:
        user = repository.findByEmail(id.toLowerCase());
        break;
      case PHONE:
        user = repository.findByPhone(id);
        break;
      case UUID:
        user = repository.findById(id);
        break;
      default:
        user = Optional.empty();
        break;

    }
    return user;
  }


  /**
   * 保存需要被保存的user 对象到mongo数据库 ,因为此方法不对外公开，所以所有的合法性验证都放在上一步的dto处理
   */
  @Override
  @Transactional
  public User save(final User user) {
    // TODO Auto-generated method stub
    return repository.save(user);

  }

  /**
   * 查看数据库中是否有此email
   */
  @Override
  public boolean emailIsExist(final String email) {
    // TODO Auto-generated method stub
    return repository.findByEmail(email).isPresent();
  }

  /**
   * 查看手机号是否存在
   */
  @Override
  public boolean phoneIsExist(final String phone) {
    // TODO Auto-generated method stub
    return repository.findByPhone(phone).isPresent();
  }

  /**
   * 查询数据库中是否有此公司名字
   */
  @Override
  public boolean companyNameIsExist(final String companyName) {
    // TODO Auto-generated method stub
    return repository.findByCompanyName(companyName).isPresent();
  }


  /**
   * 删除传递进来的user对象
   */
  @Transactional
  @Override
  public void deleteUser(final User user) {
    // TODO Auto-generated method stub
    Assert.notNull(user, "被删除的对象user不能为null");
    repository.findById(user.getId()).ifPresent(e -> repository.delete(e));
  }


  /**
   * 如果loginName找不到对于的user 数据，返回false，否则返回查询到的 emailStatus激活状态
   */
  @Override
  public boolean emailStatus(final String loginName) {
    // TODO Auto-generated method stub
    return findByLoginName(loginName).map(e -> e.isEmailStatus()).orElse(false);
  }


  /**
   * 用于login服务器，登录时，根据loginName查询系统中存在的email账户
   */
  @Override
  public String loginNameToEmail(final String loginName) {
    // TODO Auto-generated method stub
    return findById(loginName).map(e -> e.getEmail())
        .orElseThrow(() -> new EmailNotFoundException("您要查找的email不存在！"));

  }


}
