package com.xdidian.keryhu.user_account.rest;

import com.xdidian.keryhu.domain.Role;
import com.xdidian.keryhu.user_account.domain.AuthUserDto;
import com.xdidian.keryhu.user_account.domain.User;
import com.xdidian.keryhu.user_account.exception.UserNotFoundException;
import com.xdidian.keryhu.user_account.service.ConverterUtil;
import com.xdidian.keryhu.user_account.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserController {

  private final UserService userService;

  
  private final ConverterUtil converterUtil;



  /**
   * 根据唯一标志，email、phone，或user中的id，3个里任何一种，来查看数据库的user
   * @param identity
   * @return
   */
  
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/findByIdentity")
  public ResponseEntity<?> findByIdentity(@RequestParam("identity") String identity) {

    // 如果用户不存在，则抛出错误,返回json {"code":404,"message":"您查询的用户不存在！！"}
    User user = userService.findById(identity)
        .orElseThrow(() -> new UserNotFoundException("您输入的identity，数据库中不存在！！"));
    // 将User 转为 AuthUser对象
    AuthUserDto au = converterUtil.userToAuthUser.apply(user);
    return ResponseEntity.ok(au);
  }

  /**
   * 对外提供查询email是否存在数据库的api接口，不需要增加spring security验证
   */
  
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/isEmailExist")
  public Boolean isEmailExist(@RequestParam("email") String email) {
    return userService.emailIsExist(email);
  }
  


  /**
   * 对外提供查询phone是否存在与数据库，不需要增加spring security验证
   */
  
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/isPhoneExist")
  public Boolean isPhoneExist(@RequestParam("phone") String phone) {
    log.info("需要被查询的phone是： {} , phone  是否存在于数据库 ：{} ", phone, userService.phoneIsExist(phone));
    return userService.phoneIsExist(phone);
  }


  /**
   * 查询数据库中是否存在此公司名字
   */
  
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/isCompanyNameExist")
  public Boolean isCompanyNameExist(@RequestParam("companyName") String companyName) {
    log.info("查询公司名字是否存在，公司名字是 ：{} , 查到的 结果 为 ： {}", companyName,
        userService.companyNameIsExist(companyName));
    return userService.companyNameIsExist(companyName);
  }

  /**
   * rest 接口查询当前loginName所在的用户，邮箱是否已经激活，如果激活，返回ture，默认是false
   */
  
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/emailStatus")
  public Boolean emailStatus(@RequestParam("loginName") String loginName) {
    return userService.emailStatus(loginName);
  }
  
  /**
   * rest 接口查询当前loginName所在的用户，手机是否已经激活，如果激活，返回ture，默认是false
   */
  
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/phoneStatus")
  public Boolean phoneStatus(@RequestParam("loginName") String loginName) {
    return userService.phoneStatus(loginName);
  }
  
  

  /**
   * 根据提供的loginName，查询到数据库中此用户拥有的roles 如果email或phone
   * 不存在于数据库，返回空的roles，否则返回roles 的String 类型的List
   */
  
  @RequestMapping(method = RequestMethod.GET, value = "/users/query/roles")
  public List<Role> findRolesByLoginName(@RequestParam("loginName") String loginName) {


    return userService.findByLoginName(loginName).filter(e -> e.getRoles() != null)
        .map(e -> e.getRoles()).orElse(new ArrayList<Role>());


  }

 
}
