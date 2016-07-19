package com.xdidian.keryhu.pc_gateway.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description : 附属的rest设置
 * @date : 2016年6月18日 下午9:10:56
 * @author : keryHu keryhu@hotmail.com
 */
@RestController
public class MainController {


  @RequestMapping("/hello")
  public String hello() {
    return "Hello this is pc-gateway'hello page!";
  }

  @RequestMapping("/test")
  public String test() {
    return "Hello this is pc-gateway'test  page , it not protected !";
  }
  
  
  


}
