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
  public ResponseEntity<?> createUser(@RequestBody final PropertyForm propertyForm,
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
        .append("&token=").append(emailActivatedDto.getResignupToken()).toString();
    // 注册完后，导航到的页面

    String redirectUrl = new StringBuffer("redirect:").append(hostProperty.getHostName())
        .append(":8080/register/result").toString();
   
    mav.setViewName(redirectUrl);
    //因为接下来，需要前台记住，刚刚注册用户的email和发送的email token，所以注册成功后，返回这个对象
    return ResponseEntity.ok(emailActivatedDto);

  }



  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @RequestMapping("/admin")
  public String admin() {
    return "property-register this is admin role page ";
  }


}
