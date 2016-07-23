package com.xdidian.keryhu.account_activate.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Description : TODO(激活的方式，可选是手机，或者email)
 * @date : 2016年7月20日 上午11:41:37
 * @author : keryHu keryhu@hotmail.com
 */
public enum ActivateType {
  EMAIL, PHONE;

  private static Map<String, ActivateType> activateTypeMap = new HashMap<String, ActivateType>(2);
  
  static{
    activateTypeMap.put("EMAIL", EMAIL);
    activateTypeMap.put("PHONE", PHONE);
    
  }
  
  @JsonCreator
  public static ActivateType forValue(String value){
    return activateTypeMap.get(value);
  }
  
  @JsonValue
  public String toValue(){
    for(Entry<String,ActivateType>  activateType:activateTypeMap.entrySet()){
      if(activateType.getValue()==this)
        return activateType.getKey();
      
    }
    return null;
  }

}
