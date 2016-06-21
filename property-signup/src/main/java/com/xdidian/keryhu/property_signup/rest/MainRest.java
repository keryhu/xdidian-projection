package com.xdidian.keryhu.property_signup.rest;


import com.xdidian.keryhu.domain.EmailActivatedDto;
import com.xdidian.keryhu.property_signup.client.UserAccountClient;
import com.xdidian.keryhu.property_signup.domain.HostProperty;
import com.xdidian.keryhu.property_signup.domain.PropertyForm;
import com.xdidian.keryhu.property_signup.service.ConverterUtil;
import com.xdidian.keryhu.property_signup.service.UserService;
import com.xdidian.keryhu.property_signup.stream.EmailActivatedProducer;
import com.xdidian.keryhu.property_signup.stream.SaveProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@EnableConfigurationProperties(HostProperty.class)
public class MainRest {

  private final HostProperty hostProperty;

  private final ConverterUtil converterUtil;

  private final UserAccountClient userClient;

  private final UserService userService;

  private final SaveProducer saveproducer;

  private final EmailActivatedProducer emailActivatedProducer;

  /**
   * 验证输入信息的合法性的方法只方法逻辑层，在此，user－account里面不做判断，以为就算黑客恶意注册了，
   * 那么他也做不了什么事情，因为接下来需要邮件验证，手机验证，营业执照验证等，而且admin会定期检测账户 在注册完后，页面自动导航到 result 页面，并且附带了
   * 2个带有email信息的链接
   */
  @RequestMapping(method = RequestMethod.POST, value = "/signup")
  public ModelAndView createUser(@RequestBody final PropertyForm propertyForm,
      final RedirectAttributes attr) {

    // 验证输入信息的合法性
    userService.vlidateBeforSave(propertyForm);

    EmailActivatedDto emailActivatedDto =
        converterUtil.propertyFormToEmailActivatedDto.apply(propertyForm);
    // 发送email激活的message出去。
    emailActivatedProducer.send(emailActivatedDto);


    // 用户注册完，发送dto具体信息的message 给 user-account,用于保存
    saveproducer.send(converterUtil.propertyFormToRegisterDto.apply(propertyForm));

    ModelAndView mav = new ModelAndView();

    // 重新发送的url
    String resend = new StringBuffer(hostProperty.getHostName())
        .append(":8080/email-activate/email/resend?email=").append(propertyForm.getEmail())
        .append("&token=").append(emailActivatedDto.getResendToken()).toString();

    // 重新注册的url
    String reregister = new StringBuffer(hostProperty.getHostName())
        .append(":8080/email-activate/email/reregister?email=").append(propertyForm.getEmail())
        .append("&token=").append(emailActivatedDto.getReregisterToken()).toString();
    // 注册完后，导航到的页面

    String redirectUrl = new StringBuffer("redirect:").append(hostProperty.getHostName())
        .append(":8080/register/result").toString();
    attr.addAttribute("resend", resend);
    attr.addAttribute("reregister", reregister);
    mav.setViewName(redirectUrl);
    return mav;

  }


  /**
   * 用于前台物业公司注册时，查询登陆的email是否存在于数据库，此为调用的后台接口
   */
  @RequestMapping(value = "/query/isEmailExist", method = RequestMethod.GET)
  public ResponseEntity<?> isEmailExist(@RequestParam("email") final String email) {
    log.info("查到的email 是否存在于 数据库 ：{} ", userClient.isEmailExist(email));
    return ResponseEntity.ok(userClient.isEmailExist(email));
  }


  /**
   * 用于前台物业公司注册时，查询登陆的phone是否存在于数据库，此为调用的后台接口
   */
  @RequestMapping(value = "/query/isPhoneExist", method = RequestMethod.GET)
  public ResponseEntity<?> isPhoneExist(@RequestParam("phone") final String phone) {
    log.info("查到的手机号是否存在于数据库： {}", userClient.isPhoneExist(phone));
    return ResponseEntity.ok(userClient.isPhoneExist(phone));
  }


  /**
   * 查询数据库中是否存在此公司名字
   */
  @RequestMapping(method = RequestMethod.GET, value = "/query/isCompanyNameExist")
  public ResponseEntity<?> isCompanyNameExist(
      @RequestParam("companyName") final String companyName) {

    log.info("查的公司名字是否在数据库中：{} ", userClient.isCompanyNameExist(companyName));
    return ResponseEntity.ok(userClient.isCompanyNameExist(companyName));
  }


  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @RequestMapping("/admin")
  public String admin() {
    return "property-register this is admin role page ";
  }


}
