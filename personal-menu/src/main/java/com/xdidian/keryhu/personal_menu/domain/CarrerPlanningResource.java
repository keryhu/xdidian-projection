package com.xdidian.keryhu.personal_menu.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @ClassName: CarrerPlanningResource
 * @Description: TODO(用户职业规划设计的时候，可以得到的资源，例如
 * 
 *               职位，薪资，旅游，培训，技能，福利，保险，工作环境或其他)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年8月10日 上午10:08:14
 */
public enum CarrerPlanningResource {

  POSITION, // 晋升岗位
  SALARY, // 薪资
  TOUR, // 旅游
  TRAINING, // 培训
  SKILL, // 技能
  WELFARE, // 福利
  INSURANCE, // 保险
  WORK_ENVIRONMENT, // 工作环境
  OTHER; // 其他

  private static Map<String, CarrerPlanningResource> carrerPlanningResourceMap =
      new HashMap<String, CarrerPlanningResource>(9);

  static {
    carrerPlanningResourceMap.put("POSITION", POSITION);
    carrerPlanningResourceMap.put("SALARY", SALARY);
    carrerPlanningResourceMap.put("TOUR", TOUR);
    carrerPlanningResourceMap.put("TRAINING", TRAINING);
    carrerPlanningResourceMap.put("SKILL", SKILL);
    carrerPlanningResourceMap.put("WELFARE", WELFARE);
    carrerPlanningResourceMap.put("INSURANCE", INSURANCE);
    carrerPlanningResourceMap.put("WORK_ENVIRONMENT", WORK_ENVIRONMENT);
    carrerPlanningResourceMap.put("OTHER", OTHER);

  }

  @JsonCreator
  public static CarrerPlanningResource forValue(String value) {
    return carrerPlanningResourceMap.get(value);
  }

  @JsonValue
  public String toValue() {
    for (Entry<String, CarrerPlanningResource> carrer : carrerPlanningResourceMap.entrySet()) {
      if (carrer.getValue() == this)
        return carrer.getKey();
    }
    return null;
  }

  public String getName() {
    CarrerPlanningResource resource = this;
    String result = "";
    switch (resource) {
      case POSITION:
        result = "晋升岗位";
        break;
      case SALARY:
        result = "薪资";
        break;
      case TOUR:
        result = "旅游";
        break;
      case TRAINING:
        result = "培训";
        break;
      case SKILL:
        result = "技能";
        break;
      case WELFARE:
        result = "福利";
        break;
      case INSURANCE:
        result = "保险";
        break;
      case WORK_ENVIRONMENT:
        result = "工作环境";
        break;
      case OTHER:
        result = "其他";
        break;

      default:
        break;
    }

    return result;
  }

}
