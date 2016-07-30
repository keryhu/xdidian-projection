package com.xdidian.keryhu.company_info.domain;


import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xdidian.keryhu.tree.TraversalAction;
import com.xdidian.keryhu.tree.TreeNode;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description : TODO(Department的json自定义序列化)
 * @date : 2016年7月28日 下午8:38:43
 * @author : keryHu keryhu@hotmail.com
 */

@Slf4j

public class DepartmentSerializer extends JsonSerializer<DepartmentTreeNode<Department>>{

  @Override
  public void serialize(DepartmentTreeNode<Department> value, JsonGenerator jgen,
      SerializerProvider provider) throws IOException, JsonProcessingException {
    // TODO Auto-generated method stub
  
    ObjectMapper mapper = new ObjectMapper();
    Gson gson = new GsonBuilder().serializeNulls().create();
    jgen.writeString(gson.toJson(value));
    
    
  }


}
