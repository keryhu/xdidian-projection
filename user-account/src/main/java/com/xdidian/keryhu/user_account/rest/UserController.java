package com.xdidian.keryhu.user_account.rest;

import com.xdidian.keryhu.user_account.domain.AuthUserDto;
import com.xdidian.keryhu.user_account.domain.User;
import com.xdidian.keryhu.user_account.exception.UserNotFoundException;
import com.xdidian.keryhu.user_account.service.ConverterUtil;
import com.xdidian.keryhu.user_account.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
  
  @GetMapping("/users/query/findByIdentity")
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
  
  @GetMapping("/users/query/isEmailExist")
  public Boolean isEmailExist(@RequestParam("email") String email) {
    return userService.emailIsExist(email);
  }
  


  /**
   * 对外提供查询phone是否存在与数据库，不需要增加spring security验证
   */
  
  @GetMapping("/users/query/isPhoneExist")
  public Boolean isPhoneExist(@RequestParam("phone") String phone) {
    log.info("需要被查询的phone是： {} , phone  是否存在于数据库 ：{} ", phone, userService.phoneIsExist(phone));
    return userService.phoneIsExist(phone);
  }


  /**
   * rest 接口查询当前loginName所在的用户，邮箱是否已经激活，如果激活，返回ture，默认是false
   */
  
  @GetMapping("/users/query/emailStatus")
  public Boolean emailStatus(@RequestParam("loginName") String loginName) {
    return userService.emailStatus(loginName);
  }
  
  /**
   * rest 接口查询当前loginName所在的用户，手机是否已经激活，如果激活，返回ture，默认是false
   */
  
  @GetMapping("/users/query/phoneStatus")
  public Boolean phoneStatus(@RequestParam("loginName") String loginName) {
    return userService.phoneStatus(loginName);
  }
  
 /**
  * 
 * @Title: findByBirthday
 * @Description: TODO(根据当前用户的companyId，然后进入数据库查询满足companyId和生日为指定日期的user
 * 然后他们的name 的 list)
 * @param @param month
 * @param @param day
 * @param @return    设定文件
 * @return List<User>    返回类型
 * @throws
  */
  
  
  @GetMapping("/users/query/birthday")
  public ResponseEntity<?> findByBirthday(@AuthenticationPrincipal User user,@RequestParam("month") int month,@RequestParam("day") int day){
    String companyId=user.getCompanyId();
    LocalDate date=LocalDate.of(0, month, day);
    List<String> names= userService.findByBirthdayAndCompanyId(date, companyId)
         .stream()
         .filter(e->e.getName()!=null)
         .map(e->e.getName())
         .collect(Collectors.toList());
    
    return ResponseEntity.ok(names);
  }
 
  
  @GetMapping("/users/query/isInCompany")
  public boolean isInComopany(@RequestParam("id") String id){
    return userService.isInComopany(id);
  }
  
}
