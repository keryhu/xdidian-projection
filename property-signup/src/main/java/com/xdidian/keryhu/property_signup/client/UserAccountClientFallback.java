package com.xdidian.keryhu.property_signup.client;


import org.springframework.stereotype.Component;



/**
 * @Description : userAccount rest service 方法
 * @date : 2016年6月18日 下午9:14:06
 * @author : keryHu keryhu@hotmail.com
 */
@Component
public class UserAccountClientFallback implements UserAccountClient {


  /**
   * 查看email是否存在
   */
  @Override
  public Boolean isEmailExist(String email) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * 查看phone是否存在
   */
  @Override
  public Boolean isPhoneExist(String phone) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * 查看公司名字是否存在
   */
  @Override
  public Boolean isCompanyNameExist(String companyName) {
    // TODO Auto-generated method stub
    return false;
  }


}
