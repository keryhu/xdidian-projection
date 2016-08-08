package com.xdidian.keryhu.personal_menu.domain;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class Menu implements Serializable{

  private static final long serialVersionUID = 4336915375251779538L;
  
  private String id;
  
  private String userId;  //对于的user Id
  
  private String name;   //菜单的名字
  
  private String url;   //菜单所指向的url连接
  
  public Menu(){
    this.id=UUID.randomUUID().toString();
    
  }

}
