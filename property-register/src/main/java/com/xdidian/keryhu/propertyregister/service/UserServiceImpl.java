/**
 * @Title: UserServiceImpl.java
 * @Package com.xdidian.keryhu.propertyregister.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月3日 上午11:27:11
 * @version V1.0
 */
package com.xdidian.keryhu.propertyregister.service;

import com.xdidian.keryhu.propertyregister.client.UserAccountClient;
import com.xdidian.keryhu.propertyregister.domain.PropertyForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static com.xdidian.keryhu.util.StringValidate.*;

/**
 * Description : 实现了UserService 的接口
 * Date : 2016年06月18日 上午11:12
 * Author : keryHu keryhu@hotmail.com
 */
@Component("userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserAccountClient userAccountClient;

    /**
     * 验证前台输入信息的合法性
     */
    @Override
    public void vlidateBeforSave(PropertyForm propertyForm) {
        // TODO Auto-generated method stub
        log.info("需要验证的propertyForm is ： " + propertyForm);
        Assert.isTrue(isEmail(propertyForm.getEmail()), "email格式不正确！");
        Assert.isTrue(isPhone(propertyForm.getPhone()), "phone格式不正确！");
        Assert.isTrue(isPassword(propertyForm.getPassword()), "手机格式不正确！");
        Assert.isTrue(isCompanyName(propertyForm.getCompanyName()), "公司名字格式不正确！");
        Assert.isTrue(isPeopleName(propertyForm.getDirectName()), "姓名格式不正确！");

        Assert.isTrue(!userAccountClient.isEmailExist(propertyForm.getEmail()), "email已经注册过，请直接登录！");
        Assert.isTrue(!userAccountClient.isPhoneExist(propertyForm.getPhone()), "phone已经注册，请直接登录！");
        Assert.isTrue(!userAccountClient.isCompanyNameExist(propertyForm.getCompanyName()), "公司名字已经注册，请直接登录！");

    }

}
