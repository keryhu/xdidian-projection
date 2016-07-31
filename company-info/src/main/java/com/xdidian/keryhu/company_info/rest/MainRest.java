package com.xdidian.keryhu.company_info.rest;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.xdidian.keryhu.company_info.config.DepartmentDeserializer;
import com.xdidian.keryhu.company_info.domain.CompanyInfo;
import com.xdidian.keryhu.company_info.domain.Department;
import com.xdidian.keryhu.tree.TreeNode;

@RestController
public class MainRest {


  @RequestMapping(method = RequestMethod.GET, value = "/query/1")
  public CompanyInfo hello() {
    CompanyInfo c = new CompanyInfo();

    String json =
        "{\"name\":\"111\",\"department\":[{\"name\":\"222\",\"department\":[{\"name\":\"555\"},{\"name\":\"666\"}]},{\"name\":\"333\"},{\"name\":\"444\",\"department\":[{\"name\":\"777\"}]}]}";
    
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addDeserializer(TreeNode.class, new DepartmentDeserializer());
    mapper.registerModule(module);
    try {
      @SuppressWarnings("unchecked")
      TreeNode<Department> n=mapper.readValue(json, TreeNode.class);
      System.out.println(n);
    } catch (JsonParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return c;
  }



}
