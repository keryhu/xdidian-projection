package com.xdidian.keryhu.personal_menu.client;

import org.springframework.stereotype.Component;


@Component
public class UserClientFallback implements UserClient{

  @Override
  public boolean isInComopany(String id) {
    // TODO Auto-generated method stub
    return false;
  }

}
