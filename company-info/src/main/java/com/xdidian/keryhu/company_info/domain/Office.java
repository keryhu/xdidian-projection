package com.xdidian.keryhu.company_info.domain;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

/**
 * 
* @ClassName: Office
* @Description: TODO(公司职位，官职的基础类，是一个公司部门的组成单位
* 为什么这里的下级属性，只是id，而不是完整的office，因为部门最高层的 总监的领导，往往
* 在其他部门，无法形成独立的 树形结构)
* @author keryhu  keryhu@hotmail.com
* @date 2016年8月4日 上午11:19:40
 */
@Data
public class Office implements Serializable{

  private static final long serialVersionUID = 3436698318736828295L;
  
  private String id;    //职位的id，用来区分不同的职位。
  
  private String name;  //职位对外的名字
  
  private String duty; // 岗位的责任
  
  private String right;  //岗位的权利
  
  private String salary;   //薪资
  
  private String BasePay; //基本工资
  
  private String remarks; //备注
  
  private String[] juniorIds; //下级职位的id  ，不允许有重复的出现。
  
  private String leaderId;   //领导职位的id
  
  public Office(String name){
    this.name=name;
    this.id=UUID.randomUUID().toString();
  }
  
  public Office(){
    this.id=UUID.randomUUID().toString();
  }

}
