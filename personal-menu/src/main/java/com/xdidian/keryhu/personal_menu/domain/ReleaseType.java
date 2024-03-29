package com.xdidian.keryhu.personal_menu.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
* @ClassName: ReleaseType
* @Description: TODO(发布管理的，可以发布的内容的形势，
* 通常包含： 
* 会议／公告／公司活动／体育活动／旅游)
* @author keryhu  keryhu@hotmail.com
* @date 2016年8月10日 下午3:04:31
 */
public enum ReleaseType {

  MEETING,    //   会议
  NOTICE,     //  公告
  COMPANY_EVENT,   //  公司活动
  SPORTS,     //   体育活动
  TOUR;      //     旅游
  
  private static Map<String, ReleaseType> releaseTypeMap = new HashMap<String, ReleaseType>(5);
  
  static{
    releaseTypeMap.put("MEETING",MEETING );
    releaseTypeMap.put("NOTICE", NOTICE);
    releaseTypeMap.put("COMPANY_EVENT",COMPANY_EVENT );
    releaseTypeMap.put("SPORTS",SPORTS );
    releaseTypeMap.put("TOUR", TOUR);
  }

  
  @JsonCreator
  public static ReleaseType forValue(String value) {
    return releaseTypeMap.get(value);
  }

  @JsonValue
  public String toValue() {
    for (Entry<String, ReleaseType> release : releaseTypeMap.entrySet()) {
      if (release.getValue() == this)
        return release.getKey();
    }
    return null;
  }

 
  public String getName(){
    ReleaseType release=this;
    String result="";
    switch(release){
      case MEETING:
        result="会议";
        break;
      case NOTICE:
        result="公告";
        break;
      case COMPANY_EVENT:
        result="公司活动";
        break;
      case SPORTS:
        result="体育活动";
        break;
      case TOUR:
        result="旅游";
        break;
      default:
        break;
      
    }
    return result;
  }
  
}
