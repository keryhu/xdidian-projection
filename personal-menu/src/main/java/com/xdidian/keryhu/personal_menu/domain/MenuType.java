package com.xdidian.keryhu.personal_menu.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @ClassName: MenuType
 * @Description: TODO(平台可能的菜单种类,目前一共是18个菜单名字
 * ,将url硬编码，因为url不常更改。)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年8月9日 上午11:13:23
 */
public enum MenuType {
  HOME, // 首页
  COMPANY_INFO, // 公司信息
  CAREER_PLANNING, // 职业规划
  PERFORMANCE_APPRAISAL, // 绩效考核
  SUBORDINATE_MANAGEMENT, // 下属管理
  ATTENDANCE_SALARY, // 考勤薪资
  RELEASE_MANAGEMENT, // 发布管理
  REPORT_TRAINING, // 报表培训
  INNOVATION_SUGGESTIONS, // 创新建议
  TALENT_MANAGEMENT,      // 人才管理
  RECRUIT_APPLY,         //招聘应聘
  COST_ACCOUNT,         //成本核算
  INTERVIEW_MANAGEMENT,   //面试管理
  AUTHORITY_MANAGEMENT, //权限管理
  RECHARGE_PAYMENT,    //充值付费
  JOIN_COMPANY, // 加入公司
  NEW_COMPANY, // 创建公司
  PERSONAL_INFO; // 个人信息

  private static Map<String, MenuType> menuTypeMap = new HashMap<String, MenuType>(18);

  static {
    menuTypeMap.put("HOME", HOME);
    menuTypeMap.put("COMPANY_INFO", COMPANY_INFO);
    menuTypeMap.put("CAREER_PLANNING", CAREER_PLANNING);
    menuTypeMap.put("PERFORMANCE_APPRAISAL", PERFORMANCE_APPRAISAL);
    menuTypeMap.put("SUBORDINATE_MANAGEMENT", SUBORDINATE_MANAGEMENT);
    menuTypeMap.put("ATTENDANCE_SALARY", ATTENDANCE_SALARY);
    menuTypeMap.put("RELEASE_MANAGEMENT", RELEASE_MANAGEMENT);
    menuTypeMap.put("REPORT_TRAINING", REPORT_TRAINING);
    menuTypeMap.put("INNOVATION_SUGGESTIONS", INNOVATION_SUGGESTIONS);
    menuTypeMap.put("TALENT_MANAGEMENT", TALENT_MANAGEMENT);
    menuTypeMap.put("RECRUIT_APPLY", RECRUIT_APPLY);
    menuTypeMap.put("COST_ACCOUNT", COST_ACCOUNT);
    menuTypeMap.put("INTERVIEW_MANAGEMENT", INTERVIEW_MANAGEMENT);
    menuTypeMap.put("AUTHORITY_MANAGEMENT", AUTHORITY_MANAGEMENT);
    menuTypeMap.put("RECHARGE_PAYMENT", RECHARGE_PAYMENT);
    menuTypeMap.put("JOIN_COMPANY", JOIN_COMPANY);
    menuTypeMap.put("NEW_COMPANY", NEW_COMPANY);
    menuTypeMap.put("PERSONAL_INFO", PERSONAL_INFO);
  }

  @JsonCreator
  public static MenuType forValue(String value) {
    return menuTypeMap.get(value);
  }

  @JsonValue
  public String toValue() {
    for (Entry<String, MenuType> menu : menuTypeMap.entrySet()) {
      if (menu.getValue() == this)
        return menu.getKey();
    }
    return null;
  }

  /**
   * 
  * @Title: getName
  * @Description: TODO(根据不同的菜单类型，得到不同的中文含义，用于前台客户端，显示菜单的中文名字。)
  * @param @return    设定文件
  * @return String    返回类型
  * @throws
   */
  
  public String getName() {
    MenuType menu = this;
    String result = "";
    switch (menu) {
      case HOME:
        result = "会员首页";
        break;
      case COMPANY_INFO:
        result = "公司信息";
        break;
      case CAREER_PLANNING:
        result = "职业规划";
        break;
      case PERFORMANCE_APPRAISAL:
        result = "绩效考核";
        break;
      case SUBORDINATE_MANAGEMENT:
        result = "下属管理";
        break;
      case ATTENDANCE_SALARY:
        result = "考勤薪资";
        break;
      case RELEASE_MANAGEMENT:
        result = "发布管理";
        break;
      case REPORT_TRAINING:
        result = "报表培训";
        break;
      case INNOVATION_SUGGESTIONS:
        result = "创新建议";
        break;
      case TALENT_MANAGEMENT:
        result="人才管理";
        break;
      case RECRUIT_APPLY:
        result="招聘应聘";
        break;
      case COST_ACCOUNT:
        result="成本核算";
        break;
      case INTERVIEW_MANAGEMENT:
        result="面试管理";
        break;
      case AUTHORITY_MANAGEMENT:
        result="权限管理";
        break;
      case RECHARGE_PAYMENT:
        result = "充值付费";
        break;
      case JOIN_COMPANY:
        result = "加入公司";
        break;
      case NEW_COMPANY:
        result = "创建公司";
        break;
      case PERSONAL_INFO:
        result = "个人信息";
        break;
      default:
        break;
    }
    return result;

  }
  
  /**
   * 
  * @Title: getUrl
  * @Description: TODO(不同的菜单导航到不同的url，右边显示该url对应的页面)
  * @param @return    设定文件
  * @return String    返回类型
  * @throws
   */
  public String  getUrl(){
    MenuType menu = this;
    String result = "";
    switch (menu) {
      case HOME:
        result = "/profile/index";
        break;
      case COMPANY_INFO:
        result = "/profile/companyInfo";
        break;
      case CAREER_PLANNING:
        result = "/profile/career-planning";
        break;
      case PERFORMANCE_APPRAISAL:
        result = "/profile/performance-appraisal";
        break;
      case SUBORDINATE_MANAGEMENT:
        result = "/profile/subordinate-management";
        break;
      case ATTENDANCE_SALARY:
        result = "/profile/attendance-salary";
        break;
      case RELEASE_MANAGEMENT:
        result = "/profile/release-management";
        break;
      case REPORT_TRAINING:
        result = "/profile/report-training";
        break;
      case INNOVATION_SUGGESTIONS:
        result = "/profile/innovation-suggestions";
        break;
      case TALENT_MANAGEMENT:
        result="/profile/talent-management";
        break;
      case RECRUIT_APPLY:
        result="/profile/recruit-apply";
        break;
      case COST_ACCOUNT:
        result="/profile/cost-account";
        break;
      case INTERVIEW_MANAGEMENT:
        result="/profile/interview-management";
        break;
      case AUTHORITY_MANAGEMENT:
        result="/profile/authority-management";
        break;
      case RECHARGE_PAYMENT:
        result = "/profile/recharge-payment";
        break;
      case JOIN_COMPANY:
        result = "/profile/join-company";
        break;
      case NEW_COMPANY:
        result = "/profile/new-company";
        break;
      case PERSONAL_INFO:
        result = "/profile/psrsonal-info";
        break;
      default:
        break;
    }
    return result;
  }

}
