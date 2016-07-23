package com.xdidian.keryhu.account_activate.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xdidian.keryhu.account_activate.client.UserClient;
import com.xdidian.keryhu.account_activate.domain.LocalActivateDto;
import com.xdidian.keryhu.account_activate.domain.ActivateType;
import com.xdidian.keryhu.account_activate.domain.TokenType;
import com.xdidian.keryhu.account_activate.service.ActivatedExpired;
import com.xdidian.keryhu.account_activate.service.TokenService;
import com.xdidian.keryhu.domain.Role;

import lombok.RequiredArgsConstructor;

/**
 * @Description : TODO(用户点击－－重新注册)
 * @date : 2016年7月22日 上午11:37:12
 * @author : keryHu keryhu@hotmail.com
 */

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResignupRest {


  private final UserClient userClient;
  private final TokenService tokenService;
  private final ActivatedExpired activatedExpired;


  /**
   *
   * 当用户点击“重新注册”时候，调用的此方法，执行的方法和激活过期一样的
   */
  @RequestMapping(method = RequestMethod.POST, value = "/email/resignup")
  public ResponseEntity<?> emailResignup(@RequestBody final LocalActivateDto dto) {
    if (dto.getType().equals(ActivateType.EMAIL)) {
      
      /**
       * 必须要在emailStatus为false 的情况下，执行下面的操作。
       * 
       */

      if (!userClient.emailStatus(dto.getEmail())) {

        Object obj = tokenService.checkTokenAndEmail(dto, TokenType.RESIGNUP);

        // 如果obj返回的是需要 status＝200，那么就返回它。

        if (obj instanceof ResponseEntity<?>) {
          return (ResponseEntity<?>) obj;
        }

        //和 激活过期的执行任务是一样的。
        List<Role> roles = activatedExpired.executeExpired(dto.getEmail());
        return ResponseEntity.ok(roles);
      
      }


      // 处理phone 的事情。
      else if (dto.getType().equals(ActivateType.PHONE)) {
        return null;
      }
    }
      
    return null;
  }
}
