package com.xdidian.keryhu.user_account.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRest {

  // @PreAuthorize("isAuthenticated()") 表示用户必须是注册用户

  // @PreAuthorize("isAuthenticated()")
  @PreAuthorize("hasAuthority('ROLE_PROPERTY')")
  @GetMapping("/property")
  public String hello() {
    return "this is hello page , it should be ROLE_PROPERTY ";
  }


  @GetMapping("/admin")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String test() {
    return "this is test page , it should be ROLE_ADMIN ";
  }

  @PreAuthorize("hasAuthority('ROLE_TENANT')")
  @GetMapping("/tenant")
  public String tenant() {
    return "this is test page , it should be ROLE_TENANT ";
  }


}
