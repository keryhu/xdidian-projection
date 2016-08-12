package com.xdidian.keryhu.personal_menu.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @ClassName: TargetType
 * @Description: TODO(在员工进行绩效考核，目标管理时，需要提交的目标任务的几种类型， 分为：日/周/月/季/年)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年8月9日 下午5:05:44
 */
public enum TargetType {
  DAY, //  日报
  WEEK, //  周报
  MONTH, // 月报
  SEASON, // 季报
  YEAR; //  年报

  private static Map<String, TargetType> targetTypeMap = new HashMap<String, TargetType>(5);

  static {
    targetTypeMap.put("DAY", DAY);
    targetTypeMap.put("WEEK", WEEK);
    targetTypeMap.put("MONTH", MONTH);
    targetTypeMap.put("SEASON", SEASON);
    targetTypeMap.put("YEAR", YEAR);
  }

  @JsonCreator
  public static TargetType forValue(String value) {
    return targetTypeMap.get(value);
  }

  @JsonValue
  public String toValue() {
    for (Entry<String, TargetType> target : targetTypeMap.entrySet()) {
      if (target.getValue() == this)
        return target.getKey();
    }
    return null;
  }


  /**
   * 
   * @Title: getName @Description: TODO(根据不同的目标类型，得到不同的中文含义，用于前台客户端，显示菜单的中文名字。) @param @return
   * 设定文件 @return String 返回类型 @throws
   */

  public String getName() {
    TargetType type = this;
    String result = "";
    switch (type) {
      case DAY:
        result = "日报";
        break;
      case WEEK:
        result = "周报";
        break;
      case MONTH:
        result = "月报";
        break;
      case SEASON:
        result = "季报";
        break;
      case YEAR:
        result = "年报";
        break;
      default:
        break;
    }

    return result;
  }


}
