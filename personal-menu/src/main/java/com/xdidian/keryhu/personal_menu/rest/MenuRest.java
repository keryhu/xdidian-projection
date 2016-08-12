package com.xdidian.keryhu.personal_menu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xdidian.keryhu.personal_menu.service.MenuService;

import lombok.RequiredArgsConstructor;

/**
 * 
* @ClassName: MenuRest
* @Description: TODO(当遇到不同的用户，显示不同的菜单数组。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年8月11日 上午9:52:03
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuRest {
  
  private final MenuService menuService;
  
  //这个需要用户登录才能获取菜单。已经在service里面加上了  权限控制，为什么这需要从前台传递userId，而不是自己获取
  // 如果勇敢 @AuthenticationPrincipal ，那么就必须要传递 user，而这个目前没有。
  @GetMapping("/query/menus")
  public ResponseEntity<?> getMenu(@RequestParam("id") String id){
    
    return ResponseEntity.ok(menuService.getMenu(id));
  }

}
