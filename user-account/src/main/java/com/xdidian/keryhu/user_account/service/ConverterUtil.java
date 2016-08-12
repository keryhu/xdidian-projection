package com.xdidian.keryhu.user_account.service;

import com.xdidian.keryhu.domain.Role;
import com.xdidian.keryhu.domain.SignupDto;
import com.xdidian.keryhu.user_account.domain.AuthUserDto;
import com.xdidian.keryhu.user_account.domain.User;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.Function;


@Component
public class ConverterUtil {


  /**
   * 将User 转为 AuthUser 对象。
   */
  public Function<User, AuthUserDto> userToAuthUser =
      x -> new AuthUserDto(x.getId(),x.getEmail(), x.getPassword(), x.getRoles(), x.isEmailStatus());


  /**
   * 将用户注册数据转为User对象。此时将用户注册时间和权限加进去,注册用户加上默认的权限，只有等到有公司了，有了读取整个部门
   * 的权限后，才会加上其他权限。
   */
  public Function<SignupDto, User> signupDtoToUser = x -> {

    User user = new User();
    user.setEmail(x.getEmail());
    user.setPhone(x.getPhone());
    user.setPassword(x.getPassword());
    user.setRoles(Arrays.asList(Role.ROLE_DEFAULT));
    user.setRegisterTime(LocalDateTime.now());
    user.setEmailStatus(false);
    user.setCompanyId(null);;
    return user;
  };


}
