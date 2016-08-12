package com.xdidian.keryhu.account_activate.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.xdidian.keryhu.account_activate.client.UserClient;
import com.xdidian.keryhu.account_activate.domain.LocalActivateDto;
import com.xdidian.keryhu.account_activate.domain.ActivateType;
import com.xdidian.keryhu.account_activate.domain.TokenType;
import com.xdidian.keryhu.account_activate.service.ResendService;
import com.xdidian.keryhu.account_activate.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description : TODO(用户点击“重新发送”促发的后台controller)
 * @date : 2016年7月22日 下午12:05:25
 * @author : keryHu keryhu@hotmail.com
 */
@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResendRest {
  
  private final UserClient userClient;
  private final TokenService tokenService;
  private final ResendService resendService;
  
  /**
  *
  * 当用户点击“再次发送激活邮件”的时候，调用的此方法 分为两段 执行发送次数是否超的判断和消息提示，另外就是更新验证码和发送次数＋1，并保存数据库 －－ 就是
  * tokenService.doWithResend(email)执行的方法
  * 
  * 另外一段是： 如果没有过期，前台页面不跳转页面 tokenService.doWhenNotExpired )
  */

 @PostMapping("/email/resend")
 public ResponseEntity<?> emailResend(@RequestBody final LocalActivateDto dto) {

   if (dto.getType().equals(ActivateType.EMAIL)) {

     /**
      * 
      * 必须要在emailStatus为false 的情况下，执行下面的操作。
      * 
      */

     if (!userClient.emailStatus(dto.getEmail())) {

       Object obj = tokenService.checkTokenAndEmail(dto, TokenType.RESEND);

       // 如果obj返回的是需要 status＝200，那么就返回它。

       if (obj instanceof ResponseEntity<?>) {
         return (ResponseEntity<?>) obj;
       }

       // 点击“重新发送激活邮件”时间为冷却，过滤操作次数过多，和数据库处理

       resendService.clickResend(dto.getEmail());

       // 需要重设的url 参数，并且返回到前台，让前台去更新相关数据。

       Map<String, String> r = resendService.resetUrlParams(dto.getEmail());
       log.info("reset resend token and resignup token ! ");
       return ResponseEntity.ok(r);
     }

     return null;
   }

   // 处理phone 的事情。
   else if (dto.getType().equals(ActivateType.PHONE)) {
     return null;
   }


   return null;
 }


}
