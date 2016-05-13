package com.xdidian.keryhu.pcgateway.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
