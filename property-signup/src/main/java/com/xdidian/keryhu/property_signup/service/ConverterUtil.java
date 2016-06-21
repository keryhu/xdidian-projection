package com.xdidian.keryhu.property_signup.service;


import com.xdidian.keryhu.domain.EmailActivatedDto;
import com.xdidian.keryhu.domain.PropertyRegisterDto;
import com.xdidian.keryhu.property_signup.domain.ActivatedProperties;
import com.xdidian.keryhu.property_signup.domain.PropertyForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Function;

/**
 * @Description : 类型转换 不能使用 @RequiredArgsConstructor(onConstructor = @__(@Autowired))
 * @date : 2016年6月18日 下午9:16:46
 * @author : keryHu keryhu@hotmail.com
 */
@Component
@EnableConfigurationProperties(ActivatedProperties.class)
public class ConverterUtil {

  @Autowired
  private ActivatedProperties activatedProperties;

  /**
   * 将web前端提交的物业公司注册数据，转换为 dto，因为需要远程http，所以在传输之前，就先加密密码。
   */
  public Function<PropertyForm, PropertyRegisterDto> propertyFormToRegisterDto = x -> {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);

    return new PropertyRegisterDto(x.getEmail(), x.getPhone(),
        passwordEncoder.encode(x.getPassword()), x.getCompanyName(), x.getDirectName());
  };


  /**
   * 将注册完的物业公司对象PropertyForm，转为EmailActivatedDto,为了email激活
   */

  public Function<PropertyForm, EmailActivatedDto> propertyFormToEmailActivatedDto = x -> {
    EmailActivatedDto dto = new EmailActivatedDto();
    if (activatedProperties != null && activatedProperties.getExpiredTime() > 0) {
      LocalDateTime expireDate =
          LocalDateTime.now().plusHours(activatedProperties.getExpiredTime());
      dto.setExpireDate(expireDate);

    }
    dto.setEmail(x.getEmail());
    dto.setEmailToken(UUID.randomUUID().toString());
    dto.setReregisterToken(UUID.randomUUID().toString());
    dto.setResendToken(UUID.randomUUID().toString());

    return dto;
  };

}
