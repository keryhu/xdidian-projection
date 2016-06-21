package com.xdidian.keryhu.password_reset.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @Description : 关于password验证时,token的rest操作
 * @date : 2016年6月18日 下午9:09:03
 * @author : keryHu keryhu@hotmail.com
 */
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenRest {

  @RequestMapping(method = RequestMethod.POST, value = "/password/uploadEmail")
  public void uploadEmail(@RequestBody final String email) {

  }

  /**
   * 忘记密码时，如果通过email重设密码，用户打开重设密码的链接 需要验证的步骤 1 id，即userId是否存在于数据库 2 token是否和id匹配于user-account系统
   * <p>
   * 返回的是错误的提示，如果错误提示没有任何信息，那么证明通过了验证)
   *
   * @param id userId
   * @param token 就是passwordToken
   */
  @RequestMapping(method = RequestMethod.GET, value = "/code")
  public ResponseEntity<?> validateEmail(@RequestParam("id") final String id,
      @RequestParam("token") final String token) {
    Map<String, Integer> result = new HashMap<String, Integer>();

    log.info("");
    return ResponseEntity.ok(result);
  }

}
