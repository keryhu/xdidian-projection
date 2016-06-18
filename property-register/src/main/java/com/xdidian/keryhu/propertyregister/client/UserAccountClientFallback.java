package com.xdidian.keryhu.propertyregister.client;


import org.springframework.stereotype.Component;

/**
 * Description : userAccount rest service 方法
 * Date : 2016年06月18日 上午11:02
 * Author : keryHu keryhu@hotmail.com
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
