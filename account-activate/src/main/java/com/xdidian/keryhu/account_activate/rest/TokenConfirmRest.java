package com.xdidian.keryhu.account_activate.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.xdidian.keryhu.account_activate.client.UserClient;
import com.xdidian.keryhu.account_activate.domain.*;
import com.xdidian.keryhu.account_activate.service.ActivatedConfirm;
import com.xdidian.keryhu.account_activate.service.TokenService;

import lombok.RequiredArgsConstructor;
import static com.xdidian.keryhu.account_activate.domain.Constants.EMAIL_ACTIVATE_SUCCESS;;


@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenConfirmRest {

  private final UserClient userClient;
  private final TokenService tokenService;
  private final ActivatedConfirm activatedConfirm;


  /**
   * 用于前台输入邮件或手机中的验证码，来验证email和手机号的 的方法。
   */


  @PostMapping("/accountActivate/emailOrPhone")
  public ResponseEntity<?> tokenConfirm(@RequestBody final LocalActivateDto dto) {

    if (dto.getType().equals(ActivateType.EMAIL)) {
      
      /**
       * 必须要在emailStatus为false 的情况下，执行下面的操作。
       */
      
      if (!userClient.emailStatus(dto.getEmail())) {

        // email 不存在于本地的情况下 2 如果token不存在于对于的email数据库 3 如果token 已经过期。这几种情形整理到tokenService

        Object obj = tokenService.checkTokenAndEmail(dto, TokenType.CONFIRM);

        // 如果obj返回的是需要 status＝200，那么就返回它。

        if (obj instanceof ResponseEntity<?>) {
          return (ResponseEntity<?>) obj;
        }

        // 最后的情况是，验证成功了。执行这个确认流程，前台接受到ACTIVATE_SUCCESS，导航到login页面。


        activatedConfirm.exec(dto.getEmail());
        return ResponseEntity.ok(EMAIL_ACTIVATE_SUCCESS);

      }
      // 这里是email激活的情况，当然这个时候 前台已经实现了，跳转login页面

      return null;
    }

    // 手机激活的情形。

    else if (dto.getType().equals(ActivateType.PHONE)) {
      // 执行phone验证

      // 验证提交的数据合法性

      return ResponseEntity.ok(dto);
    }

    // 如果既不是email，也不是phone的情形，报错。
    else {
      return null;
    }

  }

}
