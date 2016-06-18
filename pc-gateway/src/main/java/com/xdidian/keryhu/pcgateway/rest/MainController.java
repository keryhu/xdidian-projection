package com.xdidian.keryhu.pcgateway.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description : 附属的rest设置
 * Date : 2016年06月18日 上午10:46
 * Author : keryHu keryhu@hotmail.com
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
