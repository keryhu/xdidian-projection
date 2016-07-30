package com.xdidian.keryhu.company_info.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.xdidian.keryhu.company_info.domain.CompanyInfo;

@RestController
public class MainRest {

  
  @RequestMapping(method = RequestMethod.GET, value = "/query/1")
  public CompanyInfo hello() {
    CompanyInfo c=new CompanyInfo();
    System.out.println(c);
   
    return c;
  }



}
