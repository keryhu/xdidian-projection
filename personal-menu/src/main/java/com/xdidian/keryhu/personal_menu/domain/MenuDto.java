package com.xdidian.keryhu.personal_menu.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
* @ClassName: MenuDto
* @Description: TODO(前台显示菜单到时候，需要知道菜单的中文名字，和对应的url，这个就是一个前台需要的dto，
* 将保存在后台的menu数据库，转为前台需要的数据格式。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年8月11日 下午4:35:56
 */

@Data
@AllArgsConstructor
public class MenuDto implements Serializable {
 
  private static final long serialVersionUID = 2813824434488514777L;
  
  private String name;
  private String url;
  
  public MenuDto(){
    
  }

}
