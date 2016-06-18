package com.xdidian.keryhu.authserver.client;


import com.xdidian.keryhu.authserver.domain.AuthUserDto;
import org.springframework.stereotype.Component;


/**
 * Description : 针对于user-account service remote rest 服务
 * Date : 2016年06月17日 下午9:00
 * Author : keryHu keryhu@hotmail.com
 */
@Component
public class UserClientFallback implements UserClient {

    @Override
    public AuthUserDto ByLoginName(String loginName) {
        // TODO Auto-generated method stub
        return new AuthUserDto();
    }

    /**
     * 如果调用UserAccountClient 对应的spring feign 网络失败，则此方法生效
     */
    @Override
    public Boolean isEmailExist(String email) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 如果调用UserAccountClient 对应的spring feign 网络失败，则此方法生效
     */
    @Override
    public Boolean isPhoneExist(String phone) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 当局登录名loginName，前台web，ajax查询当前loginName所在的数据库，email是否激活
     * 这个是默认的失败调用
     */
    @Override
    public Boolean emailStatus(String loginName) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 将loginName 专为对应的email
     */
    @Override
    public String loginNameToEmail(String loginName) {
        // TODO Auto-generated method stub
        return null;
    }


}
